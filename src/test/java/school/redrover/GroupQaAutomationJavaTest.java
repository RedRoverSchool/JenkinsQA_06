package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupQaAutomationJavaTest extends BaseTest {
    @Test
    public void testFirst () throws InterruptedException  {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");
        String title = getDriver().getTitle();

        Assert.assertEquals("Web form", title);

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));
        Thread.sleep(2000);

        textBox.sendKeys("Selenium");
        Thread.sleep(2000);
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        Thread.sleep(2000);

        Assert.assertEquals("Received!", value);
    }
  /*  @Test
    public void testCheckMenuAfterPushButtonPeople () {
        getDriver().findElement(By.linkText("People")).click();

        WebElement one = getDriver().findElement(By.xpath("//h1"));
        Assert.assertEquals(one.getText(),"People");

        List<String> expectedMenu = Arrays.asList("User ID", "Name", "Last Commit Activity", "On");
        /*List < String > dataInTable = new ArrayList<>();
        WebElement userId = getDriver().findElement(By.linkText("User ID"));
        WebElement name = getDriver().findElement(By.xpath("//*[@id=\"people\"]/thead/tr/th[3]/a"));
        WebElement lastCommitActivity = getDriver().findElement(By.xpath("//*[@id=\"people\"]/thead/tr/th[4]/a"));
        WebElement on = getDriver().findElement(By.xpath( "//*[@id=\"people\"]/thead/tr/th[5]/a"));
        dataInTable.add(userId.getText());
        dataInTable.add(name.getText());
        dataInTable.add(lastCommitActivity.getText());
        dataInTable.add(on.getText());*/
/*
        List<WebElement> titles =  getDriver().findElements(By.xpath("//a[@class = 'sortheader']")); // локатор на все элементы в таблице
        List<String> actualMenu = new ArrayList<>();

        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).getText().contains("↑")) {
                actualMenu.add(titles.get(i).getText().replace("↑", "").trim());
            } else {
                actualMenu.add(titles.get(i).getText());
            }
        }
        Assert.assertEquals(actualMenu, expectedMenu);
    }
} */
}
