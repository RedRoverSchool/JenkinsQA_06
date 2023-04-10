package school.redrover;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BarbaraTest {
    @Test
    public void firstTest(){
        String test = "First Test";
        Assert.assertEquals("First Test", test);
    }
}


