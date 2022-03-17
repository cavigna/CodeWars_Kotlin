import java.lang.Math.pow
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

fun findEvenIndex(arr:IntArray):Int {

    arr.forEachIndexed { index, i ->
        var a  = arr.take(index+1)
       // println(a)
    }
    var right: Int
    var left: Int

    for (i in 1 until arr.size){
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
    val mmmm = """"ladder > prx: ${'$'}112 qty: 12""""
    val n = """"<prod><name>ladder</name><prx>112</prx><qty>12</qty></prod>""""
    var word = n.substringAfter(article).substringBefore("</prod>")
    val price = n.substringAfter("<prx>").substringBefore("</prx>")
    val qty = n.substringAfter("<qty>").substringBefore("</qty>")
    //word = word.replace("<prod>", article).replace()
    val wordToReturn = """$article > prx: ${'$'}$price qty: $qty"""
    val r = Regex(article).findAll(s).map { it}
    println(r)
    println(wordToReturn)
    return wordToReturn
}
fun main() {

    val s = """<prod><name>drill</name><prx>99</prx><qty>5</qty></prod>

<prod><name>hammer</name><prx>10</prx><qty>50</qty></prod>

<prod><name>screwdriver</name><prx>5</prx><qty>51</qty></prod>

<prod><name>table saw</name><prx>1099.99</prx><qty>5</qty></prod>

<prod><name>saw</name><prx>9</prx><qty>10</qty></prod>

<prod><name>chair</name><prx>100</prx><qty>20</qty></prod>

<prod><name>fan</name><prx>50</prx><qty>8</qty></prod>

<prod><name>wire</name><prx>10.8</prx><qty>15</qty></prod>

<prod><name>battery</name><prx>150</prx><qty>12</qty></prod>

<prod><name>pallet</name><prx>10</prx><qty>50</qty></prod>

<prod><name>wheel</name><prx>8.80</prx><qty>32</qty></prod>

<prod><name>extractor</name><prx>105</prx><qty>17</qty></prod>

<prod><name>bumper</name><prx>150</prx><qty>3</qty></prod>

<prod><name>ladder</name><prx>112</prx><qty>12</qty></prod>

<prod><name>hoist</name><prx>13.80</prx><qty>32</qty></prod>

<prod><name>platform</name><prx>65</prx><qty>21</qty></prod>

<prod><name>car wheel</name><prx>505</prx><qty>7</qty></prod>

<prod><name>bicycle wheel</name><prx>150</prx><qty>11</qty></prod>

<prod><name>big hammer</name><prx>18</prx><qty>12</qty></prod>

<prod><name>saw for metal</name><prx>13.80</prx><qty>32</qty></prod>

<prod><name>wood pallet</name><prx>65</prx><qty>21</qty></prod>

<prod><name>circular fan</name><prx>80</prx><qty>8</qty></prod>

<prod><name>exhaust fan</name><prx>62</prx><qty>8</qty></prod>

<prod><name>window fan</name><prx>62</prx><qty>8</qty></prod>"""
    catalog(s,"ladder" )
    catalog(s,"saw" )
    assertEquals("ladder > prx: $112 qty: 12", catalog(s, "ladder"))
}

