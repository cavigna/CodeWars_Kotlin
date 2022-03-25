import kotlin.math.pow
import kotlin.test.assertEquals

/**
 *
Lottery Ticket

Time to win the lottery!

Given a lottery ticket (ticket), represented by an array of 2-value arrays, you must find out if you've won the jackpot.

Example ticket:

[ [ 'ABC', 65 ], [ 'HGR', 74 ], [ 'BYHT', 74 ] ]
To do this, you must first count the 'mini-wins' on your ticket. Each subarray has both a string and a number within it. If the character code of any of the characters in the string matches the number, you get a mini win. Note you can only have one mini win per sub array.

Once you have counted all of your mini wins, compare that number to the other input provided (win). If your total is more than or equal to (win), return 'Winner!'. Else return 'Loser!'.

All inputs will be in the correct format. Strings on tickets are not always the same length.

https://www.codewars.com/kata/57f625992f4d53c24200070e/kotlin
 */

fun bingo(ticket: Array<Pair<String, Int>>, win: Int): String {
    var cantidad = 0

    ticket.forEach outer@{
        it.first.forEach inner@{ char ->
            if (char.code == it.second) {
                cantidad += 1
                return@outer
            }
        }
    }
    return when {
        cantidad < win -> "Loser!"
        else -> "Winner!"
    }
}

/*
Clever
fun bingo(ticket: Array<Pair<String, Int>>, win: Int) =
    if (ticket.count { it.second.toChar() in it.first } >= win) "Winner!" else "Loser!"

fun bingo(ticket: Array<Pair<String, Int>>, win: Int) =
if (ticket.count { pair -> pair.first.any { it.toInt() == pair.second } } >= win) "Winner!" else "Loser!"

 */

/**
 * Encrypt this!

 * Description:
Encrypt this!

You want to create secret messages which can be deciphered by the Decipher this! kata. Here are the conditions:

Your message is a string containing space separated words.
You need to encrypt each word in the message using the following rules:
The first letter must be converted to its ASCII code.
The second letter must be switched with the last letter
Keepin' it simple: There are no special characters in the input.
Examples:
encryptThis "Hello" == "72olle"
encryptThis "good" == "103doo"
encryptThis "hello world" == "104olle 119drlo"
 */

fun encryptThis(text: String): String {
    val listaString = text.split(" ").toMutableList()

    listaString.forEachIndexed { index, str ->
        if (str.length > 1) {
            var charArray = str.toCharArray()
            var charArrayCOpy = str.toCharArray().copyOf()

            charArrayCOpy[1] = charArray[charArray.lastIndex]
            charArrayCOpy[charArray.lastIndex] = charArray[1]
            var word = charArrayCOpy.joinToString("")


            listaString[index] = word.replaceFirstChar { it.code.toString() }
        } else {
            listaString[index] = str.replaceFirstChar { it.code.toString() }
        }
    }
    println(listaString.joinToString(" "))
    return listaString.joinToString(" ")


}
// shorter ==> fun encryptThis(text:String): String{
//    return text.split(" ").map { it.first().toInt().toString() + it.drop(2).takeLast(1) + it.drop(2).dropLast(1) + it.drop(1).take(1) }.joinToString(" ")
//}

/** Meeting
 *
 *John has invited some friends. His list is:

s = "Fred:Corwill;Wilfred:Corwill;Barney:Tornbull;Betty:Tornbull;Bjon:Tornbull;Raphael:Corwill;Alfred:Corwill";
Could you make a program that

makes this string uppercase
gives it sorted in alphabetical order by last name.
When the last names are the same, sort them by first name. Last name and first name of a guest come in the result between parentheses separated by a comma.

So the result of function meeting(s) will be:

"(CORWILL, ALFRED)(CORWILL, FRED)(CORWILL, RAPHAEL)(CORWILL, WILFRED)(TORNBULL, BARNEY)(TORNBULL, BETTY)(TORNBULL, BJON)"
It can happen that in two distinct families with the same family name two people have the same first name too
 *
 *https://www.codewars.com/kata/59df2f8f08c6cec835000012/train/kotlin
 */

fun meeting(s: String): String {
    val listadoNombres = s.split(";").map { it.toUpperCase() }.toMutableList()
    var listaOrdenada = mutableListOf<String>()

    listadoNombres.forEach { s ->
        var temp = s.split(":")
        var pal = s.split(":").toMutableList()
        pal[0] = temp[1]
        pal[1] = temp[0]
        listaOrdenada.add("(${pal.joinToString(", ")})")

    }
    return listaOrdenada.map { it }.sortedBy { it }.joinToString("")
}

