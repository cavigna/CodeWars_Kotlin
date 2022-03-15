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
        var temp =s.split(":")
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

fun main() {
   // meeting("Alexis:Wahl;John:Bell;Victoria:Schwarz;Abba:Dorny;Grace:Meta;Ann:Arno;Madison:STAN;Alex:Cornwell;Lewis:Kern;Megan:Stan;Alex:Korn")
    assertEquals("(ARNO, ANN)(BELL, JOHN)(CORNWELL, ALEX)(DORNY, ABBA)(KERN, LEWIS)(KORN, ALEX)(META, GRACE)(SCHWARZ, VICTORIA)(STAN, MADISON)(STAN, MEGAN)(WAHL, ALEXIS)", meeting("Alexis:Wahl;John:Bell;Victoria:Schwarz;Abba:Dorny;Grace:Meta;Ann:Arno;Madison:STAN;Alex:Cornwell;Lewis:Kern;Megan:Stan;Alex:Korn"))
    assertEquals("(BELL, MEGAN)(CORNWELL, AMBER)(DORNY, JAMES)(DORRIES, PAUL)(GATES, JOHN)(KERN, ANN)(KORN, ANNA)(META, ALEX)(RUSSEL, ELIZABETH)(STEVE, LEWIS)(WAHL, MICHAEL)", meeting("John:Gates;Michael:Wahl;Megan:Bell;Paul:Dorries;James:Dorny;Lewis:Steve;Alex:Meta;Elizabeth:Russel;Anna:Korn;Ann:Kern;Amber:Cornwell"))
    assertEquals("(ARNO, ALEX)(ARNO, HALEY)(BELL, SARAH)(CORNWELL, ALISSA)(DORNY, PAUL)(DORRIES, ANDREW)(KERN, ANN)(KERN, MADISON)", meeting("Alex:Arno;Alissa:Cornwell;Sarah:Bell;Andrew:Dorries;Ann:Kern;Haley:Arno;Paul:Dorny;Madison:Kern"))
}