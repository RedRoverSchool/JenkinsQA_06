package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AnnaWTest {
    @Test
    public void firstTest(){
        String test = "First Test";
        Assert.assertEquals("First Test", test);
    }
}
