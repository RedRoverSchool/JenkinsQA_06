package school.redrover;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BarbaraTest {
    @Test
    public void firstTest(){
        String test = "Github First Simple Test";
        Assert.assertEquals("Github First Simple Test", test);
    }
}