//fun meeting(s: String) = s.toUpperCase()
//        .split(";")
//        .map { it.split(":") }
//        .map { "(${it[1]}, ${it[0]})" }
//        .sorted()
//        .joinToString("")


/**
 *A Rule of Divisibility by 13

 *From Wikipedia:

"A divisibility rule is a shorthand way of determining whether a given integer is divisible by a fixed divisor without performing the division, usually by examining its digits."

When you divide the successive powers of 10 by 13 you get the following remainders of the integer divisions:

1, 10, 9, 12, 3, 4 because:

10 ^ 0 ->  1 (mod 13)
10 ^ 1 -> 10 (mod 13)
10 ^ 2 ->  9 (mod 13)
10 ^ 3 -> 12 (mod 13)
10 ^ 4 ->  3 (mod 13)
10 ^ 5 ->  4 (mod 13)
(For "mod" you can see: https://en.wikipedia.org/wiki/Modulo_operation)

Then the whole pattern repeats. Hence the following method:

Multiply

the right most digit of the number with the left most number in the sequence shown above,
the second right most digit with the second left most digit of the number in the sequence.
The cycle goes on and you sum all these products. Repeat this process until the sequence of sums is stationary.

Example:
What is the remainder when 1234567 is divided by 13?

7      6     5      4     3     2     1  (digits of 1234567 from the right)
×      ×     ×      ×     ×     ×     ×  (multiplication)
1     10     9     12     3     4     1  (the repeating sequence)
Therefore following the method we get:

7×1 + 6×10 + 5×9 + 4×12 + 3×3 + 2×4 + 1×1 = 178

We repeat the process with the number 178:

8x1 + 7x10 + 1x9 = 87

and again with 87:

7x1 + 8x10 = 87

From now on the sequence is stationary (we always get 87) and the remainder of 1234567 by 13 is the same as the remainder of 87 by 13 ( i.e 9).

Task:
Call thirt the function which processes this sequence of operations on an integer n (>=0). thirt will return the stationary number.

thirt(1234567) calculates 178, then 87, then 87 and returns 87.

thirt(321) calculates 48, 48 and returns 48
 *
 * https://www.codewars.com/kata/564057bc348c7200bd0000ff/train/kotlin
 */

//import kotlin.math.pow
fun thirt(n: Long): Long {
    while (n >= 100) {
        val listReversed = n.toString().reversed().map { it.toString().toLong() }.toMutableList()
        listReversed.forEachIndexed { index, l ->
            val exponente = (10.0.pow(index.toDouble()) % 13).toInt()
            listReversed[index] = l * exponente
        }
        return thirt(listReversed.sum())
    }
    return n
}
//fun thirt(n: Long): Long {
//    val new = n.toString().reversed().map { it - '0' }.mapIndexed { i, c -> rem[i% rem.size] * c }.sum().toLong()
//    return if (new == n) n
//    else thirt(new)
//}

/**
 *
 *You are going to be given an array of integers. Your job is to take that array and find an index N where the sum of the integers to the left of N is equal to the sum of the integers to the right of N. If there is no index that would make this happen, return -1.

For example:

Let's say you are given the array {1,2,3,4,3,2,1}:
Your function will return the index 3, because at the 3rd position of the array, the sum of left side of the index ({1,2,3}) and the sum of the right side of the index ({3,2,1}) both equal 6.

Let's look at another one.
You are given the array {1,100,50,-51,1,1}:
Your function will return the index 1, because at the 1st position of the array, the sum of left side of the index ({1}) and the sum of the right side of the index ({50,-51,1,1}) both equal 1.

Last one:
You are given the array {20,10,-80,10,10,15,35}
At index 0 the left side is {}
The right side is {10,-80,10,10,15,35}
They both are equal to 0 when added. (Empty arrays are equal to 0 in this problem)
Index 0 is the place where the left side and right side are equal.

Note: Please remember that in most programming/scripting languages the index of an array starts at 0.

Input:
An integer array of length 0 < arr < 1000. The numbers in the array can be any integer positive or negative.

Output:
The lowest index N where the side to the left of N is equal to the side to the right of N. If you do not find an index that fits these rules, then you will return -1.

Note:
If you are given an array with multiple answers, return the lowest correct index.
 *
 * https://www.codewars.com/kata/5679aa472b8f57fb8c000047/train/kotlin
 */

