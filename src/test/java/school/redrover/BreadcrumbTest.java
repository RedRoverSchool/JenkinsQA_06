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
        return new Object[][] {{"//li[@id='yui-gen6']/a/span", "//li[@id='inpage-nav']", "Configure System"},
                {"//li[@id='yui-gen7']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/configureTools/']", "Global Tool Configuration"},
                {"//li[@id='yui-gen8']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/pluginManager/']", "Plugin Manager"},
                {"//li[@id='yui-gen9']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/computer/']", "Nodes"},
                {"//li[@id='yui-gen10']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/install/']", "Install as Windows Service"},
                {"//li[@id='yui-gen12']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/configureSecurity/']", "Configure Global Security"},
                {"//li[@id='yui-gen13']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/credentials/']", "Credentials"},
                {"//li[@id='yui-gen14']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/configureCredentials/']", "Configure Credential Providers"},
                {"//li[@id='yui-gen15']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/securityRealm/']", "Jenkins’ own user database"},
                {"//li[@id='yui-gen16']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/scriptApproval/']", "ScriptApproval"},
                {"//li[@id='yui-gen18']/a/span",
                        "//ol[@id='breadcrumbs']/li[@aria-current='page']", "System Information"},
                {"//li[@id='yui-gen19']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/log/']", "System Log"},
                {"//li[@id='yui-gen20']/a/span",
                        "//ol[@id='breadcrumbs']/li[@aria-current='page']", "Load Statistics"},
                {"//li[@id='yui-gen21']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/about/']", "About Jenkins"},
                {"//li[@id='yui-gen23']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/administrativeMonitor/OldData/']", "Manage Old Data"},
                {"//li[@id='yui-gen26']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/cli/']", "Jenkins CLI"},
                {"//li[@id='yui-gen27']/a/span",
                        "//ol[@id='breadcrumbs']/li[@aria-current='page']", "Script Console"},
                {"//li[@id='yui-gen28']/a/span",
                        "//ol[@id='breadcrumbs']/li/a[@href='/manage/prepareShutdown/']", "Prepare for Shutdown"}};
    }

    @Test(dataProvider = "subsections")
    public void testNavigateToManageJenkinsSubsection(String locator, String breadcrumbLocator, String subsectionName) {
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

        if (locator.contains("26") || locator.contains("27") || locator.contains("28")) {
            new Actions(getDriver()).sendKeys(Keys.ARROW_RIGHT).perform();
            for (int i = 0; i < 18; i++) {
                new Actions(getDriver()).sendKeys(Keys.ARROW_DOWN).perform();
            }
        }
        By subsectionNameLocator = By.xpath(locator);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(subsectionNameLocator));
        WebElement subSection = getDriver().findElement(subsectionNameLocator);
        subSection.click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath(breadcrumbLocator)));

        Assert.assertEquals(getDriver().findElement(By.xpath(breadcrumbLocator)).getText(), subsectionName);
    }
}
