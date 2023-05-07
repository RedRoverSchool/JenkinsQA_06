package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class RockstarTest extends BaseTest {

    @Test
    public void welcomeJenkinsTest() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(1));

        WebElement welcomeElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class = \"empty-state-block\"]/h1")));
        Assert.assertEquals(welcomeElement.getText(), "Добро пожаловать в Jenkins!");
    }

    @Test
    public void createItemWorksTest() {
        WebDriverWait wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(1));

        WebElement createItem = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href=\"/view/all/newJob\"][@class=\"task-link \"]")));
        createItem.click();

        WebElement nameHeader = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for=\"name\"]")));
        Assert.assertEquals(nameHeader.getText(),"Введите имя Item'а");
    }
    @Test
    public void itemUsersWorksTest() {
        WebDriverWait wait3 = new WebDriverWait(getDriver(), Duration.ofSeconds(1));

        WebElement usersButton = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href=\"/asynchPeople/\"]")));
        usersButton.click();

        WebElement usersString = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"jenkins-app-bar__content\"]/h1")));
        Assert.assertEquals(usersString.getText(),"Пользователи");
    }
}
