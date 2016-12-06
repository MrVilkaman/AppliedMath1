import alho.*
import kotlin.reflect.KFunction2

/**
 * Created by Zahar on 02.11.16.
 */


fun main(args: Array<String>) {

	val E = Math.pow(10.0, -12.0)

	val grad = Grad().work(::f, ::gradX1, ::gradX2, E, false)
	val grad2 = GradFast().work(::f, ::gradX1, ::gradX2, E, false)
	val grad3 = Newtoon().work(::f, ::gradX1, ::gradX2, E, false)

	qwer(grad)
	qwer(grad2)
	qwer(grad3)
}

private fun qwer(grad: Ans) {
	println("f= ${f(grad.x, grad.y).format()}| x1 = ${grad.x.format()} x2 = ${grad.y.format()} за ${grad.k}")
}


fun f(x1: Double, x2: Double): Double = x1 * x1 + 10 * (x2 - 1) * (x2 - 1)

fun gradX1(x1: Double, x2: Double): Double = 2 * x1

fun gradX2(x1: Double, x2: Double): Double = 20 * x2 - 20

