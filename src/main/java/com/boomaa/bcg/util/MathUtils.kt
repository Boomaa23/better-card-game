package com.boomaa.bcg.util

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object MathUtils {
    fun gcd(a: Int, b: Int): Int {
        return if (a == 0 || b == 0) {
            a + b
        } else {
            val sa = abs(a)
            val sb = abs(b)
            val smv = min(sa, sb)
            gcd(max(sa, sb) % smv, smv)
        }
    }

    fun lcm(a: Int, b: Int): Int {
        return if (a == 0 || b == 0) {
            0
        } else {
            abs(a * b) / gcd(a, b)
        }
    }
}