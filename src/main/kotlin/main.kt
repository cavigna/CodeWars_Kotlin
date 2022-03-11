import kotlin.math.round
import kotlin.test.assertEquals

fun main() {
    lala(listOf(1, 2, 2, 20, 6, 20, 2, 6, 2))
    println("/************/")
    lala(listOf(0, 0, 0, 0, 0, 0, 0))

}


fun lala(l: List<Int>) {
    val listado = listOf(1, 2, 5, 6, 5, 2)
    val freq = l.groupingBy { it }.eachCount().filterValues { it > 1 }
    var pares = 0
    var valor: Int

    freq.forEach { (k, v) ->
        print("Key: $k")
        print(" Value: $v ")
        println()
        valor = v

        while (valor >= 2) {
            println("Valor = $valor")
            println("Pares = $pares")
            pares += 1
            valor /= 2


        }

    }



    println(freq)
    println("Pares = $pares")
}