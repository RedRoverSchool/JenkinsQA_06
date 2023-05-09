package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class ManageJenkinsTest extends BaseTest {
    final String NAME_NEW_NODE = "testNameNewNode";

    private static final String USER_FULL_NAME = RandomStringUtils.randomAlphanumeric(13);

    private static final String DESCRIPTION = RandomStringUtils.randomAlphanumeric(130) + "\n\n" + RandomStringUtils.randomAlphanumeric(23);

    private final By User_Name_Link = By.xpath("//a[@href='/user/admin']");

    @Test
    public void testNameNewNodeOnCreatePage() {

        WebElement buildExecutorStatus = getDriver().findElement(By.xpath("//a[@href='/computer/']"));
        buildExecutorStatus.click();
        WebElement newNodeButton = getDriver().findElement(By.xpath("//div[@id='main-panel']//a[@href='new']"));
        newNodeButton.click();
        WebElement inputNodeName = getDriver().findElement(By.xpath("//input[@id='name']"));
        inputNodeName.sendKeys(NAME_NEW_NODE);
        WebElement permanentAgentRadioButton = getDriver().findElement(By.xpath("//label"));
        permanentAgentRadioButton.click();
        WebElement createButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        createButton.click();
        WebElement nameField = getDriver().findElement(By.xpath("//input[@name='name']"));
        String actualValueName = nameField.getAttribute("value");

        Assert.assertEquals(actualValueName, NAME_NEW_NODE);
    }

    @Test
    public void testErrorWhenCreateNewNodeWithEmptyName() {

        WebElement buildExecutorStatus = getDriver().findElement(By.xpath("//a[@href='/computer/']"));
        buildExecutorStatus.click();
        WebElement newNodeButton = getDriver().findElement(By.xpath("//div[@id='main-panel']//a[@href='new']"));
        newNodeButton.click();
        WebElement inputNodeName = getDriver().findElement(By.xpath("//input[@id='name']"));
        inputNodeName.sendKeys(NAME_NEW_NODE);
        WebElement permanentAgentRadioButton = getDriver().findElement(By.xpath("//label"));
        permanentAgentRadioButton.click();
        WebElement createButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        createButton.click();
        WebElement nameField = getDriver().findElement(By.xpath("//input[@name='name']"));
        nameField.clear();
        WebElement saveButton = getDriver().findElement(By.name("Submit"));
        saveButton.click();
        WebElement H1Text = getDriver().findElement(By.xpath("//h1"));
        WebElement textError = getDriver().findElement(By.xpath("//p"));

        Assert.assertEquals(H1Text.getText(), "Error");
        Assert.assertEquals(textError.getText(), "Query parameter 'name' is required");
    }

    @Test
    public void testVerifyChangeNameUser() {
        getDriver().findElement(User_Name_Link).click();

        WebElement configure = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/user/admin/configure']")));
        configure.click();

        WebElement fullName = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='_.fullName']")));
        fullName.clear();
        fullName.sendKeys(USER_FULL_NAME);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(User_Name_Link).getText(), USER_FULL_NAME);
    }

    @Test
    public void testVerifyUserDescription() {
        getDriver().findElement(User_Name_Link).click();

        WebElement editDescription = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='description-link']")));
        editDescription.click();

        WebElement fullName = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']")));
        fullName.clear();
        fullName.sendKeys(DESCRIPTION);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/div")).getText(), DESCRIPTION);
    }
    @Test
    public void testSearchNumericSimbol(){
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.id("settings-search-bar")));
        getDriver().findElement(By.id("settings-search-bar")).sendKeys("1");
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='jenkins-search__results']")));
        String noResults = getDriver().findElement(
                By.xpath("//p[@class='jenkins-search__results__no-results-label']")).getText();

        Assert.assertEquals(noResults, "No results");
    }
}