fun findEvenIndex(arr: IntArray): Int {

    arr.forEachIndexed { index, i ->
        var a = arr.take(index + 1)
        // println(a)
    }
    var right: Int
    var left: Int

    for (i in 1 until arr.size) {
        right = arr.take(i).sum()
        var r = arr.take(i)
        left = arr.takeLast(i).sum()
        println(r)
    }
    return 0
}

/**
 *
 * You are given a small extract of a catalog:

s = "<prod><name>drill</name><prx>99</prx><qty>5</qty></prod>

<prod><name>hammer</name><prx>10</prx><qty>50</qty></prod>

<prod><name>screwdriver</name><prx>5</prx><qty>51</qty></prod>

<prod><name>table saw</name><prx>1099.99</prx><qty>5</qty></prod>

<prod><name>saw</name><prx>9</prx><qty>10</qty></prod>

...
(prx stands for price, qty for quantity) and an article i.e "saw".

The function catalog(s, "saw") returns the line(s) corresponding to the article with $ before the prices:

"table saw > prx: $1099.99 qty: 5\nsaw > prx: $9 qty: 10\n..."
If the article is not in the catalog return "Nothing".

There is a blank line between two lines of the catalog.
The same article may appear more than once. If that happens return all the lines concerned by the article (in the same order as in the catalog).
The line separator of results may depend on the language \nor \r\n. In Pascal \n is replaced by LineEnding.
 *
 * https://www.codewars.com/kata/59d9d8cb27ee005972000045/train/kotlin
 *
 */

fun catalog(s: String, article: String): String {
    val finder = s.split("<prod>", "</prod>")
        .filter { it.isNotBlank() }
        .filter { product -> product.contains(article) }

    var listadoReturn = mutableListOf<String>()

    finder.forEach { product ->
        var productoAgregar = product.replace("<name>", "")
            .replace("</name>", " > ")
            .replace("<prx>", "prx: ${'$'}")
            .replace("</prx>", "")
            .replace("<qty>", " qty: ")
            .replace("</qty>", "")

        listadoReturn.add(productoAgregar)
    }

    return if (finder.isNotEmpty()) listadoReturn.joinToString("\n") else "Nothing"
}
/* Shorter
fun catalog(s: String, article: String): String = s.split("\n\n")
            .filter { it.contains(article) }
            .joinToString("\n") {
                it.replace(Regex("""<prod><name>(.*?)</name><prx>(.*?)</prx><qty>(.*?)</qty></prod>""")) { result ->
                    "${result.groupValues[1]} > prx: $${result.groupValues[2]} qty: ${result.groupValues[3]}"
                }}
            .ifEmpty { "Nothing" }
 */

/**
 *Mexican Wave
troduction
The wave (known as the Mexican wave in the English-speaking world outside North America) is an example of metachronal rhythm achieved in a packed stadium when successive groups of spectators briefly stand, yell, and raise their arms. Immediately upon stretching to full height, the spectator returns to the usual seated position.

The result is a wave of standing spectators that travels through the crowd, even though individual spectators never move
away from their seats. In many large arenas the crowd is seated in a contiguous circuit all the way around the sport
field, and so the wave is able to travel continuously around the arena; in discontiguous seating arrangements, the wave
can instead reflect back and forth through the crowd. When the gap in seating is narrow, the wave can sometimes pass
through it. Usually only one wave crest will be present at any given time in an arena, although simultaneous,
counter-rotating waves have been produced. (Source Wikipedia)
Task
In this simple Kata your task is to create a function that turns a string into a Mexican Wave. You will be passed
a string and you must return that string in an array where an uppercase letter is a person standing up.
Rules
1.  The input string will always be lower case but maybe empty.

2.  If the character in the string is whitespace then pass over it as if it was an empty seat
Example
wave("hello") => []string{"Hello", "hEllo", "heLlo", "helLo", "hellO"}
wave("a       b    ") ==> ["A       b    ", "a       B    "]
 *
 *
 * https://www.codewars.com/kata/58f5c63f1e26ecda7e000029/train/kotlin
 */


fun wave(str: String): List<String> = mutableListOf<String>().apply {

    str.forEachIndexed { index, c ->
        if (c != ' ') {
            var charToReplace = c.toUpperCase().toString()
            this.add(str.replaceRange(index, index + 1, charToReplace))
        }
    }
}

