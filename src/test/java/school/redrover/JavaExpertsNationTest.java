package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class JavaExpertsNationTest extends BaseTest {
    private List<String> getTextFromELementsList(List<WebElement> ElementsList) {
        if (ElementsList.size() > 0) {
            List<String> resultList = new ArrayList<>();
            for (WebElement el : ElementsList) {
                resultList.add(el.getText());
            }
            return resultList;
        }
        return List.of("Error");
    }

    @Test
    public void testManageTitle() {
        getDriver().findElement(By.cssSelector("a[href='/manage']")).click();

        WebElement manageTitle = getDriver().findElement(By.tagName("h1"));
        WebElement securityTitle = getDriver().findElement(By.cssSelector("#main-panel section:nth-child(6) h2"));

        Assert.assertEquals(manageTitle.getText(), "Manage Jenkins");
        Assert.assertEquals(securityTitle.getText(), "Security");

        WebElement systemConfigurationTitle = getDriver().
                findElement(By.xpath("//*[@id=\"main-panel\"]/section[2]/h2"));

        Assert.assertEquals(systemConfigurationTitle.getText(), "System Configuration");
    }

    @Test
    public void testPeopleTitle() {
        WebElement people = getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']"));
        people.click();

        WebElement peopleTitle = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(peopleTitle.getText(), "People");
    }

    @Test
    public void testBuildHistoryTitle() {
        WebElement buildHistory = getDriver().findElement(By.xpath("//a[@href='/view/all/builds']"));
        buildHistory.click();

        WebElement peopleTitle = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(peopleTitle.getText(), "Build History of Jenkins");
    }

    @Test
    public void testAddDescription(){
        WebElement addDescLink = getDriver().findElement(By.id("description-link"));
        addDescLink.click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea[name = 'description']")));

        WebElement inputField = getDriver().findElement(By.cssSelector("textarea[name = 'description']"));
        inputField.clear();

        String text = "Some description";
        inputField.sendKeys(text);

        WebElement saveButton = getDriver().findElement(By.cssSelector("button[name='Submit']"));
        saveButton.click();

        WebElement actualResult = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));
        Assert.assertEquals(actualResult.getText(), text);

    }

    @Test
    public void testAddNewItem() {
        WebElement newItem = getDriver().findElement(By.cssSelector("a[href='/view/all/newJob']"));
        newItem.click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        WebElement inputField = getDriver().findElement(By.id("name"));
        String firstJob = "First Job";
        inputField.sendKeys(firstJob);

        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]/label/span")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();
        WebElement projectTitle = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(projectTitle.getText(), "Project " + firstJob, "projectTitle does not match inputted text");
    }

    @Test
    public void testDropDownMenuDashboard() {
        List<String> expectedMenuItems = Arrays.asList("New Item", "People", "Build History", "Manage Jenkins", "My Views");

        WebElement hoverable = getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']/ol/li/a"));
        new Actions(getDriver())
                .moveToElement(hoverable)
                .perform();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@id='breadcrumbBar']/ol/li/a/button[@class='jenkins-menu-dropdown-chevron']")
                ));

        getDriver().findElement(
                By.xpath("//div[@id='breadcrumbBar']/ol/li/a/button[@class='jenkins-menu-dropdown-chevron']")
        ).click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='breadcrumb-menu']/div")));

        List<WebElement> elements = getDriver().findElements(
                By.xpath("//div[@id='breadcrumb-menu']/div/ul/li/a/span")
        );

        for (WebElement e : elements) {
            System.out.println(e.getText());
        }

        Assert.assertEquals(getTextFromELementsList(elements), expectedMenuItems);
    }

    @Test
    public void testDropDownMenuDashboardManageJenkins(){
        List<String> expectedMenuManageJenkinsItems = Arrays.asList(
                "System Configuration", "Configure System", "Global Tool Configuration", "Manage Plugins",
                "Manage Nodes and Clouds", "Security", "Configure Global Security", "Manage Credentials",
                "Configure Credential Providers", "Manage Users", "Status Information", "System Information",
                "System Log", "Load Statistics", "About Jenkins", "Troubleshooting", "Manage Old Data",
                "Tools and Actions", "Reload Configuration from Disk", "Jenkins CLI", "Script Console",
                "Prepare for Shutdown");

        getDriver().manage().window().maximize();
        WebElement hoverableDashbord = getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']/ol/li/a"));
        new Actions(getDriver())
                .moveToElement(hoverableDashbord)
                .perform();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='breadcrumbBar']/ol/li/a/button[@class='jenkins-menu-dropdown-chevron']")
                ));

        getDriver().findElement(
                By.xpath("//div[@id='breadcrumbBar']/ol/li/a/button[@class='jenkins-menu-dropdown-chevron']")
        ).click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='breadcrumb-menu']/div")));

        WebElement hoverableManageJenkins = getDriver().findElement(
                By.xpath("//div[@id='breadcrumb-menu']/div/ul/li/a/span[contains(text(),'Manage Jenkins')]")
        );
        new Actions(getDriver())
                .moveToElement(hoverableManageJenkins)
                .perform();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='submenu0']")));

        List<WebElement> elements = getDriver().findElements(
                By.xpath("//div[@id='submenu0']/div/ul/li/a/span")
        );

        Assert.assertEquals(getTextFromELementsList(elements), expectedMenuManageJenkinsItems);
    }
}
