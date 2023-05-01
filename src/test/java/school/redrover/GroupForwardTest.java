package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GroupForwardTest extends BaseTest {

    @Test
    public void testAddDescription() {

        WebElement addDescriptionButton = getDriver().findElement(By.xpath("//a [@href='editDescription']"));
        addDescriptionButton.click();
        WebElement textArea = getDriver().findElement(By.xpath("//textarea[@name = 'description']"));

        Assert.assertTrue(textArea.isDisplayed());
    }

    @Test
    public void testSaveDescription() throws InterruptedException {
        final String text = "Some text is here";

        WebElement addDescriptionButton = getDriver().findElement(By.xpath("//a [@href='editDescription']"));
        addDescriptionButton.click();
        Thread.sleep(2000);
        WebElement textArea = getDriver().findElement(By.xpath("//textarea[@name = 'description']"));
        textArea.clear();
        textArea.sendKeys(text);
        WebElement submitButton = getDriver().findElement(By.xpath("//button[@name = 'Submit']"));
        submitButton.click();
        WebElement description = getDriver().findElement(By.xpath("//div[@id = 'description']/div[1]"));

        Assert.assertEquals(description.getText(),text);
    }

    @Test
    public void testDropDownCredentials() {

        WebElement dropDownList = getDriver().findElement(By.xpath("//a[@href='/user/admin']//button[@class='jenkins-menu-dropdown-chevron']"));
        Actions act = new Actions(getDriver());
        act.click(dropDownList).perform();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        WebElement credentials = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href = '/user/admin/credentials']")));
        Actions actions = new Actions(getDriver());
        actions.click(credentials).perform();

        WebElement h1CredentialsPage = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(h1CredentialsPage.getText(), "Credentials");
    }

    @Test
    public void testSearchField() {
        WebElement searchField = getDriver().findElement(By.xpath("//*[@id='search-box']"));
        searchField.click();
        searchField.sendKeys("configure");
        searchField.sendKeys(Keys.ENTER);

        WebElement searchResult = getDriver().findElement(By.xpath("//div[@id='main-panel']/div[1]/div[1]/h1"));

        Assert.assertTrue(searchResult.getText().toLowerCase().contains("configure"), "configure");

    }

    @Test
    public void testStatusButtonIsDisplayed() {
        WebElement statusButton = getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]"));

        Assert.assertTrue(statusButton.isDisplayed());

    }

    @Test
    public void testAddingNewItem() throws InterruptedException {
        final String nameOfJob = "Katya's Project";

        WebElement newItemMenu = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        newItemMenu.click();
        WebElement nameInputField = getDriver().findElement(By.id("name"));
        nameInputField.sendKeys(nameOfJob);
        WebElement freestyleProjectButton = getDriver().findElement(By.xpath("//li//span[contains(text(), 'Freestyle')]"));
        freestyleProjectButton.click();
        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();
        Thread.sleep(2000);

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Configure");
    }

    @Test
    public void testListOfJobs() throws InterruptedException {

        WebElement newItemMenu = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        newItemMenu.click();
        Thread.sleep(2000);
        List<WebElement> listOfJobs = getDriver().findElements(By.xpath("//li//span"));

        Assert.assertEquals(listOfJobs.size(), 6);

        List<String> textList1 = new ArrayList<>();
        List<String> textList = new ArrayList<>();
        textList.add("Freestyle project");
        textList.add("Pipeline");
        textList.add("Multi-configuration project");
        textList.add("Folder");
        textList.add("Multibranch Pipeline");
        textList.add("Organization Folder");

        for (WebElement element: listOfJobs) {
            textList1.add(element.getText());
        }

        Assert.assertEquals(textList1, textList);
    }
}
