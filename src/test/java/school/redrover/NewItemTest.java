package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewItemTest extends BaseTest {

    @Test
    public void testNewItemHeader() {
        getDriver().findElement(By.linkText("New Item")).click();

        WebElement h3Header = new WebDriverWait(getDriver(), Duration.ofMillis(3000))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class = 'h3']")));
        String actualResult = h3Header.getText();

        Assert.assertEquals(actualResult, "Enter an item name");
    }

    @Ignore
    @Test
    public void testVerifyNewItemsList() {
        List<String> listOfNewItemsExpect = Arrays.asList("Freestyle project", "Pipeline", "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder");

        getDriver().findElement(By.cssSelector("a[href='/view/all/newJob']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label > span")));
        List<WebElement> listOfNewItems = getDriver().findElements(By.cssSelector("label > span"));

        for (int i = 0; i < listOfNewItemsExpect.size(); i++) {
            Assert.assertEquals(listOfNewItems.get(i).getText(), listOfNewItemsExpect.get(i));
        }
    }

    @Ignore
    @Test
    public void testVerifyButtonIsDisabled() {
        getDriver().findElement(By.cssSelector("a[href='/view/all/newJob']")).click();

        WebElement button = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("ok-button")));

        Assert.assertFalse(button.isEnabled());
    }

    @Test
    public void testErrorWhenCreateNewItemWithSpecialCharacterName() {
        String expectedErrorMessage = "» ‘@’ is an unsafe character";

        getDriver()
                .findElement(By.xpath("//a[@href='/view/all/newJob']"))
                .click();
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.id("name")))
                .sendKeys("@");

        String actualErrorMessage = getDriver()
                .findElement(By.xpath("//div[@id='itemname-invalid']"))
                .getText();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void testCreateFreestyleProjectWithEmptyNameError() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".hudson_model_FreeStyleProject")))
                .click();

        WebElement errorMsg = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-required")));
        Assert.assertEquals(errorMsg.getText(), "» This field cannot be empty, please enter a valid name");
    }

    public void createProject(String nameOfProject, String typeOfProject){
        getDriver().findElement(By.className("task-icon-link")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("name"))).sendKeys(nameOfProject);

        List <WebElement> listItemOptions = getDriver().findElements(By.id("j-add-item-type-standalone-projects"));
        for(WebElement element:listItemOptions){
            if (element.getText().contains(typeOfProject)){
                element.click();
            }
        }
    }

    @Test
    public void createPipelineProjectTest(){
        String expectedResult = "New pipeline project";
        String typeOfProject = "Pipeline";
        createProject(expectedResult, typeOfProject);
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//*[text()='Dashboard']")).click();

        List <WebElement> listOfCreateProjects = getDriver().findElements(By.xpath("//table[@id='projectstatus']//a[@class='jenkins-table__link model-link inside']"));
        List <String> nameOfCreateProjects = new ArrayList<>();
        for(WebElement element : listOfCreateProjects){
            nameOfCreateProjects.add(element.getText());
        }

        Assert.assertEquals(nameOfCreateProjects.get(0), expectedResult);
        Assert.assertEquals(nameOfCreateProjects.size(), 1);
    }

    @Test
    public void createPipelineProjectWithInvalidNameTest(){
        String[] invalidChars = new String[] {"!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "/", "|", "?", "<", ">"};
        String typeOfProject = "Pipeline";
        for (String invalidChar : invalidChars) {
            createProject(invalidChar, typeOfProject);
            String validationMessage = getDriver().findElement(By.id("itemname-invalid")).getText();
            Assert.assertEquals(validationMessage, "» ‘" + invalidChar + "’ is an unsafe character");
            Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
            getDriver().findElement(By.xpath("//a[contains(text(), 'Dashboard')]")).click();
        }
    }
}
