package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MainTest extends BaseTest {

    @Test
    public void createMultibranchPipelineTest() {
        final String itemName = "TestName";
        final String expectedRes = "Test";

        MainPage mainPage = new MainPage(getDriver())
                .clickNewItemButton()
                .inputAnItemName(itemName)
                .clickMultibranchPipeline()
                .clickOkButton()
                .enterValueInDisplayNameField();

        String actualRes = mainPage.getNameMultibranchPipelineDisplayNameText();

        Assert.assertEquals(actualRes, expectedRes);
    }
}
