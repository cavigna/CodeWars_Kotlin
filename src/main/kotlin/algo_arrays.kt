import kotlin.random.Random

/**
 * How do you find the missing number in a given integer array of 1 to 100?
 */

fun findMissing(list: List<Int>):Int{

    val total = (0..100).toList().sum()

    val res = (0..100).toList().sum() - list.sum()

    println(res)

    return if(res == total) 0 else res
}

fun main(){
    val prueba = (0..100).toMutableList().also { it.remove(23) }.toList()
    findMissing(
        (0..100).toMutableList().also { it.remove(Random.nextInt(0,100)) }.toList()
    )

    findMissing(prueba)
}