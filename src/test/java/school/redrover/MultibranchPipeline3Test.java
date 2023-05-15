package school.redrover;

import com.sun.jna.Structure;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import org.openqa.selenium.support.ui.Select;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class MultibranchPipeline3Test extends BaseTest {

    @Test
    public void testMoveMultibranchPipelineToFolder() {
        TestUtils.createFolder(this,"Folder1",true);
        TestUtils.createMultibranchPipeline(this,"MultibranchPipeline1", false);
        getDriver().findElement(By.linkText("Move")).click();
        WebElement folderList = getDriver().findElement(By.xpath("//select[@name='destination']"));
        getWait2().until(ExpectedConditions.elementToBeClickable(folderList)).click();
        getDriver().findElement(By.xpath("//select/option[contains(text(),'Folder')]")).click();
        getDriver().findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']")).click();

        String expectedMultibranchPipeline = "MultibranchPipeline1";
        WebElement actualMultibranchPipeline = getDriver().findElement(By.xpath("//h1"));
        Assert.assertEquals(actualMultibranchPipeline.getText(), expectedMultibranchPipeline);

        new Actions(getDriver())
                .click(getDriver().findElement(By.linkText("Folder1")))
                .perform();
        WebElement actualMultibranchPipeline2 = getDriver().findElement(By.linkText("MultibranchPipeline1"));

        Assert.assertEquals(actualMultibranchPipeline2.getText(), expectedMultibranchPipeline);
    }
}
