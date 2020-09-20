package junit5

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll

internal class TemperatureCalculatorTest {

    companion object {
        private var temperatureCalculator: TemperatureCalculator? = null

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            temperatureCalculator = TemperatureCalculator()
        }
    }

    @Test
    fun calculator_is_not_null() {
        assertNotNull(temperatureCalculator)
    }

    @Test
    fun valid_degree_to_fahrenheit_test() {
        assertEquals(86.0f, temperatureCalculator?.toFahrenheit(30.0f))
    }

    @Test
    fun valid_degree_to_fahrenheit_with_delta_test() {
        assertEquals(-9.4f, temperatureCalculator!!.toFahrenheit(-23f), 0.01f)
    }

    @Test
    fun not_valid_degree_to_fahrenheit_test() {
        assertNotEquals(86.0f, temperatureCalculator?.toFahrenheit(10.0f))
    }
}