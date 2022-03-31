/**
 * How do you find the missing number in a given integer array of 1 to 100?
 */

fun findMissing(list: List<Int> = (0..100).toMutableList().also { it.remove(23) }.toList()): Int {

    val total = (0..100).toList().sum()

    val res = (0..100).toList().sum() - list.sum()

    println(res)

    return if (res == total) 0 else res
}

/**
 * How do you find the duplicate number on a given integer array?
 *
 */

fun duplicateInt(lista: List<Int> = listOf(-23, 140, -93, 16699, 3, 12, 18, 55, 3)): Int {
    val listaOrdenada = lista.sorted()

    var res = 0

    for (i in 0 until listaOrdenada.size - 1) {
        if (lista[i] == lista[i + 1]) {
            res = lista[i]
        }
    }

    println(println(res))
    return res
}

fun woordMatch(word: String, list: List<String>) {

}

fun primeChecker(n: Int): String {

    var result = ""
    for (i in 2..(n / 2) + 1) {
        if (n % i == 0) {
            result = "$n: is NOT Prime"
            break
        } else {
            result = "$n: is  Prime"
        }
    }

    println(result)
    return result
}

class Ramo(val name: String, val nota: MutableList<Double> = mutableListOf()) {

    override fun toString(): String {
        return this.name
    }

    fun setNotas(list: List<Double>) = list.forEach { this.nota.add(it) }

    fun promedioPorRamo(): Double = nota.average()
}

class Alumno(var name: String, val ramos: MutableList<Ramo> = mutableListOf()) {

    fun addRamos(ramo: Ramo) = this.ramos.add(ramo)

    fun promedioPorRamoPorAlumno(nombreRamo: String): String {
        val a = this.ramos.filter { it.name == nombreRamo }
        println("Promedio de ${this.name} en $nombreRamo es de: ${a[0].promedioPorRamo().toString()}")
        return a[0].promedioPorRamo().toString()
    }

    fun promedioGeneral(): String {
        val c = this.ramos.sumOf { it.nota.average() } / ramos.size
        println(c)
        return ""
    }
}

fun String.ordenar(): String = this.toCharArray().apply { this.sort() }.joinToString("")

fun anagrams(list: List<String>): List<String> {

    val res = list.toMutableList()

    val stack = mutableListOf<String>()

    for (i in 0 until list.size - 1) {
        var current = list[i].ordenar()

        var next = list[i + 1].ordenar()

        res.add(list.filter { it.ordenar() == list[i].ordenar()  }.first())

        if (!stack.contains(list[i]) && stack.firstOrNull { it.ordenar() !=current } == null) {


        }

    }

    val a = res.filter { it.ordenar() == "cdeo"}
    //println(a)
    res.sort()

    println(res)
    //println(stack)
    return emptyList()
    /*
    anagrams(listOf("code", "doce", "ecod", "framer", "frame"))
    anagrams(listOf("aaagmnrs", "code"))
    anagrams(listOf(
        "code","aaagmnrs","anagrams","doce","cedo", "ana"
    ))
     */
}

fun possibleAnagram(string: String){
    val listado = mutableListOf<String>().also { it.add(string) }

    val palabras = string.map { it.toString() }.toMutableList()
    var letra = string[0].toString()
    var letrados = palabras[1]


    for (s in string){
        val str = s.toString()
        for (i in 0 until palabras.size - 1){
            palabras[i] = palabras[i+1]
            palabras[i+1] = str

            val word = palabras.joinToString("")


            if (!listado.contains(word)) listado.add(word)
        }
    }

    println(listado)


}

fun main() {
possibleAnagram("puerto")

}