//shorter ==> fun wave(str: String) = str.indices.map { str.take(it) + str.drop(it).capitalize() }.filter { it != str }

/** Consecutive strings
 * You are given an array(list) strarr of strings and an integer k. Your task is to return the first longest string consisting of k consecutive strings taken in the array.

Examples:
strarr = ["tree", "foling", "trashy", "blue", "abcdef", "uvwxyz"], k = 2

Concatenate the consecutive strings of strarr by 2, we get:

treefoling   (length 10)  concatenation of strarr[0] and strarr[1]
folingtrashy ("      12)  concatenation of strarr[1] and strarr[2]
trashyblue   ("      10)  concatenation of strarr[2] and strarr[3]
blueabcdef   ("      10)  concatenation of strarr[3] and strarr[4]
abcdefuvwxyz ("      12)  concatenation of strarr[4] and strarr[5]

Two strings are the longest: "folingtrashy" and "abcdefuvwxyz".
The first that came is "folingtrashy" so
longest_consec(strarr, 2) should return "folingtrashy".

In the same way:
longest_consec(["zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"], 2) --> "abigailtheta"
n being the length of the string array, if n = 0 or k > n or k <= 0 return "" (return Nothing in Elm).

Note
consecutive strings : follow one after another without an interruption
https://www.codewars.com/kata/56a5d994ac971f1ac500003e/train/kotlin
 *
 */


fun longestConsec(strarr: Array<String>, k: Int): String {
    return if (k < 1 || strarr.isEmpty() || k > strarr.size) {
        ""
    } else if (k == 1) {
        strarr.toList().first { it -> it.length == strarr.maxOf { it.length } }

    } else {
        val listadoPalabras = mutableListOf<String>().apply {
            for (i in 0 until strarr.size - k + 1) {
                this.add(strarr.toList().slice(i until i + k).joinToString(""))
            }
        }
        listadoPalabras.filter { it -> it.length == listadoPalabras.maxOf { it.length } }.first()
    }
}
/* Other Solutions
fun longestConsec(strarr:Array<String>, k:Int): String {
    if(k > strarr.size || k <= 0) return ""

    return strarr.toList()
        .windowed(k)
        .map { it.fold("", String::plus) }
        .maxBy { it.length } ?: ""
}
 */


/**
 * Tricky Kotlin #0: extension constructor
 *
 *This is a series of Kotlin tricks.
 * As we all know, Kotlin supports something called extension methods, which means you can add some methods to some existing classes.
 * In this Kata, you should make some extension constructor. For example, you can add a constructor to kotlin.Int that accepts a String and parse the string to int.
 * You should add such extension constructor to kotlin.Int, kotlin.Long, kotlin.Double.
 * [https://www.codewars.com/kata/59b336de5fb1334711000067/train/kotlin]
 *
 *
 *
 *     val r = Random(System.currentTimeMillis())

println(100.Int("9"))
//println(kotlin.Int("100"))
(0..100).forEach { r.nextInt().let { assertEquals(it, kotlin.Int(it.toString())) } }
//(0..100).forEach { r.nextInt().let { assertEquals(it, kotlin.Int(it.toString()))


//class kotlin(){
//    fun Int(s: String) = s.toInt()
//}
//class Int(){
//    constructor(s:String): this(s)
//}

/*
get() = s.toInt()
set() = s.toInt()
val n = kotlin.Int.Companion

s.toInt()
return (this to s.toInt()).second
*/
fun kotlin.Int.Int2(s: String): Int {


//this.toInt() = s.toInt()

return this

}

fun Int.Int(s: String) =  this.apply {  (this to s.toInt()).second }


fun Long(s: String) = s.toLong()
fun Double(s: String) = s.toDouble()
 */

