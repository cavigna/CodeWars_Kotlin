fun evaporator(content: Double, evap_per_day: Double, threshold: Double): Int {
    var days = 0
    var percentage = 100.0
    while (percentage > threshold) {
        percentage -= percentage * evap_per_day / 100

        days++
    }

    return days
}

/**
 * Simple enough this one - you will be given an array. The values in the array will either be numbers or strings, or a mix of both. You will not get an empty array, nor a sparse one.

Your job is to return a single array that has first the numbers sorted in ascending order, followed by the strings sorted in alphabetic order. The values must maintain their original type.

Note that numbers written as strings are strings and must be sorted with the other strings.

arrayOf<Any>("Apple",46,"287",574,"Peach","3","69",78,"Grape","423"), arrayOf<Any>(46, 78, 574, "287", "3", "423", "69", "Apple", "Grape", "Peach")
 */


fun dbSort(a: Array<Any>): Array<Any> {
    var b = mutableListOf<Int>()
    var c = mutableListOf<String>()

    a.forEach {
        if (it is Int) {
            b.add(it)
        } else if (it is String) {
            c.add(it)
        }
    }
    b.sort()
    c.sort()

    return (b + c).toTypedArray()
}
// shorter ==> fun dbSort(a: Array<Any>): Array<Any> = (a.filterIsInstance<Int>().sortedBy { it } + a.filterIsInstance<String>().sorted()).toTypedArray()

/**
 * Given an array of digitals numbers, return a new array of length number containing the last even numbers from the original array (in the same order). The original array will be not empty and will contain at least "number" even numbers.

For example:

([1, 2, 3, 4, 5, 6, 7, 8, 9], 3) => [4, 6, 8]
([-22, 5, 3, 11, 26, -6, -7, -8, -9, -8, 26], 2) => [-8, 26]
([6, -25, 3, 7, 5, 5, 7, -3, 23], 1) => [6]
 */

fun evenNumbers(array: List<Int>, number: Int): List<Int> =
    array.filter { it % 2 == 0 }.reversed().slice(0 until number).reversed()

// shorter ==> fun evenNumbers(array: List<Int>, number: Int) = array.filter{ it % 2 == 0 }.takeLast(number)

/**
 * Enjoying your holiday, you head out on a scuba diving trip!

Disaster!! The boat has caught fire!!

You will be provided a string that lists many boat related items. If any of these items are "Fire" you must spring into action. Change any instance of "Fire" into "~~". Then return the string.
 */
fun fireFight(s: String): String = s.replace("Fire", "~~")


/**
 * You have stumbled across the divine pleasure that is owning a dog and a garden. Now time to pick up all the cr@p! :D

Given a 2D array to represent your garden, you must find and collect all of the dog cr@p - represented by '@'.

You will also be given the number of bags you have access to (bags), and the capactity of a bag (cap). If there are no bags then you can't pick anything up, so you can ignore cap.

You need to find out if you have enough capacity to collect all the cr@p and make your garden clean again.

If you do, return 'Clean', else return 'Cr@p'.

Watch out though - if your dog is out there ('D'), he gets very touchy about being watched. If he is there you need to return 'Dog!!'.

For example:

x=
[[_,_,_,_,_,_]
[_,_,_,_,@,_]
[@,_,_,_,_,_]]

bags = 2, cap = 2

return --> 'Clean'
 */

fun crap(x: Array<CharArray>, bags: Int, cap: Int): String {
    var bolsas = bags * cap
    var cacona = 0
    var perro = ""
    x.forEach { listado ->
        listado.forEach {
            if (it.toString() == "@") {
                bolsas--
                cacona++
            } else if (it.toString() == "D") {
                perro = it.toString()
            }
        }
    }
    return when {
        perro.isNotBlank() -> "Dog!!"
        bolsas >= cacona -> "Clean"
        bolsas <= cacona -> "Cr@p"
        else -> "Clean"

    }
}

/**
 * Task
Given a string str, reverse it omitting all non-alphabetic characters.

Example
For str = "krishan", the output should be "nahsirk".

For str = "ultr53o?n", the output should be "nortlu".

Input/Output
[input] string str
A string consists of lowercase latin letters, digits and symbols.

[output] a string
 */

fun reverseLetter(str: String): String = str.filter { it.isLetter() }.reversed()

