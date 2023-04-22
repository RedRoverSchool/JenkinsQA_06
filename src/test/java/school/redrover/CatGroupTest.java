package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatGroupTest extends BaseTest {


    @FindBy(xpath = "//section[@class='empty-state-section']//span[text()='Create a job']")
    private WebElement createAJobButton;

    @FindBy(xpath = "//div[@id='items']//span[@class='label']")
    private List<WebElement> itemsNameOfLabels;

    @FindBy(xpath = "//button[@id='ok-button']")
    private WebElement okButton;

    public WebDriverWait webDriverWait10;


    public final WebDriverWait getWait10() {
        if (webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return webDriverWait10;
    }

    public final void verifyElementVisible(WebElement element) {

        getWait10().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement verifyElementIsClickable(WebElement element) {
        return getWait10().until(ExpectedConditions.elementToBeClickable(element));
    }

    public final void clickCreateAJobButton() {
        verifyElementVisible(createAJobButton);
        verifyElementIsClickable(createAJobButton).click();
    }

    public void scrollByElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public int getListSize(List<WebElement> elements) {
        return elements.size();
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public List<String> getNamesOfLists(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(getText(element));
        }

        return texts;
    }

    @Test
    public void testNameOfItemsOfLabels() {

        getDriver().manage().window().maximize();

        final List<String> expectedNamesOfItems = Arrays.asList("Freestyle project", "Pipeline",
                "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder");

        PageFactory.initElements(getDriver(), this);
        clickCreateAJobButton();

        verifyElementVisible(okButton);
        List<String> actualNameOfItems = getNamesOfLists(itemsNameOfLabels);

        Assert.assertEquals(actualNameOfItems, expectedNamesOfItems);
    }
}
