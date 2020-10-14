import org.junit.Assert
import org.junit.Test

class ProjectTwoTest {
    private val projectTwo = ProjectTwo()

    @Test
    fun shouldReturnMsg() {
        Assert.assertEquals("Hello from Project Two", projectTwo.hello())
    }
}