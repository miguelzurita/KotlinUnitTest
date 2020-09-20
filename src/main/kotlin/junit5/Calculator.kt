package junit5

import java.lang.ArithmeticException
import java.lang.Exception

class Calculator {
    fun add(n1: Int, n2: Int): Int {
        return n1 + n2
    }

    fun substract(n1: Int, n2: Int): Int {
        return n1 - n2
    }

    fun divide(n1: Int, n2: Int): Int {
        return n1 / n2
    }

    fun dividiedByZero(n1: Int, n2: Int): Int {
        if (n2 == 0) {
            throw ArithmeticException("Can't divide by zero")
        }
        return n1 / n2
    }

    fun longTaskOperation() {
        try {
            Thread.sleep(1000)
        } catch (exception: Exception) {

        }
    }
}