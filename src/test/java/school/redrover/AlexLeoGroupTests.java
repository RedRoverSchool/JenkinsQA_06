package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class AlexLeoGroupTests extends BaseTest {

    @Test
    public void testVerifyLogoJenkinsIsPresent() {
        WebElement element = getDriver().findElement(By.cssSelector("img#jenkins-head-icon"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test
    public void testVerifyWordIconJenkinsPresent() {
        WebElement logoWord = getDriver()
                .findElement(By.id("jenkins-name-icon"));
       Assert.assertTrue(logoWord.isDisplayed());
    }

    @Test
    public void validateJenkinsLoginTest() {
        String verify = getDriver().findElement(By.xpath("//div[@class='empty-state-block']/h1")).getText();
        Assert.assertEquals(verify, "Welcome to Jenkins!");
    }

    @Test
    public void testVerifyPeopleLinkTextPresentOnHomePage() {
        List<WebElement> elems = getDriver().findElements(By.xpath("//span[@class = 'task-link-text']"));
        String PeopleText = elems.get(1).getText();

        Assert.assertEquals(PeopleText, "People");

    }

    @Test
    public void testVerifyBuildQueueTextPresentOnHomePage() {
        List<WebElement> elems = getDriver().findElements(By.xpath("//span[@class = 'pane-header-title']"));
        String BuildQueueText = elems.get(0).getText();

        Assert.assertEquals(BuildQueueText, "Build Queue");
    }

    @Test
    public void testVerifyIconButtonsRowPresent() {
        getDriver().get("http://localhost:8080/asynchPeople/");
        WebElement iconButtonsRow = getDriver().
                findElement(By.xpath("//div[contains(@class, 'jenkins-buttons-row')]"));

        Assert.assertTrue(iconButtonsRow.isDisplayed());
    }

    @Test
    public void testVerifyFolderLabelFont() {
        getDriver().get("http://localhost:8080/view/all/newJob");
        WebElement elem = getDriver().findElement(By.id("items"));
        List<WebElement> items = elem.findElements(By.cssSelector("li span"));

        for ( WebElement element  : items) {
            Assert.assertTrue(element.getAttribute("baseURI").contains("newJob"));
        }
    }
}
