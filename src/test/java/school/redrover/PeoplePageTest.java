package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class PeoplePageTest extends BaseTest {
    private static final By CREATE_USER = By.xpath("//a[@href = 'addUser']");
    private static final By USER_NAME = By.xpath("//input[@id = 'username']");
    private static final By PASSWORD = By.xpath("//input[@name= 'password1']");
    private static final By CONFIRM_PASSWORD = By.xpath("//input[@name= 'password2']");
    private static final By FULL_NAME = By.xpath("//input[@name= 'fullname']");
    private static final By EMAIL_ADRESS = By.xpath("//input[@name='email']");
    private static final By SUBMIT = By.xpath("//button[@name ='Submit']");

    public static List<String> ListText(List<WebElement> elementList) {
        List<String> stringList = new ArrayList<>();
        for (WebElement element : elementList) {
            stringList.add(element.getText());
        }
        return stringList;
    }

    @Test
    public void testViewPeoplePage() {
        getDriver().findElement(By.xpath("//span/a[@href='/asynchPeople/']")).click();
        WebElement nameOfPeoplePageHeader = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(nameOfPeoplePageHeader.getText(), "People");
    }

    @Test
    public void testViewIconButtonsPeoplePage() {
        List expectedIconButtonsNames = List.of("S" + "\n" + "mall", "M" + "\n" + "edium", "L" + "\n" + "arge");

        getDriver().findElement(By.xpath("//span/a[@href='/asynchPeople/']")).click();
        List<WebElement> iconButtons = getDriver().findElements(By.xpath("//div[@class='jenkins-icon-size']//ol/li"));
        List<String> actualIconButtonsNames = ListText(iconButtons);

        Assert.assertEquals(actualIconButtonsNames, expectedIconButtonsNames);
    }

    @Test
    public void testSortArrowModeChangesAfterClickingSortHeaderButton() {
        getDriver().findElement(By.xpath("//span/a[@href='/asynchPeople/']")).click();

        WebElement userIdBtnNoSortArrowBeforeClick = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span"));
        Assert.assertTrue(userIdBtnNoSortArrowBeforeClick.getText().isEmpty());

        getDriver().findElement(By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]")).click();

        String userIdBtnSortArrowUpAfterFirstClick = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span")).getText();
        Assert.assertEquals(userIdBtnSortArrowUpAfterFirstClick, "  ↑");

        getDriver().findElement(By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]")).click();

        String userIdBtnSortArrowDownAfterSecondClick = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span")).getText();
        Assert.assertEquals(userIdBtnSortArrowDownAfterSecondClick, "  ↓");

            getDriver().findElement(By.xpath("//a[@class='sortheader'][contains(text(), 'Name')]")).click();

        WebElement userIdBtnNoArrowAfterAnotherButtonClick = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span"));
        Assert.assertTrue(userIdBtnNoArrowAfterAnotherButtonClick.getText().isEmpty());
    }

    @Test
    public void testSearchPeople() {
        String expectedUserName = "admin";

        WebElement searchField = getDriver().findElement(
                By.xpath("//input[@name='q']"));
        searchField.sendKeys(expectedUserName);
        searchField.sendKeys(Keys.RETURN);

        WebElement actualUserName = getDriver().findElement(
                By.xpath("//div[contains(text(), 'Jenkins User ID:')]"));

        Assert.assertEquals(actualUserName.getText(), "Jenkins User ID: " + expectedUserName);
    }
    @Test
    public void testPeopleSortingListOfUsers() {

        getDriver().findElement(By.linkText("Manage Jenkins")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href = 'securityRealm/']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(CREATE_USER)).click();
        getDriver().findElement(USER_NAME).sendKeys("Peter");
        getDriver().findElement(PASSWORD).sendKeys("12");
        getDriver().findElement(CONFIRM_PASSWORD).sendKeys("12");
        getDriver().findElement(FULL_NAME).sendKeys("Peter Pen");
        getWait10().until(ExpectedConditions.elementToBeClickable(EMAIL_ADRESS)).sendKeys("pen@gmail.com");
        getWait2().until(ExpectedConditions.elementToBeClickable(SUBMIT)).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(CREATE_USER)).click();
        getDriver().findElement(USER_NAME).sendKeys("Mark");
        getDriver().findElement(PASSWORD).sendKeys("34");
        getDriver().findElement(CONFIRM_PASSWORD).sendKeys("34");
        getDriver().findElement(FULL_NAME).sendKeys("Mark Sobol");
        getWait10().until(ExpectedConditions.elementToBeClickable(EMAIL_ADRESS)).sendKeys("mark@gmail.com");
        getWait2().until(ExpectedConditions.elementToBeClickable(SUBMIT)).click();
        getDriver().findElement(By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span")).click();

        WebElement actualResult1 = getDriver().findElement(By.linkText("admin"));
        Assert.assertEquals(actualResult1.getText(),"admin");

        WebElement actualResult2 = getDriver().findElement(By.linkText("Mark"));
        Assert.assertEquals(actualResult2.getText(),"Mark");

        WebElement actualResult3 = getDriver().findElement(By.linkText("Peter"));
        Assert.assertEquals(actualResult3.getText(),"Peter");

        String arrowUp = getDriver().findElement(By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span")).getText();
        Assert.assertEquals(arrowUp, "  ↑");

    }
}

