package com.perullheim.homework

fun main() {
    mainLoop@while (true) {
        println("დაწყება")

        print("შეიყვანეთ X ცვლადის მნიშვნელობა: ")
        val x = readln().filter { it.isDigit() }.toDoubleOrNull() ?: 0.0

        print("შეიყვანეთ Y ცვლადის მნიშვნელობა: ")
        val y = readln().filter { it.isDigit() }.toDoubleOrNull() ?: 0.0

        val z = if (y != 0.0) (x / y).toString() else "განუსაზღვრელი"

        println("X და Y განაყოფი არის: $z")

        println("გსურთ პროგრამის ხელახლა დაწყება <Y/N>?")

        var answer = readln()

        while (answer != "დიახ") {
            if (answer == "არა") {
                println("დასასრული")
                break@mainLoop
            }

            answer = readln()
        }

        continue
    }
}