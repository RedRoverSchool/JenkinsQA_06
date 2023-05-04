package school.redrover;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openqa.selenium.By.xpath;


public class GroupHighwayToAqaTest extends BaseTest {
    private static final By NEW_ITEM = xpath("//a[@href='/view/all/newJob']");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By OK_BUTTON = xpath("//*[@id='ok-button']");
    private static final By DASHBOARD = xpath("//*[@id='jenkins-head-icon']");
    private static final By SET_ITEM_NAME = By.id("name");

    @Test
    public void testAddBoardDescription() {
        String description = "Some text about dashboard";
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement addDescriptionButton = getDriver().findElement(xpath("//div/a [@id = 'description-link']"));
        addDescriptionButton.click();

        WebElement inputForm = getDriver().findElement(xpath("//div[@class = 'setting-main help-sibling']/textarea"));
        inputForm.clear();
        inputForm.sendKeys(description);

        WebElement saveButton = getDriver().findElement(xpath("//div/button[@name = 'Submit']"));
        saveButton.click();

        WebElement descriptionText = getDriver().findElement(xpath("//*[@id='description']/div[1]"));
        Assert.assertEquals(descriptionText.getText(), description);

        addDescriptionButton = getDriver().findElement(xpath("//div/a [@id = 'description-link']"));
        addDescriptionButton.click();
        getDriver().findElement(xpath("//div[@class = 'setting-main help-sibling']/textarea")).clear();

        saveButton = getDriver().findElement(xpath("//div/button[@name = 'Submit']"));
        saveButton.click();
    }

