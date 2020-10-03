package mockito

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.*
import org.mockito.BDDMockito.*
import org.mockito.Mockito.`when`
import org.mockito.stubbing.Answer
import java.lang.ArithmeticException
import java.lang.Exception

class AddTest {
    @InjectMocks
    private val add: Add? = null

    @Mock
    private val print: Print? = null

    @Mock
    private val validNumber: ValidNumber? = null

    @Captor
    private val captor: ArgumentCaptor<Int>? = null

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
        val answer: Answer<Int> = Answer { 7 }
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

    @Test
    fun argumentMatcherTest() {
        //Given
        given(validNumber!!.check(ArgumentMatchers.anyInt())).willReturn(true)

        //When
        val result = add!!.add(10, 20)

        //Then
        assertEquals(30, result)
    }

    @Test
    fun addPrintVerifyTimesTest() {
        //Given
        given(validNumber!!.check(4)).willReturn(true)

        //When
        val result = add!!.add(4, 4)

        //Then
        //check how many times the function was called
        Mockito.verify(validNumber, Mockito.times(2)).check(4)
        assertEquals(8, result)
    }

    @Test
    fun verifyTypesTest() {
        //Given
        given(validNumber!!.check(4)).willReturn(true)
        given(validNumber.check(5)).willReturn(true)

        //When
        val result = add!!.add(4, 5)

        //Then
        Mockito.verify(validNumber).check(4)
        Mockito.verify(validNumber).check(5)
        //verify that validNumber.check(9) was never called
        Mockito.verify(validNumber, Mockito.never()).check(9)
        //atLeast
        Mockito.verify(validNumber, Mockito.atLeast(1)).check(4)
        //atMost
        Mockito.verify(validNumber, Mockito.atMost(3)).check(5)

        assertEquals(9, result)
    }

    @Test
    fun addPrintTest() {
        //Given
        given(validNumber!!.check(4)).willReturn(true)
        given(validNumber.check(5)).willReturn(true)


        add!!.addPrint(4, 5)
        Mockito.verify(print!!, Mockito.atLeast(1)).showMessage(9)

        add.addPrint(4, 7f)
        Mockito.verify(print).showError()
    }

    @Test
    fun captorTest() {
        //Given
        given(validNumber!!.check(4)).willReturn(true)
        given(validNumber.check(5)).willReturn(true)

        //When
        add!!.addPrint(4, 5)

        //Then
        verify(print)!!.showMessage(captor!!.capture())
        assertEquals(9, captor.value)

    }
}