/**
 * Write a function partlist that gives all the ways to divide a list (an array) of at least two elements into two non-empty parts.

Each two non empty parts will be in a pair (or an array for languages without tuples or a structin C - C: see Examples test Cases - )
Each part will be in a string
Elements of a pair must be in the same order as in the original array.

a = ["az", "toto", "picaro", "zone", "kiwi"] -->
[["az", "toto picaro zone kiwi"], ["az toto", "picaro zone kiwi"], ["az toto picaro", "zone kiwi"], ["az toto picaro zone", "kiwi"]]

 */

fun partlist(arr: Array<String>): Array<Array<String>> {
    var arrOfarr = mutableListOf<Array<String>>()
    var leftString: String? = null

    for (i in 0 until arr.size - 1) {
        leftString = when (i) {
            0 -> arr[i]
            else -> "$leftString ${arr[i]}"
        }
        var finalList = mutableListOf<String>()

        var rightString = arr.slice(i + 1 until arr.size).joinToString(" ")
        finalList.add(leftString)
        finalList.add(rightString)

        arrOfarr.add(finalList.toTypedArray())


    }

    return arrOfarr.toTypedArray()
}

/**
 * Shortest Word

 * Simple, given a string of words, return the length of the shortest word(s).

String will never be empty and you do not need to account for different data types.
 */

fun findShort(s: String): Int {
    val listWords = s.split(" ")
    var shortestWord = listWords[0].length

    listWords.forEach { word: String ->
        /* shortestWord =  if (word.length<shortestWord) word.length else shortestWord */
        shortestWord = when {
            word.length < shortestWord -> word.length
            else -> shortestWord
        }
    }
    return shortestWord
}

/* clever ==> fun findShort(s: String): Int = s.split(" ").minOf{it.length}*/

/**
 *
The Baby Years I - Baby Counting
 * You've had a baby.

Well done. Nice isn't it? Life destroying... but in a good way.

Part of your new routine is lying awake at night worrying that you've either lost the baby... or that you have more than 1!

Given a string of words (x), you need to calculate how many babies are in it.
To count as a baby you must have all of the letters in baby ('b', 'a', 'b', 'y').
That counts as 1. They do not need to be in order in the string. Upper and lower case letters count.
 */


fun babyCount(x: String): Int? {
    /*val allLetters = x.filter { it.toString() == "b" || it.toString() == "y" || it.toString() == "a" }*/
    val b = x.filter { it.toString().toLowerCase() == "b" }
    var bCount = x.count { it.toString().toLowerCase() == "b" }
    var a = x.count { it.toString().toLowerCase() == "a" }
    var y = x.count { it.toString().toLowerCase() == "y" }
    var babies: Int = 0
    if (bCount > 0) {
        for (i in b) {
            if (bCount >= 2 && a >= 1 && y >= 1) {
                babies += 1

                bCount -= 2
                a--
                y--

            } else {
                break
            }
        }

    }
    println(x)
    return if (babies > 0) babies else null
}

/**
 * Take 2 strings s1 and s2 including only letters from ato z. Return a new sorted string, the longest possible, containing distinct letters - each taken only once - coming from s1 or s2.

Examples:
a = "xyaabbbccccdefww"
b = "xxxxyyyyabklmopq"
longest(a, b) -> "abcdefklmopqwxy"

a = "abcdefghijklmnopqrstuvwxyz"
longest(a, a) -> "abcdefghijklmnopqrstuvwxyz"
 */

fun longest(s1: String, s2: String): String = (s1 + s2).toSet().toCharArray().sorted().joinToString("")

//clever ==> (s1 + s2).toSortedSet().joinToString("")


/**
 * You will be given an array and a limit value. You must check that all values in the array are below or equal to the limit value. If they are, return true. Else, return false.

You can assume all values in the array are numbers.
 */

fun smallEnough(a: IntArray, limit: Int): Boolean = a.none { it > limit }

/**
 * Cat and Mouse - Easy Version
 *
 * You will be given a string (x) featuring a cat 'C' and a mouse 'm'. The rest of the string will be made up of '.'.

You need to find out if the cat can catch the mouse from it's current position. The cat can jump over three characters. So:

C.....m returns 'Escaped!' <-- more than three characters between

C...m returns 'Caught!' <-- as there are three characters between the two, the cat can jump.
 */


