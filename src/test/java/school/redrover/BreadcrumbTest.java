package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class BreadcrumbTest extends BaseTest {
    @Test
    public void testNavigateToManageJenkinsSection() {
        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath("//*[@id='breadcrumbs']/li/a[@href='/']"))).perform();

        By pointerLocator =
                By.xpath("//*[@id='breadcrumbs']/li/a/button[@class='jenkins-menu-dropdown-chevron']");
        getWait5().until(ExpectedConditions.elementToBeClickable(pointerLocator));
        WebElement pointer = getDriver().findElement(pointerLocator);
        new Actions(getDriver()).moveToElement(pointer).perform();
        pointer.sendKeys(Keys.RETURN);

        By sectionNameLocator = By.xpath("//*[@id='yui-gen4']/a/span");
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(sectionNameLocator));
        getDriver().findElement(sectionNameLocator).click();

        WebElement manageJenkinsHeader = getDriver().findElement(By.tagName("h1"));
        getWait2().until(ExpectedConditions.visibilityOf(manageJenkinsHeader));

        Assert.assertEquals(manageJenkinsHeader.getText(), "Manage Jenkins");
    }

    @DataProvider(name = "subsections")
    public Object[][] provideSubsections() {
        return new Object[][]{{"//li[@id='yui-gen6']/a/span", "Configure System"},
                {"//li[@id='yui-gen7']/a/span", "Global Tool Configuration"},
                {"//li[@id='yui-gen8']/a/span", "Plugins"},
                {"//li[@id='yui-gen9']/a/span", "Manage nodes and clouds"},
                {"//li[@id='yui-gen11']/a/span", "Configure Global Security"},
                {"//li[@id='yui-gen12']/a/span", "Credentials"},
                {"//li[@id='yui-gen13']/a/span", "Configure Credential Providers"},
                {"//li[@id='yui-gen14']/a/span", "Users"},
                {"//li[@id='yui-gen16']/a/span", "System Information"},
                {"//li[@id='yui-gen17']/a/span", "Log Recorders"},
                {"//li[@id='yui-gen18']/a/span", "Load statistics: Jenkins"},
                {"//li[@id='yui-gen19']/a/span", "Jenkins\n" +
                        "Version\n" +
                        "2.387.2"},
                {"//li[@id='yui-gen21']/a/span", "Manage Old Data"},
                {"//li[@id='yui-gen23']/a/span", "Reload Configuration from Disk: are you sure?"},
                {"//li[@id='yui-gen24']/a/span", "Jenkins CLI"},
                {"//li[@id='yui-gen25']/a/span", "Script Console"},
                {"//li[@id='yui-gen26']/a/span", "Prepare for Shutdown"}};
    }
    @Test(dataProvider = "subsections")
    public void testNavigateToManageJenkinsSubsection(String locator, String subsectionName) {
        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath("//*[@id='breadcrumbs']/li/a[@href='/']"))).perform();

        By pointerLocator =
                By.xpath("//*[@id='breadcrumbs']/li/a/button[@class='jenkins-menu-dropdown-chevron']");
        getWait5().until(ExpectedConditions.elementToBeClickable(pointerLocator));
        WebElement pointer = getDriver().findElement(pointerLocator);
        new Actions(getDriver()).moveToElement(pointer).perform();
        pointer.sendKeys(Keys.RETURN);

        By sectionNameLocator = By.xpath("//*[@id='yui-gen4']/a/span");
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(sectionNameLocator));
        new Actions(getDriver()).moveToElement(getDriver().findElement(sectionNameLocator)).perform();

        if (locator.contains("23")||locator.contains("24") || locator.contains("25") || locator.contains("26")) {
            new Actions(getDriver()).sendKeys(Keys.ARROW_RIGHT).perform();
            for (int i = 0; i < 16; i++) {
                new Actions(getDriver()).sendKeys(Keys.ARROW_DOWN).perform();
            }
        }
        By subsectionNameLocator = By.xpath(locator);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(subsectionNameLocator));
        WebElement subSection = getDriver().findElement(subsectionNameLocator);
        subSection.click();

        if (locator.contains("23")){
            Alert alert = getWait5().until(ExpectedConditions.alertIsPresent());
            String text = alert.getText();
            alert.dismiss();
            Assert.assertEquals(text, "Reload Configuration from Disk: are you sure?");
        } else {
            getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
            Assert.assertEquals(getDriver().findElement(By.tagName("h1")).getText(), subsectionName);
        }
    }
}
