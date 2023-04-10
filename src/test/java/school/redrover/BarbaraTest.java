package school.redrover;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BarbaraTest {
    @Test
    public void firstTest(){
        String test = "Github First Test";
        Assert.assertEquals("Github First Test", test);
    }
}


