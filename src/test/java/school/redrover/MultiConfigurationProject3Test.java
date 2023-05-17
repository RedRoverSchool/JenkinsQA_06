package school.redrover;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultiConfigurationProject3Test extends BaseTest {

    @Test
    public void createDefaultMultiConfigurationProjectTest(){
        final String expectedprojectName = getProjectName();

        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name = 'name']"))).sendKeys(expectedprojectName);
        getDriver().findElement(By.cssSelector("li.hudson_matrix_MatrixProject span")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name = 'Submit']"))).click();
        getDriver().findElement(By.xpath("//a[@href='/']")).click();
        WebElement project = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#projectstatus a.model-link")));

        Assert.assertEquals(project.getText(), expectedprojectName);
    }

    private String getProjectName() {
        Faker faker = new Faker();
        return faker.funnyName().name();
    }

    @Test(dataProvider = "unsafeCharacter")
    public void verifyProjectNameCreationWithUnsafeSymbolsTest(char unsafeSymbol , String htmlUnsafeSymbol){
            getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
            getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name = 'name']")))
                    .sendKeys(getProjectName()+unsafeSymbol );
            getDriver().findElement(By.cssSelector("li.hudson_matrix_MatrixProject span")).click();
            String errorNotification = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#itemname-invalid"))).getText();

            Assert.assertEquals(errorNotification, String.format("» ‘%s’ is an unsafe character", unsafeSymbol));

            getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();

            String errorPageHeader = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']//h1"))).getText();
            String errorPageMessage = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']//p"))).getText();

            Assert.assertEquals(errorPageHeader, "Error");
            Assert.assertEquals(errorPageMessage, String.format("‘%s’ is an unsafe character", unsafeSymbol));
    }

    @DataProvider(name = "unsafeCharacter")
    public Object[][] unsafeCharacterArray() {
        return new Object[][]{{'!', "!"}, {'@', "@"}, {'#', "#"}, {'$', "$"}, {'%', "%"}, {'^', "^"}, {'&', "&amp;"}, {'*', "*"}, {'[', "["}, {']', "]"}, {'\\', "\\"}, {'|', "|"}
                , {';', ";"}, {':', ":"}, {'<', "&lt;"}, {'>', "&gt;"}, {'/', "/"}, {'?', "?"}};
    }
}