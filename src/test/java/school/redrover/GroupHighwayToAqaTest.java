package school.redrover;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;
import java.util.List;


public class GroupHighwayToAqaTest extends BaseTest {

    @Test
    public void testAddBoardDescription() {
        String description = "Some text about dashboard";
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement addDescriptionButton = getDriver().findElement(By.xpath("//div/a [@id = 'description-link']"));
        addDescriptionButton.click();

        WebElement inputForm = getDriver().findElement(By.xpath("//div[@class = 'setting-main help-sibling']/textarea"));
        inputForm.clear();
        inputForm.sendKeys(description);

        WebElement saveButton = getDriver().findElement(By.xpath("//div/button[@name = 'Submit']"));
        saveButton.click();

        WebElement descriptionText = getDriver().findElement(By.xpath("//*[@id='description']/div[1]"));
        Assert.assertEquals(descriptionText.getText(), description);

        addDescriptionButton = getDriver().findElement(By.xpath("//div/a [@id = 'description-link']"));
        addDescriptionButton.click();
        getDriver().findElement(By.xpath("//div[@class = 'setting-main help-sibling']/textarea")).clear();

        saveButton = getDriver().findElement(By.xpath("//div/button[@name = 'Submit']"));
        saveButton.click();
    }

    @Test
    public void testNegativeJobWithSpecialDollSign() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        WebElement createJob = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//span[normalize-space(.)='Create a job']")));
        createJob.click();
        WebElement inputName = getDriver().findElement(By.id("name"));
        String str = new Faker().name().firstName();
        inputName.sendKeys(str);
        WebElement firstOption = getDriver()
                .findElement(By
                        .xpath("//input[@value='hudson.model.FreeStyleProject']//parent::label"));
        firstOption.click();
        WebElement okBtn = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        inputName.sendKeys("$");
        WebElement firstCheck = getDriver().findElement(By.id("itemname-invalid"));

        Assert.assertEquals(firstCheck.getText(), "» ‘$’ is an unsafe character");

        okBtn.click();
        WebElement errorMessage = getDriver().findElement(By.cssSelector("div[id='main-panel'] h1"));

        Assert.assertEquals(errorMessage.getText(), "Error");

        WebElement errorSubMessage = getDriver().findElement(By.cssSelector("div[id='main-panel'] p"));

        Assert.assertEquals(errorSubMessage.getText(), "‘$’ is an unsafe character");
    }

    @Test
    public void testDescriptionOnMainPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        WebElement addDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//a[@href='editDescription']")));
        addDescription.click();
        WebElement textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//textarea[@name='description']")));
        textArea.click();
        String send = "Look! It is my description";
        textArea.clear();
        textArea.sendKeys(send);
        WebElement textPreviewBtn = getDriver().findElement(By.className("textarea-show-preview"));
        textPreviewBtn.click();
        WebElement textPreview = getDriver().findElement(By.className("textarea-preview"));

        Assert.assertEquals(textPreview.getText(), send);

        WebElement saveBtn = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveBtn.click();
        WebElement descriptionAfterEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@id='description']/div")));

        Assert.assertEquals(descriptionAfterEdit.getText(), send);

        addDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//a[@href='editDescription']")));
        addDescription.click();
        textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//textarea[@name='description']")));
        textArea.click();
        textArea.clear();
        saveBtn = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveBtn.click();
    }

    @Test
    public void testCreateMultiConfigurationProjectWithDescription() {
        final String expectedDescription = "Web-application project";

        WebElement newItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItem.click();
        WebElement setNewItemName = getDriver().findElement(By.id("name"));
        setNewItemName.sendKeys("Project1_MultiConfigJob");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement selectMultiConfigProject = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Multi-configuration project']")));
        selectMultiConfigProject.click();
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/button[@id= 'ok-button']")));
        okButton.click();

        WebElement textAreaDescription = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        textAreaDescription.sendKeys("Web-application project");

        WebElement scrollBySubmitButton = getDriver().findElement(
                By.xpath("//div/button[contains(@class,'jenkins-button jenkins-button--primary')]"));
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView(true)", scrollBySubmitButton);
        scrollBySubmitButton.click();

        WebElement multiConfigProjectDescription = getDriver().findElement(By.xpath("//div[@id = 'description']/div[1]"));

        Assert.assertEquals(multiConfigProjectDescription.getText(), expectedDescription);
    }

    @Test
    public void testSideBar() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        String[] titles = new String[]{"New Item", "People", "Build History", "Manage Jenkins", "My Views"};

        List<WebElement> sideBarItems = getDriver().findElements(By.xpath("//div[@id = 'tasks']//div[@class = 'task ']"));

        Assert.assertEquals(titles.length, sideBarItems.size());

        for (int i = 0; i < titles.length; i++) {
            Assert.assertEquals(sideBarItems.get(i).getText(), titles[i]);
        }
    }
}


