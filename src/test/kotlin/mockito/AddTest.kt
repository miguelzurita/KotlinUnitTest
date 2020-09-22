package mockito

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

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
}
