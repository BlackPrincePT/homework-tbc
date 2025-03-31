package com.perullheim.homework

import kotlin.math.*

class MathFunctions {

    fun findGCD(a: Int, b: Int): Int? {

        if (a == 0 && b == 0)
            return null
        else if (a == 0)
            return b
        else if (b == 0)
            return a

        val aPrimeFactors = getPrimeFactors(number = a)
        val bPrimeFactors = getPrimeFactors(number = b)

        val gcdPrimeFactors = mutableMapOf<Int, Int>()

        for ((factor, count) in aPrimeFactors) {
            bPrimeFactors[factor]?.let { bFactor ->
                gcdPrimeFactors[factor] = min(count, bFactor)
            }
        }

        return calculateProductOfFactors(gcdPrimeFactors)
    }

    fun findLCM(a: Int, b: Int): Int {

        if (a == 0 || b == 0)
            return 0

        val aPrimeFactors = getPrimeFactors(number = a)
        val bPrimeFactors = getPrimeFactors(number = b)

        val lcmPrimeFactors: MutableMap<Int, Int> = bPrimeFactors.toMutableMap()

        for ((factor, count) in aPrimeFactors) {
            val bFactor = bPrimeFactors[factor] ?: 0
            lcmPrimeFactors[factor] = max(count, bFactor)
        }

        return calculateProductOfFactors(lcmPrimeFactors)
    }

    private fun calculateProductOfFactors(factorCounts: MutableMap<Int, Int>): Int {

        var result = 1

        for ((factor, count) in factorCounts) {
            result *= factor.toDouble().pow(count.toDouble()).toInt()
        }

        return result
    }

    private fun getPrimeFactors(number: Int): Map<Int, Int> {

        val primeFactors = mutableMapOf<Int, Int>()

        var factor = 2
        var remainder = number.absoluteValue

        while (remainder != 1) {
            if (remainder % factor == 0) {
                primeFactors[factor] = primeFactors.getOrDefault(factor, defaultValue = 0) + 1
                remainder /= factor
            } else
                factor++
        }

        return primeFactors
    }

    fun checkForDollarSign(word: String): Boolean = word.contains(char = '$')

    fun sumEvenIntegersUntilOneHundred(): Int = (1..100).filter { it % 2 == 0 }.sum()

    fun invertInteger(a: Int): Int? {
        val answer = a.absoluteValue.toString().reversed().toLong() * a.sign
        return if (answer > Int.MAX_VALUE || answer < Int.MIN_VALUE) null else answer.toInt()
    }

    fun checkForPalindrome(word: String): Boolean = word == word.reversed()
}