package alho

/**
 * Created by Zahar on 02.11.16.
 */

interface IAlgorithm {
	fun find(left: Double, right: Double, accuracy: Double, expression: (Double) -> Double): CalcResult
}

data class CalcResult(val iterationCount: Int, val point: Double, val error: Boolean = false)


class Bisection(val maxIteration: Int = 1000) : IAlgorithm {
	override fun find(pleft: Double, pright: Double, accuracy: Double, expression: (Double) -> Double): CalcResult {
		var iterationCount = 0
		var right = pright
		var left = pleft

		if (expression(left) * expression(right) >= 0) {
			CalcResult(iterationCount, 0.0, true)
		}

		var c: Double
		do {
			c = (left + right) / 2;

			if (expression(left) * expression(c) < 0) {
				right = c
			} else {
				left = c
			}

			if (Math.abs(expression(c)) <= accuracy || (right - left) <= accuracy || iterationCount == maxIteration) {
				break
			}

			iterationCount++
		} while (true)

		return CalcResult(iterationCount, c)
	}
}

class Newton {

	fun find(px: Double, accuracy: Double, expression: (Double) -> Double, derivative: (Double) -> Double): CalcResult {
		var iterationCount = 0
		var x1: Double
		var y: Double
		var x = px
		do {
			iterationCount++
			val ext = expression(x)
			x1 = x - ext / derivative(x)
			x = x1
			y = ext
		} while (Math.abs(y) >= accuracy)

		return CalcResult(iterationCount, x)
	}
}

class Simple {

	fun find(px: Double, accuracy: Double, expression: (Double) -> Double): CalcResult {
		var x = 0.0
		var xPrev = px

		var iterationCount = 0
		do {
			val expressionPrev = expression(xPrev)
			if (Math.signum(expression(x)) == Math.signum(expressionPrev)) {
				x = xPrev - 1.2 * expressionPrev
			} else {
				x = xPrev - 0.5 * expressionPrev
			}

			if (Math.abs(x - xPrev) <= accuracy || iterationCount == 1000) {
				break
			}
			xPrev = x
			iterationCount++
		} while (true)

		return CalcResult(iterationCount, x)
	}
}