/**
 *
 * Decompose double strand DNA into 6 reading frames
 *
 * In a double strand DNA you find 3 more Reading frames than the single strand DNA reading frames base on the reverse complement-strand.

Input
Given a DNA sequence like the following

AGGTGACACCGCAAGCCTTATATTAGC
Processing
In the reverse complement the following transformations are made

A-->T
G-->C
T-->A
C-->G
Due to the splicing of DNA strands and the fixed reading direction of a nucleotide strand, the reverse complement gets read from right to left.

DNA                     AGGTGACACCGCAAGCCTTATATTAGC
Reverse complement:     TCCACTGTGGCGTTCGGAATATAATCG
reversed reverse frame: GCTAATATAAGGCTTGCGGTGTCACCT
Output
You'll have to output:

Frame 1: AGG TGA CAC CGC AAG CCT TAT ATT AGC
Frame 2: A GGT GAC ACC GCA AGC CTT ATA TTA GC
Frame 3: AG GTG ACA CCG CAA GCC TTA TAT TAG C

Reverse Frame 1: GCT AAT ATA AGG CTT GCG GTG TCA CCT
Reverse Frame 2: G CTA ATA TAA GGC TTG CGG TGT CAC CT
Reverse Frame 3: GC TAA TAT AAG GCT TGC GGT GTC ACC T
Instructions on how to output the first 3 frames are on a previous simpler kata Decompose single strand DNA into 3 reading frames

 */

/**
 * Frame 1: AGG TGA CAC CGC AAG CCT TAT ATT AGC
Frame 2: A GGT GAC ACC GCA AGC CTT ATA TTA GC
Frame 3: AG GTG ACA CCG CAA GCC TTA TAT TAG C
 */

fun decomposeDoubleStrand(doubleStrand: String = "AGGTGACACCGCAAGCCTTATATTAGC"): String {
    val frame1 = doubleStrand.chunked(3).joinToString(" ")

    val frame2 = doubleStrand.takeLast(doubleStrand.length - 1)
        .chunked(3).toMutableList().also {
            it.add(0, doubleStrand.take(1))
        }.joinToString(" ")

    val frame3 = doubleStrand.takeLast(doubleStrand.length - 2)
        .chunked(3).toMutableList().also {
            it.add(0, doubleStrand.take(2))
        }.joinToString(" ")

    val reverse = doubleStrand.map { it.toString() }.toMutableList().also {
        it.forEachIndexed { index, s ->
            when (s) {
                "A" -> it[index] = "T"
                "G" -> it[index] = "C"
                "T" -> it[index] = "A"
                "C" -> it[index] = "G"
            }
        }
    }.joinToString("").reversed()

    val reverseFrame1 = reverse.chunked(3).joinToString(" ")

    val reverseFrame2 = reverse.takeLast(reverse.length - 1)
        .chunked(3).toMutableList().also {
            it.add(0, reverse.take(1))
        }.joinToString(" ")

    val reverseFrame3 = reverse.takeLast(reverse.length - 2)
        .chunked(3).toMutableList().also {
            it.add(0, reverse.take(2))
        }.joinToString(" ")

    return "Frame 1: $frame1\nFrame 2: $frame2\nFrame 3: $frame3\n\nReverse Frame 1: " +
            "$reverseFrame1\nReverse Frame 2: $reverseFrame2\nReverse Frame 3: $reverseFrame3"
}

fun reversedFunctionNotUsed(doubleStrandS: String): String = doubleStrandS
    .map { it.toString() }
    .toMutableList()
    .also {
        it.forEachIndexed { index, s ->
            when (s) {
                "A" -> it[index] = "T"
                "G" -> it[index] = "C"
                "T" -> it[index] = "A"
                "C" -> it[index] = "G"
            }
        }
    }.joinToString("")

/**
 * Highest Scoring Word
 * Given a string of words, you need to find the highest scoring word.

Each letter of a word scores points according to its position in the alphabet: a = 1, b = 2, c = 3 etc.

You need to return the highest scoring word as a string.

If two words score the same, return the word that appears earliest in the original string.

All letters will be lowercase and all inputs will be valid
https://www.codewars.com/kata/57eb8fcdf670e99d9b000272/train/kotlin
 */

fun highFunction(str: String): String =

    mutableMapOf<String, Int>().also {
        str.split(" ").forEach {string ->
            it[string] = string.sumOf { c->
                c.code -96
            }
        }
    }.maxByOrNull { it.value }?.key ?: ""


/**
 * fun high(str: String): String {
return str.split(' ').maxBy{ it.sumBy{ it - 'a' + 1 } }!!
}
 */

