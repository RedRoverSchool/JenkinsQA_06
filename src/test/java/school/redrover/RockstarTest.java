package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class RockstarTest extends BaseTest {

    @Test
    public void welcomeJenkinsTest() {

        WebElement welcomeElement = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class = \"empty-state-block\"]/h1")));
        Assert.assertEquals(welcomeElement.getText(), "Добро пожаловать в Jenkins!");
    }

    @Test
    public void createItemWorksTest() {

        WebElement createItem = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href=\"/view/all/newJob\"][@class=\"task-link \"]")));
        createItem.click();

        WebElement nameHeader = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for=\"name\"]")));
        Assert.assertEquals(nameHeader.getText(),"Введите имя Item'а");
    }
    @Test
    public void itemUsersWorksTest() {

        WebElement usersButton = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href=\"/asynchPeople/\"]")));
        usersButton.click();

        WebElement usersString = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"jenkins-app-bar__content\"]/h1")));
        Assert.assertEquals(usersString.getText(),"Пользователи");
    }
}
