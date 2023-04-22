package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineTest extends BaseTest {

    public void scrollByElement(By by) throws InterruptedException {
        WebElement scroll =getDriver().findElement(by);
        new Actions(getDriver())
                .scrollToElement(scroll)
                .perform();
    }
    @Test
    public void testCreatePipeline() throws InterruptedException {

        WebElement jobName = getDriver().findElement(By.cssSelector(".content-block__link>span"));
        jobName.click();

        WebElement field = getDriver().findElement(By.cssSelector("#name"));
        field.sendKeys("Pipe");

        WebElement pipeline = getDriver().findElement(By.xpath("//span[text()='Pipeline']"));
        pipeline.click();

        WebElement buttonOk = getDriver().findElement(By.cssSelector("#ok-button"));
        buttonOk.click();

        Thread.sleep(3000);

        scrollByElement(By.cssSelector(".page-footer__links.page-footer__links--white.jenkins_ver>a"));
        Thread.sleep(3000);

        WebElement save = getDriver().findElement(By.cssSelector(".jenkins-button.jenkins-button--primary"));
        Thread.sleep(3000);
        save.click();

        WebElement pipelinePipe= getDriver().findElement(By.cssSelector("#main-panel>h1"));

        Assert.assertEquals(pipelinePipe.getText(), "Pipeline Pipe");

    }

}
