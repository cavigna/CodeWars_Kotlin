import java.util.*
import kotlin.math.ceil
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

fun mxdiflg(a1: Array<String>, a2: Array<String>): Int {
    var l1 = a1.maxOf { it.length }
    var l2 = a2.maxOf { it.length }

    println(l1 - l2)

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
fun maxRot(n: Long): Long {
    val l = n.toString().map { it.toString() }.toMutableList()
    val listadoResultados = mutableListOf<Long>()
    listadoResultados.add(l.joinToString("").toLong())

    for (i in 0 until l.size - 1) {
        l.add(l[i])
        l.removeAt(i)
        listadoResultados.add(l.joinToString("").toLong())
    }

    return listadoResultados.maxOf { it }
}


/**
 *Hide password from jdbc url
 * We have to create a function that receives a connection string with password included and you have to mask the password i.e. change password by asterisks.

Preconditions:

non empty valid url
password always next to string section password=
assume password will not contain ampersand sign for sake of simplicity
to make it more real it has non ASCII characters
"password=" and "user" will occur only once
empty passwords are not validated but best solutions take empty passwords into account
https://www.codewars.com/kata/5a726f16373c2ee6c60000db/train/kotlin
 *
 */

fun hidePasswordFromConnection(urlString: String): String {
    val pass = urlString.substringAfter("password=").substringBefore("&")

    return when {
        pass.isNotBlank() -> {
            val asterisco = "*".repeat(pass.length)
            urlString.replace(pass, asterisco)
        }
        else -> urlString
    }
}


/**
 *Predict your age!

 *My grandfather always predicted how old people would get, and right before he passed away he revealed his secret!

In honor of my grandfather's memory we will write a function using his formula!

Take a list of ages when each of your great-grandparent died.
Multiply each number by itself.
Add them all together.
Take the square root of the result.
Divide by two.
Example
predictAge(65, 60, 75, 55, 60, 63, 64, 45) === 86
Note: the result should be rounded down to the nearest integer.
 *
 */

//shorter ==> fun predictAge(vararg age:Int) = (Math.sqrt((age.map{it*it}.sum().toDouble()))/2).toInt()

fun predictAge(age1: Int, age2: Int, age3: Int, age4: Int, age5: Int, age6: Int, age7: Int, age8: Int): Int {
    val edades = mutableListOf(age1, age2, age3, age4, age5, age6, age7, age8)
    edades.forEachIndexed { index, i ->
        edades[index] *= i
    }
    println((sqrt(edades.sum().toDouble()) / 2).toInt())
    return (sqrt(edades.sum().toDouble()) / 2).toInt()
}

/**
 * Simple Fun #384: Is Turing's Equation?
 * https://www.codewars.com/kata/5a1e6323ffe75f71ae000026/train/kotlin
 * Story
Joe Stoy, in his book "Denotational Semantics", tells following story:

The decision which way round the digits run is, of course, mathematically trivial.
Indeed, one early British computer had numbers running from right to left (because
the spot on an oscilloscope tube runs from left to right, but in serial logic the
least significant digits are dealt with first).

Turing used to mystify audiences at public lectures when, quite by accident, he would
slip into this mode even for decimal arithmetic, and write things like 73+42=16.

The next version of the machine was made more conventional simply by crossing the
x-deflection wires: this, however, worried the engineers, whose waveforms were all
backwards. That problem was in turn solved by providing a little window so that the
engineers(who tended to be behind the computer anyway) could view the oscilloscope
screen from the back.

[C. Strachey - private communication.]
You will play the role of the audience and judge on the truth value of Turing's equations.

Task
You are given a string s. It's an equation such as "a+b=c", where a, b, c are numbers made up of the digits 0 to 9. This includes possible leading or trailing zeros. The equations will not contain any spaces.

Your task is to determine whether s is a valid Turing equation. Return true or false, respectively, in Turing's interpretation, i.e. the numbers being read backwards.

Still struggling to understand the task? Look at the following examples ;-)

Examples
For s = "73+42=16", the output should be true.

73 -> 37
42 -> 24
16 -> 61
37+24==61
For s = "5+8=13", the output should be false.

5 -> 5
8 -> 8
13 -> 31
5+8!=31
For s = "10+20=30", the output should be true.

10 -> 01 -> 1
20 -> 02 -> 2
30 -> 03 -> 3
1+2==3
Note
All the numbers a,b,c no more than 10 digits, excluding leading zeros(read backwards)

s contains only digits, "+" and "=", "-","*" or "/" will not appear in the string.

Happy Coding ^_^
 */

// shorter ==> fun isTuringEquation(s: String): Boolean = s.split('+', '=').map { it.reversed().toInt() }.let { it[0] + it[1] == it[2] }

fun isTuringEquation(s: String): Boolean {

    val primerNumero = s.substringBefore("+").reversed().toLong()
    val segundoNumero = s.substringAfter("+").substringBefore("=").reversed().toLong()
    val resultado = s.substringAfter("=").reversed().toLong()

    return (primerNumero + segundoNumero) == resultado
}

/**  Alphabetical Sequence
 *
 *
 * In this kata you will be given a random string of letters and tasked with returning them as a string of comma-separated sequences sorted alphabetically, with each sequence starting with an uppercase character followed by n-1 lowercase characters, where n is the letter's alphabet position 1-26.

Example
alphaSeq("ZpglnRxqenU") -> "Eeeee,Ggggggg,Llllllllllll,Nnnnnnnnnnnnnn,Nnnnnnnnnnnnnn,Pppppppppppppppp,Qqqqqqqqqqqqqqqqq,Rrrrrrrrrrrrrrrrrr,Uuuuuuuuuuuuuuuuuuuuu,Xxxxxxxxxxxxxxxxxxxxxxxx,Zzzzzzzzzzzzzzzzzzzzzzzzzz"
Technical Details
The string will include only letters.
The first letter of each sequence is uppercase followed by n-1 lowercase.
Each sequence is separated with a comma.
Return value needs to be a string.

https://www.codewars.com/kata/5bd00c99dbc73908bb00057a/train/kotlin


 */

fun alphaSeq(str: String): String {
    val listaString = mutableListOf<String>()
    str.lowercase(Locale.getDefault()).forEach {
        listaString.add(it.toString().repeat(it.code - 97 + 1).capitalize())
        listaString.sort()
    }
    return listaString.joinToString(",")
}
// shorter ==>fun alphaSeq(str: String) = str.toLowerCase().toCharArray().sorted().joinToString(",") { it.toString().repeat(it.toInt() - 96).capitalize() }

/**
 * Given an array of strings, reverse them and their order in such way that their length stays the same as the length of the original inputs.

Example:
Input:  {"I", "like", "big", "butts", "and", "I", "cannot", "lie!"}
Output: {"!", "eilt", "onn", "acIdn", "ast", "t", "ubgibe", "kilI"}

// No Kotlin coder would ever use Arrays if they could avoid it,
// even in a challenge called "Ultimate Array Reverser."
// To paraphrase a man in a cave,
// "It's dangerous to go alone, take this [List]"

https://www.codewars.com/kata/5c3433a4d828182e420f4197/train/kotlin
 */

fun reverse(a: List<String>): List<String> {
    var alvere = a.joinToString("").reversed()
    val dadoVuelta = mutableListOf<String>()

    a.forEach {
        dadoVuelta.add(alvere.substring(0, it.length))
        alvere = alvere.drop(it.length)
    }
    println(dadoVuelta)


    return dadoVuelta
}


/**
 * Circular List

 * Create a Circular List

A circular list is of finite size, but can infititely be asked for its previous and next elements. This is because it acts like it is joined at the ends and loops around.

For example, imagine a CircularList of [1, 2, 3, 4]. Five invocations of next() in a row should return 1, 2, 3, 4 and then 1 again. At this point, five invocations of prev() in a row should return 4, 3, 2, 1 and then 4 again.

Your CircularList is created by passing a vargargs parameter in, e.g. new CircularList(1, 2, 3). Your list constructor/init code should throw an Exception if nothing is passed in.
class CircularList<T>(vararg val elements: T) {
fun next(): T {
// your code here
}

fun prev(): T {
// your code here
}
}
https://www.codewars.com/kata/5b2e60742ae7543f9d00005d/train/kotlin

 */


class CircularList<T>(vararg val elements: T) {
    private val listIterator = elements.toList().listIterator()
    private var currentPos = 0
    private var current = elements[currentPos]

    private var anteriorPos = elements.size - 1
    private var anterior = elements[elements.size - 1]

    fun next(): T {

        if (currentPos >= elements.size - 1) {
            currentPos = 0
            current = elements[currentPos]

            return current
        } else {
            currentPos += 1
            return elements[currentPos - 1]

        }
    }

    fun prev(): T {
//        if (anteriorPos == elements.size-1){
//            anteriorPos -=1
//            return elements[elements.size -1]
//        }

        if (anteriorPos <= elements.size - 1) {
            anteriorPos = if (anteriorPos > elements.size - 1) -1 else elements.size - 1
            return elements[anteriorPos]
        } else {
            anteriorPos = elements.size - 1
            anterior = elements[anteriorPos]
            return anterior
        }

/*
    val xs = CircularList<String>("one", "two", "three")
    println("${xs.next()} ==> one")
    println("${xs.next()} ==> two")
    println("${xs.next()} ==> three")
    println("${xs.next()} ==> one")
    println("${xs.prev()} ==> three")
    println("${xs.prev()} ==> two")
    println("${xs.prev()} ==> one")
    println("${xs.prev()} ==> three")


    val ys = CircularList<Int>(1, 2, 3, 4, 5)
    println("${ys.prev()}, `is`(5)")
    println("${ys.prev()}, `is`(4)")
    println("${ys.next()}, `is`(5)")
    println("${ys.next()}, `is`(1)")
    println("${ys.next()}, `is`(2)")
    println("${ys.next()}, `is`(3)")
    println("${ys.next()}, `is`(4)")
    println("${ys.prev()}, `is`(3)")
    println("${ys.prev()}, `is`(2)")
    println("${ys.next()}, `is`(3)")
    println("${ys.next()}, `is`(4)")
    println("${ys.next()}, `is`(5)")
    println("${ys.next()}, `is`(1)")
    println("${ys.next()}, `is`(2)")
    println("${ys.next()}, `is`(3)")
 */
/*
class CircularList<T>(vararg val elements: T) {
   private val listIterator = elements.toList().listIterator()
    private var current = elements[0]
    private var currentPos = 0
    private var anteriorPos = elements.size -1
    private var anterior = elements[elements.size-1]

    fun next(): T {

        if (listIterator.hasNext()) {
            current = listIterator.next()
            return current
        }else{
            return elements[0]
        }
    }

    fun prev(): T {

        if (listIterator.hasPrevious()) {
            return listIterator.previous()
        }else{
            return elements[elements.size-1]
        }
    }
}
 */
    }
}


/**
 * Opposites Attract

 * Timmy & Sarah think they are in love, but around where they live, they will only know once they pick a flower each. If one of the flowers has an even number of petals and the other has an odd number of petals it means they are in love.

Write a function that will take the number of petals of each flower and return true if they are in love and false if they aren't.

https://www.codewars.com/kata/555086d53eac039a2a000083/train/kotlin
 *
 */
fun loveFun(flowerA: Int, flowerB: Int): Boolean {
    return (flowerA % 2 == 0 && flowerB % 2 != 0) || (flowerA % 2 != 0 && flowerB % 2 == 0)
}

/**
 *
Number of People in the Bus
 * There is a bus moving in the city, and it takes and drop some people in each bus stop.

You are provided with a list (or array) of integer pairs. Elements of each pair represent number of people get into bus (The first item) and number of people get off the bus (The second item) in a bus stop.

Your task is to return number of people who are still in the bus after the last bus station (after the last array). Even though it is the last bus stop, the bus is not empty and some people are still in the bus, and they are probably sleeping there :D

Take a look on the test cases.

Please keep in mind that the test cases ensure that the number of people in the bus is always >= 0. So the return integer can't be negative.

The second value in the first integer array is 0, since the bus is empty in the first bus stop.
https://www.codewars.com/kata/5648b12ce68d9daa6b000099/train/kotlin


 */
// clever ==> fun people(busStops: Array<Pair<Int, Int>>) = busStops.sumBy { it.first - it.second }
fun people(busStops: Array<Pair<Int, Int>>): Int {
    var numberPassangers = 0
    busStops.forEach {
        numberPassangers += it.first
        numberPassangers -= it.second
    }
    return if (numberPassangers <= 0) 0 else numberPassangers
}

/** The Office I - Outed

 * Your colleagues have been looking over you shoulder. When you should have been doing your boring real job, you've been using the work computers to smash in endless hours of codewars.

In a team meeting, a terrible, awful person declares to the group that you aren't working. You're in trouble. You quickly have to gauge the feeling in the room to decide whether or not you should gather your things and leave.

Given an object (meet) containing team member names as keys, and their happiness rating out of 10 as the value, you need to assess the overall happiness rating of the group. If <= 5, return 'Get Out Now!'. Else return 'Nice Work Champ!'.

Happiness rating will be total score / number of people in the room.

Note that your boss is in the room (boss), their score is worth double it's face value (but they are still just one person!).

The Office II - Boredom Score
The Office III - Broken Photocopier
The Office IV - Find a Meeting Room
The Office V - Find a Chair
https://www.codewars.com/kata/57ecf6efc7fe13eb070000e1/train/kotlin
 */


fun outed(meet: Map<String, Int>, boss: String): String {
    var happines = 0
    meet.forEach {
        happines += when (it.key) {
            boss -> (it.value) * 2
            else -> it.value
        }
    }
    return when {
        happines / meet.size <= 5 -> "Get Out Now!"
        else -> "Nice Work Champ!"
    }
}

/**
 * Exclamation marks series #11: Replace all vowel to exclamation mark in the sentence
 * Description:
Replace all vowel to exclamation mark in the sentence. aeiouAEIOU is vowel.

Examples
replace("Hi!") === "H!!"
replace("!Hi! Hi!") === "!H!! H!!"
replace("aeiou") === "!!!!!"
replace("ABCDE") === "!BCD!"
 */

fun replace(s: String): String = s.replace("a", "!", ignoreCase = true)
    .replace("e", "!", ignoreCase = true)
    .replace("i", "!", ignoreCase = true)
    .replace("o", "!", ignoreCase = true)
    .replace("u", "!", ignoreCase = true)

/**
 * Century From Year
 *
 * Introduction
The first century spans from the year 1 up to and including the year 100, the second century - from the year 101 up to and including the year 200, etc.

Task
Given a year, return the century it is in.

Examples
1705 --> 18
1900 --> 19
1601 --> 17
2000 --> 20

https://www.codewars.com/kata/5a3fe3dde1ce0e8ed6000097/kotlin
 */

fun century(number: Int): Int = ceil((number.toDouble()/100)).toInt()

// clever?? ==> fun century(number: Int) = (number +99) / 100



fun main() {

}
