package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

public class FreestyleProject01Test extends BaseTest {
    private static final String FREESTYLE_NAME = "Freestyle";

    @Test
    public void testCreateFreestyleProject(){
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .projectSave();

        Assert.assertEquals(getDriver().findElement(By.tagName("h1")).getText(), "Project " + FREESTYLE_NAME);
    }

    @Test
    public void testNameAndDescAreDisplayed(){
        String desc = "Description";

        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .addDescription(desc)
                .projectSave();

        Assert.assertEquals(getDriver().findElement(By.tagName("h1")).getText(), "Project " + FREESTYLE_NAME);
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id = 'description']/div")).getText(), desc);
    }

    @Test
    public void testDisableFreestyleProject(){
        String message = "This project is currently disabled";

        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .projectSave()
                .clickTheDisableProjectButton();

                Assert.assertEquals(getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("enable-project"))).getText().substring(0, message.length()), message);

                Assert.assertTrue(getDriver().findElement(By.xpath("//button[text() = 'Enable']")).isDisplayed());
        }

    @Test
    public void testEnableFreestyleProject(){
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .projectSave()
                .clickTheDisableProjectButton()
                .clickTheEnableProjectButton();

                Assert.assertTrue(getDriver().findElement(By.xpath("//button[text() = 'Disable Project']")).isDisplayed());
    }
}
