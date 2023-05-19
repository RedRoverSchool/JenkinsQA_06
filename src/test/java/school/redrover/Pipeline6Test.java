package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
public class Pipeline6Test extends BaseTest {

    @Test
    public void testNewItemSubmit() {

        final String name = "NewName";
        WebElement newItemBtn = getDriver().findElement(By.cssSelector("a[href='/view/all/newJob']"));
        newItemBtn.click();
        getDriver().findElement(By.cssSelector("#name")).sendKeys(name);
        getDriver().findElement(By.cssSelector(".org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();

        getDriver().findElement(By.xpath("//button[contains(text(),'Save')]")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[contains(text(),'Pipeline " + name + "')]"))
                .getText(), "Pipeline " + name);

    }
}
