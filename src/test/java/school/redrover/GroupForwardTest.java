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
@Ignore
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

        Assert.assertEquals(description.getText(), text);
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

        for (WebElement element : listOfJobs) {
            textList1.add(element.getText());
        }

        Assert.assertEquals(textList1, textList);
    }

    @Test
    public void testErrorMessageNewJob() throws InterruptedException {
        final String errorMessage = "» This field cannot be empty, please enter a valid name";

        WebElement newItemMenu = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        newItemMenu.click();
        Thread.sleep(2000);
        WebElement freestyleProjectButton = getDriver().findElement(By.xpath("//li//span[contains(text(), 'Freestyle')]"));
        freestyleProjectButton.click();

        Assert.assertEquals(getDriver().findElement(By.id("itemname-required")).getText(), errorMessage);
    }



    @Test
    public void testListOfBuildHistory() {

        WebElement newItemMenu = getDriver().findElement(By.xpath("//a[@href = '/view/all/builds']"));
        newItemMenu.click();
        WebElement iconLegendButton = getDriver().findElement(By.xpath("//a[@href = '/legend']"));
        iconLegendButton.click();

        WebElement textProjectHealth = getDriver().findElement(By.xpath("//div/h2[text()='Project Health']"));
        List<WebElement> listProjectHealth = getDriver().findElements(By.xpath("//div/dl[@class='app-icon-legend'][2]/dd"));

        Assert.assertEquals(textProjectHealth.getText(), "Project Health");
        Assert.assertEquals(listProjectHealth.size(), 5);
    }

    private List<String> getListProject(List<WebElement> WebList) {
        if(WebList.size() > 0) {
            List<String> text = new ArrayList<>();
            for (WebElement webElement : WebList) {
                text.add(webElement.getText());
            }

            return text;
        }

        return null;
    }
    @Test
    public void testListProjectHealth(){
        WebElement newItemMenu = getDriver().findElement(By.xpath("//a[@href = '/view/all/builds']"));
        newItemMenu.click();
        WebElement iconLegendButton = getDriver().findElement(By.xpath("//a[@href = '/legend']"));
        iconLegendButton.click();
        List<WebElement> listProjectHealth = getDriver().findElements(By.xpath("//div/dl[@class='app-icon-legend'][2]/dd"));

        List<String> textListExpected = List.of("Project health is over 80%", "Project health is over 60% and up to 80%",
                "Project health is over 40% and up to 60%", "Project health is over 20% and up to 40%", "Project health is 20% or less");

        Assert.assertEquals(getListProject(listProjectHealth), textListExpected);

    }
}