fun catMouse(s: String): String {
    val puntitos = s.count { it.toString() == "." }

    return when {
        puntitos <= 3 -> "Caught!"
        else -> "Escaped!"
    }
}

//clever ==> fun catMouse(s: String) = if (s.length <= 5) "Caught!" else "Escaped!"

/**Find all pairs

 * You are given array of integers, your task will be to count all pairs in that array and return their count.

Notes:

Array can be empty or contain only one value; in this case return 0
If there are more pairs of a certain number, count each pair only once. E.g.: for [0, 0, 0, 0] the return value is 2 (= 2 pairs of 0s)
Random tests: maximum array length is 1000, range of values in array is between 0 and 1000
Examples
[1, 2, 5, 6, 5, 2]  -->  2
...because there are 2 pairs: 2 and 5

[1, 2, 2, 20, 6, 20, 2, 6, 2]  -->  4

https://www.codewars.com/kata/5c55ad8c9d76d41a62b4ede3/train/kotlin
 */

fun duplicates(array: IntArray): Int {
    val frequency = array.toList().groupingBy { it }.eachCount().filterValues { it > 1 }

    return 0 // Make the magic happen
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


/**
 * Mumbling
 * This time no story, no theory. The examples below show you how to write function accum:

Examples:
accum("abcd") -> "A-Bb-Ccc-Dddd"
accum("RqaEzty") -> "R-Qq-Aaa-Eeee-Zzzzz-Tttttt-Yyyyyyy"
accum("cwAt") -> "C-Ww-Aaa-Tttt"
The parameter of accum is a string which includes only letters from a..z and A..Z.

https://www.codewars.com/kata/5667e8f4e3f572a8f2000039/train/kotlin
 */

fun accum(s: String): String {
    val m = mutableListOf<String>()

    s.map { it.toString().toLowerCase() }.forEachIndexed { index, s ->
        m.add(s.repeat(index + 1).capitalize())
    }

    return m.joinToString("-")
}

// shorter ==> fun accum(s:String):String = s.mapIndexed { index, char -> char.toUpperCase() + char.toString().toLowerCase().repeat(index) }.joinToString("-")

/** Maximum Length Difference

 *
 * You are given two arrays a1 and a2 of strings. Each string is composed with letters from a to z. Let x be any string in the first array and y be any string in the second array.

Find max(abs(length(x) − length(y)))

If a1 and/or a2 are empty return -1 in each language except in Haskell (F#) where you will return Nothing (None).

Example:
a1 = ["hoqq", "bbllkw", "oox", "ejjuyyy", "plmiis", "xxxzgpsssa", "xxwwkktt", "znnnnfqknaz", "qqquuhii", "dvvvwz"]
a2 = ["cccooommaaqqoxii", "gggqaffhhh", "tttoowwwmmww"]
mxdiflg(a1, a2) --> 13
Bash note:
input : 2 strings with substrings separated by ,
output: number as a string
 */

fun mxdiflg(a1:Array<String>, a2:Array<String>):Int {
    var l1 =a1.maxOf {it.length}
    var l2 =a2.maxOf {it.length}

println(l1-l2)

    return -1
}

/**
 * Rotate for a Max
 * Let us begin with an example:

Take a number: 56789. Rotate left, you get 67895.

Keep the first digit in place and rotate left the other digits: 68957.

Keep the first two digits in place and rotate the other ones: 68579.

Keep the first three digits and rotate left the rest: 68597. Now it is over since keeping the first four it remains only one digit which rotated is itself.

You have the following sequence of numbers:

56789 -> 67895 -> 68957 -> 68579 -> 68597

and you must return the greatest: 68957.

Task
Write function max_rot(n) which given a positive integer n returns the maximum number you got doing rotations similar to the above example.

So max_rot (or maxRot or ... depending on the language) is such as:

max_rot(56789) should return 68957

max_rot(38458215) should return 85821534

https://www.codewars.com/kata/56a4872cbb65f3a610000026/train/kotlin
 */
fun maxRot(n:Long):Long {
    val l = n.toString().map { it.toString() }.toMutableList()
    val listadoResultados = mutableListOf<Long>()
    listadoResultados.add(l.joinToString("").toLong())

    for(i in 0 until l.size-1){
        l.add(l[i])
        l.removeAt(i)
        listadoResultados.add(l.joinToString("").toLong())
    }

    return listadoResultados.maxOf { it }
}

fun main(){
    maxRot(56789)
}