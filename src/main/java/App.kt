import alho.*
import kotlin.reflect.KFunction2

/**
 * Created by Zahar on 02.11.16.
 */




fun main(args: Array<String>) {
//
//	val E = Math.pow(10.0,-12.0)
//
//	val expression: (Double) -> Double = {
//		Math.tan(it + Math.PI / 5)
//	}
//
//	println("Метод половинного деления")
//	val result = Bisection().find(1.0, 4.0, E, expression)
//	qwer(expression, result)
//
//	println()
//	println("Метод Ньютона")
//	val derivative: (Double) -> Double = {
//		val cos = Math.cos(it + Math.PI / 5)
//		1 /(cos * cos)
//	}
//	val result2 = Newton().find(3.0, E, expression,derivative)
//	qwer(expression, result2)
//
//	println()
//
//	println("Метод простой итерации")
//	val result3 = Simple().find(3.0, E, expression)
//	qwer(expression, result3)
		val E = Math.pow(10.0,-12.0)

	Grad().work(::f, )

}

class Grad {
	fun work(kFunction2: KFunction2<Double, Double, Double>) {
		val y = 0.0
		var yNext = y + kFunction2()

	}
}

class NewToon {
	fun work(kFunction2: KFunction2<Double, Double, Double>) {
		val y = 0.0
		var yNext = y + kFunction2()

	}
}

fun f(x1:Double,x2:Double): Double {
	val d1 = x2 - 1
	return x1*x1+10*d1*d1
}


private fun qwer(expression: (Double) -> Double, result: CalcResult) {
	println("F(x): = tg(x + PI/5)")
	println("Результат: $result")
	println("F(${result.point}): =${expression.invoke(result.point)}")
}