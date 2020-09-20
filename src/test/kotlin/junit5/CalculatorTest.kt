package junit5

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.lang.ArithmeticException
import java.lang.Exception
import java.time.Duration

internal class CalculatorTest {

    //moving to beforeAll in companion object
//    private var calculator: Calculator? = null

    companion object {
        private var calculator: Calculator? = null

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            calculator = Calculator()
            println("@BeforeAll -> beforeAll()")
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            calculator = null
            println("@AfterAll -> beforeAll()")
        }
    }


    //executes before test, initialize instances
    @BeforeEach
    fun setUp() {
//        moving to beforeAll in companion object
//        calculator = Calculator()
        println("@BeforeEach -> setup()")
    }

    //clean method
    @AfterEach
    fun tearDown() {
//        calculator = null
        println("@AfterEach -> tearDown")
    }

    @Test
    fun calculatorNotNullTest() {
//        calculator = Calculator()
        assertNotNull(calculator, "Calculator must be not null")
        println("@Test -> calculatorNotNullTest()")
    }

    @Test
    fun `we need and instance of calculator to work`() {
        assertNotNull(calculator, "Calculator must be not null")
        println("@Test -> we need and instance of calculator to work()")
    }

    @Test
    fun addAssertTest() {
        //1.- SetUp
        val calculatorAssert = Calculator()
        val expectedResult = 30
        val currentResult: Int
        //2.- Action
        currentResult = calculatorAssert.add(10, 20)
        //3.- Assert
        assertEquals(expectedResult, currentResult, "custom message")
        println("@Test -> addAssertTest")
    }

    @Test
    fun addTest() {
        assertEquals(30, calculator?.add(10, 20))
        println("@Test -> addTest")
    }

    @Test
    fun assertTypesTest() {
        assertTrue(1 == 1)
        assertFalse(1 == 2)
        assertNull(null)
        assertNotNull(calculator)

        val calculator1: Calculator = Calculator()
        val calculator2: Calculator = Calculator()
        val calculator3: Calculator = calculator1

        //same instance
//        assertSame(calculator1, calculator2)
        assertSame(calculator1, calculator3)

//        assertEquals("jhon", "Jhon", "lowercase not working")
        assertEquals("jhon", "jhon", "lowercase not working")
    }

    @Test
    fun divide_ValidInput_ValidExpected_Test() {
        assertEquals(2, calculator?.divide(4, 2))
        println("@Test ->  divide_ValidInput_wValidExpected_Test")
    }

    @Disabled("Disabled test")
    @Test
    fun divide_NotValidInput_Zero_Test() {
        fail<Int>("Fail detected -> FIXME: can't divide by zero")
//        assertEquals(2, calculator?.divide(5, 0))
//        println("@Test ->  divide_ValidInput_wValidExpected_Test")
    }

    @Test
    fun divideByZero_InvalidInput_ExpectedException_Test() {
        assertThrows(ArithmeticException::class.java, { calculator?.divide(5, 0) }, "Can't divide by zero")
    }

    @DisplayName("Method divide by zero")
    @Test
    fun divideByZero_InvalidInput_ExpectedException_Name_Test() {
        assertThrows(ArithmeticException::class.java, { calculator?.divide(5, 0) }, "Can't divide by zero")
    }

    //executes all teest without success or failed.
    @Test
    fun add_Assert_all_test() {
        assertAll(
            { assertEquals(31, calculator?.add(10, 20)) },
            { assertEquals(20, calculator?.add(10, 20)) },
            { assertEquals(2, calculator?.add(1, 1)) }
        )
    }


    @Nested
    inner class AddTest {
        @Test
        fun add_positive_test() {
            assertEquals(30, calculator?.add(15, 15))
        }

        @Test
        fun add_negative_test() {
            assertEquals(-30, calculator?.add(-15, -15))
        }

        @Test
        fun add_zero_test() {
            assertEquals(0, calculator?.add(0, 0))
        }
    }

    @Test
    fun timeOut_test() {
        assertTimeout(Duration.ofMillis(100)) { calculator?.longTaskOperation() }
    }

}