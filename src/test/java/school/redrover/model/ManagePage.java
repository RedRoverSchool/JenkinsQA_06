package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

import java.util.List;

public class ManagePage extends BaseMainHeaderPage<ManagePage> {
    public ManagePage(WebDriver driver) {
        super(driver);
    }

    private static final By SEARCH_SETTINGS = By.id("settings-search-bar");

    public ManagePage navigateToManagePage() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        return this;
    }

    public ManagePage enterSearchQuery(String symbol) {
        getDriver().findElement(SEARCH_SETTINGS).sendKeys(symbol);
        return this;
    }

    public ManagePage clickSearchButton() {
        getDriver().findElement(SEARCH_SETTINGS).click();
        getWait2().until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath(" //div[@class='jenkins-search__results']")));
        return this;
    }

    public String getNoResultsDisplayed() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(" //div[@class='jenkins-search__results']"))).getText();
    }

    public ManagePage selectOnTheFirstLineInDropdown() {

        getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".jenkins-search__results-item--selected")));

        List<WebElement> options = getDriver().findElements(By.cssSelector(".jenkins-search__results-item--selected"));
        for (WebElement option : options) {
            if (option.getText().equals("Configure System")) {
                option.click();
                break;
            }
        }
        return this;
    }

    public String getConfigureSystemPage() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//h1[normalize-space()='Configure System']"))).getText();
    }

    public ManagePage navigateToManageUsersPage() {
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
        return this;
    }


    public ManagePage navigateManageNodesAndClouds() {
        getDriver().findElement(By.xpath("//a[@href='computer']")).click();
        return this;
    }

    public ManagePage clickConfigureMasterNode() {
        getDriver().findElement(By.xpath("//a[@class='jenkins-table__button']//*[name()='svg']")).click();
        return this;
    }


    public ManagePage changeNumberOfExecutorsAndSave(String number) {
        WebElement changeNumber = getDriver().findElement(By.name("_.numExecutors"));
        changeNumber.clear();
        changeNumber.sendKeys(number);
        getDriver().findElement(By.name("Submit")).click();
        return new ManagePage(getDriver());
    }

    public ManagePage navigateToMasterNodeConfiguration() {
        getDriver().findElement(By.xpath("//a[@href='/manage/computer/(built-in)/configure']")).click();
        return this;
    }

    public String numberOfExecutors() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("_.numExecutors")))
                .getAttribute("value");
    }

    public ManagePage navigateToDashboardIcon() {
        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath("//div[@id='breadcrumbBar']//li[1]"))).perform();
        return this;
    }

    public ManagePage dropdownBreadcrumps() {
        WebElement arrow = getDriver().findElement(By.xpath("//*[@id='breadcrumbs']/li/a/button[@class='jenkins-menu-dropdown-chevron']"));
        new Actions(getDriver()).moveToElement(arrow).perform();
        arrow.sendKeys(Keys.RETURN);
        return this;
    }

    public ManagePage navigateToManageJenkinsAndClick() {
        getDriver().findElement(By.xpath("//*[@id='yui-gen4']/a/span")).click();
        return this;
    }

    public String verifyManageJenkinsPage() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))).getText();
    }
}

