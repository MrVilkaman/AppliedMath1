package alho

import f
import gradX1
import gradX2
import kotlin.reflect.KFunction2

/**
 * Created by Zahar on 02.11.16.
 */

data class Ans(val x: Double, val y: Double, val k: Int)

fun Double.format() = this.format(13)
fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)


class Newtoon {
	fun work(func: KFunction2<Double, Double, Double>, gradX: KFunction2<Double, Double, Double>,
	         gradY: KFunction2<Double, Double, Double>, e: Double, log: Boolean): Ans {

		val x0 = 4.12
		val y0 = 2.74
		var x = x0
		var y = y0

		var gradxPrev = 0.0
		var gradyPrev = 0.0

		var k = 0
		if (log)
			output(x, y, k)
		do {

			val gradx = gradX(x, y)
			val grady = gradY(x, y)
			x -= getGesX() * gradx
			y -= getGesY() * grady


			val d = gradx - gradxPrev
			val d2 = grady - gradyPrev
			val prev = Math.sqrt(d * d + d2 * d2)
			gradxPrev = gradx
			gradyPrev = grady
			k++

			if (log)
				output(x, y, k)
		} while (e < prev)

		return Ans(x, y, k)
	}

	private fun  getGesX(): Double = 20.0/40.0
	private fun  getGesY(): Double = 2.0/40.0
}


class Grad {
	fun work(func: KFunction2<Double, Double, Double>, gradX: KFunction2<Double, Double, Double>,
	         gradY: KFunction2<Double, Double, Double>, e: Double, log: Boolean): Ans {

		val x0 = 4.12
		val y0 = 2.74
		val h = 0.05
		var x = x0
		var y = y0

		var gradxPrev = 0.0
		var gradyPrev = 0.0

		var k = 0
		if (log)
			output(x, y, k)
		do {

			val gradx = gradX(x, y)
			val grady = gradY(x, y)
			x -= h * gradx
			y -= h * grady


			val d = gradx - gradxPrev
			val d2 = grady - gradyPrev
			val prev = Math.sqrt(d * d + d2 * d2)
			gradxPrev = gradx
			gradyPrev = grady
			k++

			if (log)
				output(x, y, k)
		} while (e < prev)

		return Ans(x, y, k)
	}


}

fun output(x: Double, y: Double, k: Int) {
	println("$k) ${x.format()} ${y.format()}")

}

class GradFast {
	fun work(func: KFunction2<Double, Double, Double>, gradX: KFunction2<Double, Double, Double>,
	         gradY: KFunction2<Double, Double, Double>, e: Double, log: Boolean): Ans {

		val x0 = 4.12
		val y0 = 2.74
		var x = x0
		var y = y0

		var gradxPrev = 0.0
		var gradyPrev = 0.0

		var k = 0
		if (log)
			output(x, y, k)
		do {

			val gradx = gradX(x, y)
			val grady = gradY(x, y)
			val alpha = Dihotomia(-1000.0,1000.0,0.0001,x,y)
			x -= alpha * gradx
			y -=  alpha * grady


			val d = gradx - gradxPrev
			val d2 = grady - gradyPrev
			val prev = Math.sqrt(d * d + d2 * d2)
			gradxPrev = gradx
			gradyPrev = grady
			k++

			if (log)
				output(x, y, k)
		} while (e < prev)

		return Ans(x, y, k)
	}
}

fun g(x: Double, y: Double, alpha: Double): Double {
	return f(x - alpha * gradX1(x, y), y - alpha * gradX2(x, y))
}

//Метод половинного деления для нахождения минимума в градиентном спуске
fun Dihotomia(a0: Double, b0: Double, epsilon: Double, x: Double, y: Double): Double {
	//Номер шага
	var k: Int
	//Отклонени от середины отрезка влево, вправо
	var lk: Double
	var mk: Double
	//Величина на которую мы отклонимся от середины отрезка
	val delta = 0.5 * epsilon
	//Точка минимума
	val x_: Double
	//Отрезок локализации минимума
	var ak = a0
	var bk = b0
	k = 1
	//Пока длина отрезка больше заданной точности
	do {
		//Берем середину (ну почти середину - +\- некоторое дельта в частности у нас delta=0.5*epsilon)
		lk = (ak + bk - delta) / 2
		mk = (ak + bk + delta) / 2

		k++
		//Проверяем в какую часть попадает точка минимума слева от разбиения или справа и выбираем соответствующую точку
		if (g(x, y, lk) <= g(x, y, mk)) {
			//Теперь правая граница отрезка локализации равна mk
			bk = mk
		} else {
			//Теперь левая граница отрезка локализации равна mk
			ak = lk
		}
	} while (bk - ak >= epsilon)

	x_ = (ak + bk) / 2 //minimum point

	return x_
}

