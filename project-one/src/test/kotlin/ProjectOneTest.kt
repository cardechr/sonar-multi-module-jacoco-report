import org.junit.Assert
import org.junit.Test

class ProjectOneTest {
    private val projectOne = ProjectOne()

    @Test
    fun shouldReturnMsg() {
        Assert.assertEquals("Hello from Project One", projectOne.hello())
    }
}