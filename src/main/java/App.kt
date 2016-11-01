import alho.Bisection
import alho.IAlgorithm

/**
 * Created by Zahar on 02.11.16.
 */




fun main(args: Array<String>) {

	val E = Math.pow(10.0,-12.0)

	val a: IAlgorithm = Bisection()

	val expression: (Double) -> Double = {
		Math.tan(it + Math.PI / 5)
	}
	val result = a.find(1.0, 4.0, E, expression)


	println("F(x): = tg(x + PI/5)")
	println("Результат: $result")
	println("F(${result.point}): =${expression.invoke(result.point)}")

}