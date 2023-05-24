package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

    public class FreestyleJobTest extends BaseTest {
        private static final String NAME_PROJECT = "Hello world";

        public void scroll(int deltaY) {
            new Actions(getDriver())
                    .scrollFromOrigin(WheelInput.ScrollOrigin.fromViewport(), 0, deltaY)
                    .perform();
        }

        public void clickPerform(By by) {
            new Actions(getDriver())
                    .click(getDriver().findElement(by))
                    .perform();
        }

        public void clickPerform(WebElement webElement) {
            new Actions(getDriver())
                    .click(webElement)
                    .perform();
        }

        public void createFreestyleProjectJob(String nameProject) {
            getDriver().findElement(
                    By.xpath("//*[@id='tasks']/div[1]/span/a")
            ).click();
            getDriver().findElement(
                    By.name("name")
            ).sendKeys(NAME_PROJECT);
            getDriver().findElement(
                    By.cssSelector(".icon-freestyle-project")
            ).click();

            clickPerform(By.xpath("//*[@id='ok-button']"));
            getDriver().findElement(
                    By.xpath("//*[@name='description']")
            ).sendKeys(NAME_PROJECT.concat(" java test program"));
            scroll(600);

            clickPerform(getWait2().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@for='radio-block-1']"))));
            getWait2().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@checkdependson='credentialsId']")
            )).sendKeys("https://github.com/kriru/firstJava.git");
            scroll(2000);

            getWait2().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='yui-gen9-button']")
            )).click();
            getWait2().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='yui-gen24']")
            )).click();
            getWait2().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@name='description']")
            )).sendKeys("javac ".concat(NAME_PROJECT.concat(".java\njava ".concat(NAME_PROJECT))));

            getWait2().until(ExpectedConditions.elementToBeClickable(
                    By.name("Apply")
            )).click();
            getWait2().until(ExpectedConditions.elementToBeClickable(
                    By.name("Submit")
            )).click();
            clickPerform(getWait2().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='tasks']/div[4]/span/a"))));
            getWait5().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@class='model-link inside build-link display-name']")
            )).click();
            getWait5().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@class='icon-terminal icon-xlg']")
            )).click();
        }

        @Test
        public void testFreestyleJob() {
            createFreestyleProjectJob("Hello world");

            Assert.assertTrue(getWait5().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@class='console-output']")
            )).getText().contains("Finished: SUCCESS"));
    }
}
