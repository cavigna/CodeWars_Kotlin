package eight_kyu

import kotlin.math.pow

/**
 * Ask a small girl - "How old are you?". She always says strange things... Lets help her!

For correct answer program should return int from 0 to 9.

Assume test input string always valid and may look like "1 year old" or "5 years old", etc.. The first char is number only.
 */

fun getAge(yearsOld: String): Int {

    return yearsOld[0].toString().toInt()

}


/**
 * Summation
Write a program that finds the summation of every number from 1 to num. The number will always be a positive integer greater than 0.

For example:

summation(2) -> 3
1 + 2

summation(8) -> 36
1 + 2 + 3 + 4 + 5 + 6 + 7 + 8
 */


fun summation(n: Int): Int = (0 until n).map { it + 1 }.sum()

// better way ==> fun summation(n: Int) = (1..n).sum()


/**
 * Write a function to convert a name into initials. This kata strictly takes two words with one space in between them.

The output should be two capital letters with a dot separating them.

It should look like this:

Sam Harris => S.H

patrick feeney => P.F
 */

fun abbrevName(name: String): String = name
    .split(' ')
    .mapNotNull { it.first().toString().toUpperCase() }
    .joinToString(".")
// shorter ==> fun abbrevName(name: String) = name.split(" ").joinToString(".") { it.take(1).uppercase() }


/**
 * Remove String Spaces
Simple, remove the spaces from the string, then return the resultant string.
assertEquals("8j8mBliB8gimjB8B8jlB", noSpace("8 j 8   mBliB8g  imjB8B8  jl  B"))
 */

fun noSpace(x: String): String = x.filter { !it.isWhitespace() }
// similar ==> fun noSpace(x: String) = x.filterNot { it.isWhitespace() }
// other ==> fun noSpace(x: String) = x.replace(" ", "")


/**
 * This function should test if the factor is a factor of base.

Return true if it is a factor or false if it is not.

About factors
Factors are numbers you can multiply together to get another number.

2 and 3 are factors of 6 because: 2 * 3 = 6

You can find a factor by dividing numbers. If the remainder is 0 then the number is a factor.
You can use the mod operator (%) in most languages to check for a remainder
For example 2 is not a factor of 7 because: 7 % 2 = 1

Note: base is a non-negative number, factor is a positive number.
assertEquals(true, checkForFactor(10, 2))
 */

fun checkForFactor(base: Int, factor: Int): Boolean = base % factor == 0


/**
 * Given an array of integers, return a new array with each value doubled.

For example:

[1, 2, 3] --> [2, 4, 6]
 */

fun lostwithoutmap(x: IntArray): IntArray = x.map { it * 2 }.toIntArray()


/**
 * Given a set of numbers, return the additive inverse of each. Each positive becomes negatives, and the negatives become positives.

invert([1,2,3,4,5]) == [-1,-2,-3,-4,-5]
invert([1,-2,3,-4,5]) == [-1,2,-3,4,-5]
invert([]) == []
 */
fun invert(arr: IntArray): IntArray = if (arr.isNotEmpty()) {
    arr.map { it * -1 }.toIntArray()
} else arr
// clever ==> fun invert(arr: IntArray) = arr.map { -it }.toIntArray()


/**
 * Our football team finished the championship. The result of each match look like "x:y". Results of all matches are recorded in the collection.

For example: ["3:1", "2:2", "0:1", ...]

Write a function that takes such collection and counts the points of our team in the championship. Rules for counting points for each match:

if x>y - 3 points
if x<y - 0 point
if x=y - 1 point
Notes:

there are 10 matches in the championship
0 <= x <= 4
0 <= y <= 4
 */

fun points(games: List<String>): Int {
    var result = 0
    games.forEach { mach: String ->
        val score = mach.split(':')
        when {
            score[0].toInt() > score[1].toInt() -> result += 3
            score[0].toInt() < score[1].toInt() -> result -= 0
            else -> result += 1
        }
    }
    return result
}

/*
fun points(games: List<String>) =
        games.sumBy {
            val (x, y) = it.split(":")
            when {
                x > y -> 3
                x < y -> 0
                else -> 1
            }
        }
 */

/**
 * Convert number to reversed array of digits
Given a random non-negative number, you have to return the digits of this number within an array in reverse order.

Example:
348597 => [7,9,5,8,4,3]
0 => [0]
 */

fun digitize(n:Long):IntArray = n.toString().map { it.toString().toInt()}.reversed().toIntArray()


/**
 * Given a non-empty array of integers, return the result of multiplying the values together in order. Example:

[1, 2, 3, 4] => 1 * 2 * 3 * 4 = 24
 */

fun grow(arr: IntArray): Int = arr.toList().reduce { acc, i -> acc * i  }

// no list ==> fun grow(arr: IntArray): Int = arr.reduce { a, b -> a * b }