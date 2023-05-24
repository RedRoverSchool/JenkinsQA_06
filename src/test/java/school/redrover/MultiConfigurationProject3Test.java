package school.redrover;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.DataTest;
import school.redrover.runner.TestUtils;

public class MultiConfigurationProject3Test extends BaseTest {

    final String expectedprojectName =getProjectName();
    @Ignore
    @Test
    public void createDefaultMultiConfigurationProjectTest(){

        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name = 'name']"))).sendKeys(expectedprojectName);
        getDriver().findElement(By.cssSelector("li.hudson_matrix_MatrixProject span")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name = 'Submit']"))).click();
        getDriver().findElement(By.xpath("//a[@href='/']")).click();
        WebElement project = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#projectstatus a.model-link")));

        Assert.assertEquals(project.getText(), expectedprojectName);
    }

    private static String getProjectName() {
        Faker faker = new Faker();
        return faker.funnyName().name();
    }
    @Ignore
    @Test(dataProvider = "unsafeCharacter" ,dataProviderClass = DataTest.class)
    public void verifyProjectNameCreationWithUnsafeSymbolsTest(char unsafeSymbol , String htmlUnsafeSymbol){
            getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
            getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name = 'name']")))
                    .sendKeys(expectedprojectName +unsafeSymbol );
            getDriver().findElement(By.cssSelector("li.hudson_matrix_MatrixProject span")).click();
            String errorNotification = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#itemname-invalid"))).getText();

            Assert.assertEquals(errorNotification, String.format("» ‘%s’ is an unsafe character", unsafeSymbol));

            getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();

            String errorPageHeader = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']//h1"))).getText();
            String errorPageMessage = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']//p"))).getText();

            Assert.assertEquals(errorPageHeader, "Error");
            Assert.assertEquals(errorPageMessage, String.format("‘%s’ is an unsafe character", unsafeSymbol));
    }
    
    @Ignore
    @Test(dataProvider = "unsafeCharacter", dataProviderClass = DataTest.class)
    public void verifyProjectNameRenameWithUnsafeSymbolsTest(char unsafeSymbol, String htmlUnsafeSymbol) {
        TestUtils.createMultiConfigurationProject(this, expectedprojectName, true);

        WebElement project = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#projectstatus a.model-link")));
        new Actions(getDriver()).moveToElement(project).perform();
        WebElement menuDropdown = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".jenkins-table__link > .jenkins-menu-dropdown-chevron")));
        getWait10().until(ExpectedConditions.elementToBeClickable(menuDropdown)).sendKeys(Keys.RETURN);

        getDriver().findElement(By.xpath("//div[@id='breadcrumb-menu']//li//span[text()='Rename']")).click();
        WebElement renameInput = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='newName']")));
        renameInput.clear();
        renameInput.sendKeys(expectedprojectName + unsafeSymbol);
        new Actions(getDriver()).click(getDriver().findElement((By.cssSelector(".error")))).perform();

        String errorNotification = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error"))).getText();

        Assert.assertEquals(errorNotification, String.format("‘%s’ is an unsafe character", unsafeSymbol));

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        String errorPageHeader = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']//h1"))).getText();
        String errorPageMessage = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']//p"))).getText();

        Assert.assertEquals(errorPageHeader, "Error");
        Assert.assertEquals(errorPageMessage, String.format("‘%s’ is an unsafe character", htmlUnsafeSymbol));
    }
}