/**
 * Playing with digits
Some numbers have funny properties. For example:

89 --> 8¹ + 9² = 89 * 1

695 --> 6² + 9³ + 5⁴= 1390 = 695 * 2

46288 --> 4³ + 6⁴+ 2⁵ + 8⁶ + 8⁷ = 2360688 = 46288 * 51

Given a positive integer n written as abcd... (a, b, c, d... being digits) and a positive integer p

we want to find a positive integer k, if it exists, such that the sum of the digits of n taken to the successive powers of p is equal to k * n.
In other words:

Is there an integer k such as : (a ^ p + b ^ (p+1) + c ^(p+2) + d ^ (p+3) + ...) = n * k

If it is the case we will return k, if not return -1.

Note: n and p will always be given as strictly positive integers.

digPow(89, 1) should return 1 since 8¹ + 9² = 89 = 89 * 1
digPow(92, 1) should return -1 since there is no k such as 9¹ + 2² equals 92 * k
digPow(695, 2) should return 2 since 6² + 9³ + 5⁴= 1390 = 695 * 2
digPow(46288, 3) should return 51 since 4³ + 6⁴+ 2⁵ + 8⁶ + 8⁷ = 2360688 = 46288 * 51
 */


fun digPow(n: Int, p: Int): Int {
    val sumaLista = n.toString().map { it.toString().toInt() }.toMutableList().also {
        it.forEachIndexed { index, i ->
            it[index] = i.toDouble().pow(index+p).toInt()
        }
    }.sum()

    return when{
        sumaLista.rem(n) ==0 -> sumaLista/n
        else -> -1
    }

}
// Oneliner ==> fun digPow(n: Int, p: Int) = n.toString().mapIndexed { i, c -> c.toString().toDouble().pow(p + i).toInt() }.sum().let { if (it % n == 0) it / n else -1 }


/**Alphabet war - airstrike - letters massacre
 *
 * Task
Write a function that accepts fight string consists of only small letters and * which means a bomb drop place. Return who wins the fight after bombs are exploded. When the left side wins return Left side wins!, when the right side wins return Right side wins!, in other case return Let's fight again!.

The left side letters and their power:

w - 4
p - 3
b - 2
s - 1
The right side letters and their power:

m - 4
q - 3
d - 2
z - 1
The other letters don't have power and are only victims.
The * bombs kill the adjacent letters ( i.e. aa*aa => a___a, **aa** => ______ );

Example
AlphabetWar("s*zz");           //=> Right side wins!
AlphabetWar("*zd*qm*wp*bs*"); //=> Let's fight again!
AlphabetWar("zzzz*s*");       //=> Right side wins!
AlphabetWar("www*www****z");  //=> Left side wins!
https://www.codewars.com/kata/5938f5b606c3033f4700015a
 */
fun alphabetWar(fight: String): String {
    val reg = Regex("(?<=[*])|(?=[*])")

    val lista = mutableListOf<String>().also {
        for (i in fight.indices){
            if (fight.getOrNull(i-1) !='*' && fight.getOrNull(i + 1) != '*'){
                it.add(fight[i].toString())
            }
        }
    }

    val leftSide = "sbpw"
    val rightSide = "zdqm"

    val countRight = lista.filter { it in rightSide }.sumOf { c -> rightSide.indexOf(c)+1  }
    val countLeft = lista.filter { it in leftSide }.sumOf { c -> leftSide.indexOf(c)+1  }

    return when {
        countLeft > countRight -> "Left side wins!"
        countRight> countLeft -> "Right side wins!"
        else -> "Let's fight again!"
    }
}

fun main() {
    alphabetWar("*zd*qm*wp*bs*")
    alphabetWar("sz**z**zs")
    alphabetWar("**z**")

    assertEquals("Right side wins!", alphabetWar("z"))
    assertEquals("Let's fight again!", alphabetWar("****"))
    assertEquals("Let's fight again!", alphabetWar("z*dq*mw*pb*s"))
    assertEquals("Let's fight again!", alphabetWar("zdqmwpbs"))
    assertEquals("Right side wins!", alphabetWar("zz*zzs"))
    assertEquals("Left side wins!", alphabetWar("sz**z**zs"))
    assertEquals("Left side wins!", alphabetWar("z*z*z*zs"))
    assertEquals("Left side wins!", alphabetWar("*wwwwww*z*"))
}

//    val listados = fight.map { it.toString() }.toMutableList()
//    listados.forEach {
//        println(it.takeWhile {c-> c != '*' })
//    }
//    println(fight.takeWhile { it.toString()!="*" })
//    println(fight.takeLastWhile { it.toString()!="*" })
//    println(fight.groupBy { it == '*' })
