package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class RockstarTest extends BaseTest {

    @Test
    public void welcomeJenkinsTest() {

        WebElement welcomeElement = getDriver().findElement(By.xpath("//div[@class = \"empty-state-block\"]/h1"));

        Assert.assertEquals(welcomeElement.getText(), "Добро пожаловать в Jenkins!");
    }

    @Test
    public void createItemWorksTest() throws InterruptedException {
        Thread.sleep(2000);
        WebElement createItem = getDriver().findElement(By.xpath("//a[@href=\"/view/all/newJob\"][@class=\"task-link \"]"));


        createItem.click();
        Thread.sleep(2000);

        WebElement nameHeader = getDriver().findElement((By.xpath("//label[@for=\"name\"]")));
        Assert.assertEquals(nameHeader.getText(),"Введите имя Item'а");

    }
    @Test
    public void itemUsersWorksTest() throws InterruptedException {
        WebElement usersButton = getDriver().findElement(By.xpath("//a[@href=\"/asynchPeople/\"]"));

        usersButton.click();
        Thread.sleep(2000);

        WebElement usersString = getDriver().findElement(By.xpath("//div[@class=\"jenkins-app-bar__content\"]/h1"));
        Assert.assertEquals(usersString.getText(),"Пользователи");
    }
}
