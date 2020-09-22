package mockito

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AddCreateMock1Test {
    private var add: Add? = null
    private var validNumber: ValidNumber? = null

    @BeforeEach
    fun setUp() {
        validNumber = Mockito.mock(ValidNumber::class.java)
        add = Add(validNumber!!)
    }

    @Test
    fun addTest() {
        add?.add(2, 5)
        Mockito.verify(validNumber)?.check(2)
    }
}