package com.example.sakova1

import java.util.concurrent.ThreadLocalRandom

class Service {
    fun generateNumbers(start: String, stop: String, count: String): ArrayList<Int> {
        val startInt = start.toInt()
        val stopInt  = stop.toInt()
        val countInt = count.toInt()

        val numbers : ArrayList<Int> = arrayListOf()
        for (i in 0 until countInt) {
            val x = ThreadLocalRandom.current().nextInt(startInt, stopInt+1)
            numbers.add(x)
        }

        return numbers
    }

    fun processNumbers(numbers: ArrayList<Int>) : List<Int> {
        return numbers.distinct()
            .filter {
                    x -> numbers.count { it == x } > 1
            }.toList()
    }
}


