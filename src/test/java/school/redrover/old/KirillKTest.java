package school.redrover.old;
import org.testng.annotations.Ignore;
import school.redrover.runner.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;
@Ignore
public class KirillKTest extends BaseTest {

    @Test
    public void testRedRoverSiteLanguage() {

        WebDriver driver = getDriver();
        Actions actions = new Actions(getDriver());
        //setResolution(1920, 1080);

        driver.get("https://redrover.school/");

        WebElement langDropdownBase = driver.findElement(By.cssSelector("a[href='#submenu:more']"));
        actions.moveToElement(langDropdownBase).perform();

        WebElement langOptionRu = driver.findElement(By.cssSelector("a[href='/ru']"));
        actions.moveToElement(langOptionRu);
        actions.click(langOptionRu).perform();

        WebElement boldHeading = driver.findElement(By.cssSelector("h2[class='tn-atom']"));
        Assert.assertEquals(boldHeading.getText(), "Обучение");

    }
}