    @Test
    public void testNegativeJobWithSpecialDollSign() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        WebElement createJob = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("//span[normalize-space(.)='Create a job']")));
        createJob.click();
        WebElement inputName = getDriver().findElement(By.id("name"));
        String str = new Faker().name().firstName();
        inputName.sendKeys(str);
        WebElement firstOption = getDriver()
                .findElement(xpath("//input[@value='hudson.model.FreeStyleProject']//parent::label"));
        firstOption.click();
        WebElement okBtn = getDriver().findElement(xpath("//button[@id='ok-button']"));
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
        WebElement addDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("//a[@href='editDescription']")));
        addDescription.click();
        WebElement textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("//textarea[@name='description']")));
        textArea.click();
        String send = "Look! It is my description";
        textArea.clear();
        textArea.sendKeys(send);
        WebElement textPreviewBtn = getDriver().findElement(By.className("textarea-show-preview"));
        textPreviewBtn.click();
        WebElement textPreview = getDriver().findElement(By.className("textarea-preview"));

        Assert.assertEquals(textPreview.getText(), send);

        WebElement saveBtn = getDriver().findElement(xpath("//button[@name='Submit']"));
        saveBtn.click();
        WebElement descriptionAfterEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("//div[@id='description']/div")));

        Assert.assertEquals(descriptionAfterEdit.getText(), send);

        addDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("//a[@href='editDescription']")));
        addDescription.click();
        textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath("//textarea[@name='description']")));
        textArea.click();
        textArea.clear();
        saveBtn = getDriver().findElement(xpath("//button[@name='Submit']"));
        saveBtn.click();
    }

    @Test
    public void testCreateMultiConfigurationProjectWithDescription() {
        final String expectedDescription = "Web-application project";

        WebElement newItem = getDriver().findElement(xpath("//a[@href='/view/all/newJob']"));
        newItem.click();
        WebElement setNewItemName = getDriver().findElement(By.id("name"));
        setNewItemName.sendKeys("Project1_MultiConfigJob");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement selectMultiConfigProject = wait.until(ExpectedConditions.elementToBeClickable(
                xpath("//span[text()='Multi-configuration project']")));
        selectMultiConfigProject.click();
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(xpath("//div/button[@id= 'ok-button']")));
        okButton.click();

        WebElement textAreaDescription = getDriver().findElement(xpath("//textarea[@name='description']"));
        textAreaDescription.sendKeys("Web-application project");

        WebElement scrollBySubmitButton = getDriver().findElement(
                xpath("//div/button[contains(@class,'jenkins-button jenkins-button--primary')]"));
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView(true)", scrollBySubmitButton);
        scrollBySubmitButton.click();

        WebElement multiConfigProjectDescription = getDriver().findElement(xpath("//div[@id = 'description']/div[1]"));

        Assert.assertEquals(multiConfigProjectDescription.getText(), expectedDescription);
    }

    @Test
    public void testSideBar() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        String[] titles = new String[]{"New Item", "People", "Build History", "Manage Jenkins", "My Views"};

        List<WebElement> sideBarItems = getDriver().findElements(xpath("//div[@id = 'tasks']//div[@class = 'task ']"));

        Assert.assertEquals(titles.length, sideBarItems.size());

        for (int i = 0; i < titles.length; i++) {
            Assert.assertEquals(sideBarItems.get(i).getText(), titles[i]);
        }
    }

    @Test
    public void testCreateAJobWithAnErrorMessageAsAResult() {
        WebElement myViewsTask = getDriver().findElement(
                xpath("//a[@href='/me/my-views']")
        );
        myViewsTask.click();
        WebElement messageOnThePage = getDriver().findElement(xpath("//div[@id='main-panel']//h2"));

        Assert.assertEquals(messageOnThePage.getText(), "This folder is empty");

        WebElement createAJobBlock = getDriver().findElement(xpath("//span[text()='Create a job']"));
        createAJobBlock.click();
        WebElement okButton = getDriver().findElement(xpath("//div[@class='btn-decorator']"));
        okButton.click();
        WebElement messageInRed = getDriver().findElement(xpath("//div[@id='itemname-required']"));

        Assert.assertEquals(messageInRed.getText(), "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testNegativeSymbolForFreestyleProjectItemsName() {
        final String[] NegativeSymbol = {"!", "@", "#", "$", "%", "^", "&", "*", ";", ":", "?", "/", "<", ">", "\\", "[", "]", "|", "."};

        WebElement newItem = getDriver().findElement(xpath("//a[@href = '/view/all/newJob']"));
        newItem.click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement freestyleProjectItem = wait.until(ExpectedConditions.elementToBeClickable(
                xpath("//span[text() = 'Freestyle project']")));
        freestyleProjectItem.click();
        WebElement inputFieldItemName = getDriver().findElement(By.id("name"));

        for (String symbol : NegativeSymbol) {
            inputFieldItemName.click();
            inputFieldItemName.sendKeys((symbol));

            String expectedResult = "» " + "‘" + symbol + "’" + " is an unsafe character";

            if (symbol.equals(".")) {
                expectedResult = "» " + "“" + symbol + "”" + " is not an allowed name";
            }
            if (symbol.equals("")) {
                expectedResult = "» This field cannot be empty, please enter a valid name";
            }

            WebElement itemInvalidName = getDriver().findElement(By.id("itemname-invalid"));
            itemInvalidName.getText();

            Assert.assertEquals(itemInvalidName.getText(), expectedResult);

            inputFieldItemName.click();
            inputFieldItemName.clear();
        }
    }

    @Test
    public void testCreateDisabledFreestyleProject() {
        final String projectName = "NewFreestyleProject";
        final String expectedResult = "This project is currently disabled\nEnable";

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        getDriver().findElement(NEW_ITEM).click();
        WebElement setNameItem = getDriver().findElement(xpath("//*[@id='name']"));
        setNameItem.sendKeys(projectName);
        WebElement selectFreestyleProject = getDriver().findElement(xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]/label/span"));
        selectFreestyleProject.click();
        getDriver().findElement(OK_BUTTON).click();

        WebElement enableDisableToggle = getDriver().findElement(xpath("//span[@class='jenkins-toggle-switch__label__checked-title']"));
        enableDisableToggle.click();
        getDriver().findElement(SAVE_BUTTON).click();

        getDriver().findElement(DASHBOARD).click();
        getDriver().findElement(xpath("//span[text()='" + projectName + "']")).click();
        WebElement statusProject = getDriver().findElement(xpath("//*[@id='enable-project']"));

        Assert.assertEquals(statusProject.getText(), expectedResult);
    }

    @Test
    public void testCreateNewProject() throws InterruptedException {

        String name = "Мой проект";
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(7));

        getDriver().findElement(NEW_ITEM).click();
        WebElement writeNameOfItem = getDriver().findElement(By.id("name"));
        Thread.sleep(3000);
        writeNameOfItem.sendKeys(name);
        WebElement chooseProject = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("label")));
        chooseProject.click();
        getDriver().findElement(OK_BUTTON).click();
        WebElement saveChanges = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-button--primary")));
        saveChanges.click();
        String successMessageOfNewProject = getDriver().findElement(By.className("job-index-headline")).getText();
        Assert.assertEquals(successMessageOfNewProject, "Project " + name);

        getDriver().findElement(DASHBOARD).click();
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(By.id("name")).sendKeys(name);
        WebElement chooseProject1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("label")));
        chooseProject1.click();
        getDriver().findElement(OK_BUTTON).click();
        Thread.sleep(5000);
        String errorMessage = "Error\n" +
                "A job already exists with the name ‘Мой проект’";
        String effortMessage = getDriver().findElement(By.id("main-panel")).getText();
        Assert.assertEquals(effortMessage, errorMessage);
    }

    @Test
    public void testTitle() {
        WebElement header = getDriver().findElement(xpath("//h1"));

        String h1 = header.getText();

        Assert.assertEquals(h1, "Welcome to Jenkins!");
    }

    @Test
    public void testSearchItemWithEmptyFieldNegative() {
        getDriver().findElement(NEW_ITEM).click();

        WebDriverWait waitFor = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        waitFor.until(ExpectedConditions.visibilityOfElementLocated(xpath("//label[@for='name']")));

        WebElement itemNameField = getDriver().findElement(xpath("//div[@class='add-item-name']"));
        itemNameField.click();

        waitFor.until(ExpectedConditions.visibilityOfElementLocated(xpath("//div[@id='itemname-required']")));

        WebElement emptyFieldNotification = getDriver().findElement(xpath("//div[@id='itemname-required']"));
        String actualEmptyFieldNotificationText = emptyFieldNotification.getText();

        Assert.assertEquals(actualEmptyFieldNotificationText, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testNotificationFreestyleProjectBuiltSuccessfullyByGreenMark() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        WebDriverWait waitForSvgIcon = new WebDriverWait(getDriver(), Duration.ofSeconds(5), Duration.ofSeconds(2));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        getDriver().findElement(NEW_ITEM).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SET_ITEM_NAME)).sendKeys("first-jenkins-job");
        WebElement selectFreestyleProject = getDriver().findElement(xpath("//span[text()='Freestyle project']"));
        selectFreestyleProject.click();

        getDriver().findElement(OK_BUTTON).click();

        WebElement descriptionArea = getDriver().findElement(xpath("//textarea[@name='description']"));
        descriptionArea.sendKeys("First jenkins job");

        WebElement saveButton = getDriver().findElement(xpath("//button[@name='Submit']"));
        js.executeScript("arguments[0].click();", saveButton);

        WebElement buildNowButton = getDriver().findElement(xpath("//span[text() = 'Build Now']"));
        js.executeScript("arguments[0].click();", buildNowButton);

        waitForSvgIcon.until(ExpectedConditions.presenceOfElementLocated(xpath("//span[@class = 'build-status-icon__outer']/*[local-name() = 'svg']")));
        WebElement svgIcon = getDriver().findElement(xpath("//span[@class = 'build-status-icon__outer']/*[local-name() = 'svg']"));

        Assert.assertEquals(Color.fromString(svgIcon.getCssValue("color")).asHex(), "#1ea64b");
    }

    @Test
    public void testCreateNewUser() {
        String userName = createUser();
        List<WebElement> users = getDriver().findElements(xpath("//a[@class ='jenkins-table__link model-link inside']"));
        Assert.assertTrue(isUserExist(users, userName));
        deleteUser(userName);
    }

    @Test
    public void testDeleteUser() {
        String userName = createUser();
        deleteUser(userName);
        List<WebElement> users = getDriver().findElements(xpath("//a[@class ='jenkins-table__link model-link inside']"));
        Assert.assertFalse(isUserExist(users, userName));
    }

    private String createUser() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));

        WebElement manageJenkinsTab = getDriver().findElement(xpath("//a[@href = '/manage']"));
        wait.until(ExpectedConditions.elementToBeClickable(manageJenkinsTab)).click();

        WebElement manageUsersSection = getDriver().findElement(xpath("//a[@href = 'securityRealm/']"));
        manageUsersSection.click();

        WebElement createUserBtn = getDriver().findElement(xpath("//a[@href = 'addUser']"));
        createUserBtn.click();

        WebElement userNameField = getDriver().findElement(xpath("//input[@name = 'username']"));
        String userName = generateName();
        userNameField.sendKeys(userName);

        WebElement passwordField = getDriver().findElement(xpath("//input[@name = 'password1']"));
        String password = generatePassword();
        passwordField.sendKeys(password);

        WebElement confirmPasswordField = getDriver().findElement(xpath("//input[@name = 'password2']"));
        confirmPasswordField.sendKeys(password);

        WebElement fullNameField = getDriver().findElement(xpath("//input[@name = 'fullname']"));
        fullNameField.sendKeys(userName + " " + generateLastName());

        WebElement emailField = getDriver().findElement(xpath("//input[@name = 'email']"));
        emailField.sendKeys(generateEmail());

        WebElement createBtn = getDriver().findElement(xpath("//button[@name = 'Submit']"));
        createBtn.click();
        return userName;
    }

    private void deleteUser(String userName) {
        WebElement trashBtn = getDriver().findElement(xpath("//a[@href = 'user/" + userName.toLowerCase() + "/delete']"));
        trashBtn.click();

        WebElement confirmBtn = getDriver().findElement(xpath("//button[@name = 'Submit']"));
        confirmBtn.click();
    }

    private String generateName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    private String generatePassword() {
        Faker faker = new Faker();
        return faker.internet().password(5, 10, true, true, true);
    }

    private String generateLastName() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    private String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    private boolean isUserExist(List<WebElement> list, String name) {
        for (WebElement el : list) {
            if (el.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testTitlesOnManageJenkinsPage() {
        WebElement buttonManageJenkins = getDriver().findElement(xpath("//span[contains(text(), 'Manage Jenkins')]/.."));
        buttonManageJenkins.click();

        List<WebElement> sectionTitles = getDriver().findElements(xpath("//h2[@class='jenkins-section__title']"));

        List<String> actualTitles = new ArrayList<>();

        for (WebElement title : sectionTitles) {
            actualTitles.add(title.getText());
        }

        List<String> expectedTitles = Arrays.asList("System Configuration", "Security", "Status Information", "Troubleshooting", "Tools and Actions");

        Assert.assertEquals(actualTitles, expectedTitles);
    }

    @Test
    public void testIconSizeButtonsOnBuildHistoryPageAreDisplayedAndClickable() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        WebElement buildHistoryLink = getDriver().findElement(xpath("//a[@href='/view/all/builds']"));
        buildHistoryLink.click();

        List<WebElement> iconSizeButtons = getDriver().findElements(xpath("//div[@class='jenkins-icon-size__items jenkins-buttons-row']"));

        for (WebElement checkedElement : iconSizeButtons) {
            if (checkedElement.isEnabled() && checkedElement.isDisplayed()) {
                wait.until(ExpectedConditions.visibilityOf(checkedElement));
                wait.until(ExpectedConditions.elementToBeClickable(checkedElement));
            } else {
                Assert.fail("Icon size button is not clickable or visible.");
            }
        }
        Assert.assertTrue(iconSizeButtons.size() > 0, "No icon size buttons found on the page.");
    }

=======

    @Test
    public void testCreateFolder() {
        getDriver().findElement(NEW_ITEM).click();

        getDriver().findElement(SET_ITEM_NAME).sendKeys("New folder");

        getDriver().findElement(By.xpath("//input[@value='com.cloudbees.hudson.plugins.folder.Folder']/..")).click();

        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(DASHBOARD).click();

        WebElement folderName = getDriver().findElement(By.xpath("(//a[@class='jenkins-table__link model-link inside'])[1]"));

        Assert.assertEquals(folderName.getText(), "New folder");
    }
}


    @Test
    public void testCreateNewPipeline(){
        String name = "Мой проект";
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(SET_ITEM_NAME).sendKeys(name);
        WebElement pipeline = getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']/ul/li[2]"));
        pipeline.click();
        getDriver().findElement(OK_BUTTON).click();
        WebElement scrollBySelectButton = getDriver().findElement(
                xpath("//div[@class = 'samples']"));
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView(true)", scrollBySelectButton);
        scrollBySelectButton.click();
        Select selectPipelineScript = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class = 'samples']/select"))));
        selectPipelineScript.selectByVisibleText("Hello World");
        WebElement saveChanges = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-button--primary")));
        saveChanges.click();
        WebElement nameOfPipeline = getDriver().findElement(xpath("//h1[@class='job-index-headline page-headline']"));
        String nameOfPipeline1 = nameOfPipeline.getText();
        Assert.assertEquals(nameOfPipeline1, "Pipeline "+ name);
    }
}