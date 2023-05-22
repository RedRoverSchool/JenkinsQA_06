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
        return new Object[][]{{"//div[@id='submenu0']/div/ul/li/a[@href='/manage/configure']/span", "Configure System"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/configureTools']/span", "Global Tool Configuration"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/pluginManager']/span", "Plugins"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/computer']/span", "Manage nodes and clouds"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/configureSecurity']/span", "Configure Global Security"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/credentials']/span", "Credentials"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/configureCredentials']/span", "Configure Credential Providers"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/securityRealm/']/span", "Users"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/systemInfo']/span", "System Information"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/log']/span", "Log Recorders"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/load-statistics']/span", "Load statistics: Jenkins"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/about']/span", "Jenkins\n" +
                        "Version\n" +
                        "2.387.2"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/administrativeMonitor/OldData/']/span", "Manage Old Data"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='#' and @class='yuimenuitemlabel']/span",
                        "Reload Configuration from Disk: are you sure?"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/cli']/span", "Jenkins CLI"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/script']/span", "Script Console"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/prepareShutdown']/span", "Prepare for Shutdown"}};
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

        if (locator.contains("@class='yuimenuitemlabel'")||
                locator.contains("/cli") || locator.contains("/script") || locator.contains("/prepareShutdown")) {
            new Actions(getDriver()).sendKeys(Keys.ARROW_RIGHT).perform();
            for (int i = 0; i < 16; i++) {
                new Actions(getDriver()).sendKeys(Keys.ARROW_DOWN).perform();
            }
        }
        By subsectionNameLocator = By.xpath(locator);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(subsectionNameLocator));
        WebElement subSection = getDriver().findElement(subsectionNameLocator);
        subSection.click();

        if (locator.contains("@class='yuimenuitemlabel'")){
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
