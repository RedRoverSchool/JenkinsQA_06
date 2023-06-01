package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class RestAPITest extends BaseTest{

    @Test
    public void restApiTest() {
        final String apiLink = "REST API";

        String mainPage = new MainPage(getDriver())
                .clickOnRestApiLink()
                .getRestApiPageTitle();

        Assert.assertEquals(mainPage, apiLink);
    }
}
