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

fun encryptThis(text:String): String{
    val listaString = text.split(" ").toMutableList()

    listaString.forEachIndexed { index, str ->
        if (str.length>1){
            var charArray =  str.toCharArray()
            var charArrayCOpy =  str.toCharArray().copyOf()

            charArrayCOpy[1] = charArray[charArray.lastIndex]
            charArrayCOpy[charArray.lastIndex] = charArray[1]
            var word = charArrayCOpy.joinToString("")


            listaString[index] = word.replaceFirstChar { it.code.toString() }
        }
        else{
            listaString[index] = str.replaceFirstChar { it.code.toString() }
        }
    }
    println(listaString.joinToString(" "))
    return listaString.joinToString(" ")


}
// shorter ==> fun encryptThis(text:String): String{
//    return text.split(" ").map { it.first().toInt().toString() + it.drop(2).takeLast(1) + it.drop(2).dropLast(1) + it.drop(1).take(1) }.joinToString(" ")
//}

fun main() {
    assertEquals("65 119esi 111dl 111lw 108dvei 105n 97n 111ka", encryptThis("A wise old owl lived in an oak"))
    assertEquals("84eh 109ero 104e 115wa 116eh 108sse 104e 115eokp", encryptThis("The more he saw the less he spoke"))
    assertEquals("84eh 108sse 104e 115eokp 116eh 109ero 104e 104dare", encryptThis("The less he spoke the more he heard"))
    assertEquals("87yh 99na 119e 110to 97ll 98e 108eki 116tah 119esi 111dl 98dri", encryptThis("Why can we not all be like that wise old bird"))
    assertEquals("84kanh 121uo 80roti 102ro 97ll 121ruo 104ple", encryptThis("Thank you Piotr for all your help"))
}