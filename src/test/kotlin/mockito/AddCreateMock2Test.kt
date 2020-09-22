package mockito

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AddCreateMock2Test {

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
        add?.add(2, 5)
        Mockito.verify(validNumber)?.check(2)
    }
}