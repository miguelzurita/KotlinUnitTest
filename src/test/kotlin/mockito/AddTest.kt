package mockito

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.lang.ArithmeticException
import java.lang.Exception

class AddTest {
    @InjectMocks
    private val add: Add? = null

    @Mock
    private val validNumber: ValidNumber? = null

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun addTest() {
        `when`(validNumber!!.check(3)).thenReturn(true)
        val checkNumber: Boolean = validNumber!!.check(3)
        assertEquals(true, checkNumber)

        `when`(validNumber!!.check(-3)).thenReturn(false)
        val checkNumber2: Boolean = validNumber!!.check(-3)
        assertEquals(false, checkNumber2)
    }

    @Test
    fun addMockExceptionTest() {
        `when`(validNumber!!.checkZero(0)).thenThrow(ArithmeticException("Can't accept zero"))

        var exception: Exception? = null
        try {
            validNumber.checkZero(0)
        } catch (e: ArithmeticException) {
            exception = e
        }

        assertNotNull(exception)
        assertThrows(ArithmeticException::class.java) {
            validNumber!!.checkZero(0)
        }
    }

    @Test
    fun addRealMethodTest() {
        `when`(validNumber!!.check(3)).thenCallRealMethod()
        assertEquals(true, validNumber.check(3))

        `when`(validNumber!!.check("3")).thenCallRealMethod()
        assertEquals(false, validNumber.check("3"))
    }
}
