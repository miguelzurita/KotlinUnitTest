package mockito

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ValidNumberTest {

    private var validNumber: ValidNumber? = null

    @BeforeEach
    fun setUp() {
        validNumber = ValidNumber()
    }

    @AfterEach
    fun tearDown() {
        validNumber = null
    }

    @Test
    fun checkTest() {
        assertEquals(true, validNumber!!.check(5))

    }

    @Test
    fun checkNegativeTest() {
        assertEquals(false, validNumber!!.check(-5))
    }

    @Test
    fun checkStringTest() {
        assertEquals(false, validNumber!!.check("5"))
    }

    @Test
    fun checkDoubleTest() {
        assertEquals(false, validNumber!!.check(5.5))
    }

    @Test
    fun checkZeroTest() {
        assertEquals(true, validNumber!!.check(0))
    }

}