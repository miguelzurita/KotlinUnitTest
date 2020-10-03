package mockito

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.Answer
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

    @Test
    fun addDoubleToIntThenAnswerTest() {
        val answer: Answer<Int> = Answer{ 7 }
        `when`(validNumber!!.doubleToInt(7.777)).thenAnswer(answer)
        assertEquals(14, add!!.addInt(7.777))

    }

    /*
    Arrange
    Act (Action / Method to test)
    Assert

    Given
    When
    Then
     */
    @Test
    fun aaaPatternTest() {
        //Arrange
        `when`(validNumber!!.check(4)).thenReturn(true)
        `when`(validNumber.check(5)).thenReturn(true)

        //Act
        val result = add!!.add(4, 5)

        //Assert
        assertEquals(9, result)
    }

    @Test
    fun bddPatternTest() {
        //Given
        given(validNumber!!.check(4)).willReturn(true)
        given(validNumber.check(5)).willReturn(true)

        //When
        val result = add!!.add(4, 5)

        //Then
        assertEquals(9, result)
    }
}
