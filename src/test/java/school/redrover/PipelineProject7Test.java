package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class PipelineProject7Test extends BaseTest {
    private String name1 = "Project1";

    @Test
    public void testCreatePipelineProject() {
        TestUtils.createPipeline(this, name1, true);

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//tr[@id='job_" + name1 + "']//a//span['" + name1 + "']"))
                .getText(), name1);
    }

    @DataProvider(name = "wrong-characters")
    public Object[][] providerWrongCharacters() {
        return new Object[][]{{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"?"}, {"|"}, {">"}, {"["}, {"]"}};

    }

    @Test(dataProvider = "wrong-characters")
    public void testWrongCharactersBeforeNameProject(String wrongCharacters) {
        getDriver().findElement(By.linkText("New Item")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("name"))).sendKeys(wrongCharacters);

        Assert.assertEquals(getDriver()
                .findElement(By.id("itemname-invalid"))
                .getText(), "» ‘" + wrongCharacters + "’ is an unsafe character");

        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());

        getDriver().findElement(By.xpath("//a[contains(text(), 'Dashboard')]")).click();
    }

    @Test
    public void testDotBeforeNameProject() {
        getDriver().findElement(By.linkText("New Item")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("name"))).sendKeys(".");

        Assert.assertEquals(getDriver().findElement(By.id("itemname-invalid"))
                .getText(), "» “.” is not an allowed name");
    }
}
