import kotlin.random.Random

/**
 * How do you find the missing number in a given integer array of 1 to 100?
 */

fun findMissing(list: List<Int> = (0..100).toMutableList().also { it.remove(23) }.toList()):Int{

    val total = (0..100).toList().sum()

    val res = (0..100).toList().sum() - list.sum()

    println(res)

    return if(res == total) 0 else res
}

/**
 * How do you find the duplicate number on a given integer array?
 *
 */

fun duplicateInt(lista: List<Int> = listOf(-23,140,-93,16699,3,12,18,55,3)): Int{
    val listaOrdenada = lista.sorted()

    var res = 0

    for (i in 0 until listaOrdenada.size-1){
        if (lista[i] == lista[i+1])  {
            res = lista[i] }
    }

    println(println(res))
    return res
}

fun woordMatch(word:String, list: List<String>){
    
}

fun main(){

    duplicateInt()
}