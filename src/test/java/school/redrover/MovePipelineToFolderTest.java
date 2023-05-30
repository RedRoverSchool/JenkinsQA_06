package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import school.redrover.model.FolderPage;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class MovePipelineToFolderTest extends BaseTest {

    @Test
    public void testMovePipelineToFolder() {

        TestUtils.createFolder(this, "testFolder",true);
        TestUtils.createPipeline(this, "testPipeline",true);

        new MainPage(getDriver())
        .clickJobDropDownMenu("testPipeline")
        .dropDownMenuClickMove("testPipeline", new FolderPage(getDriver()))
        .selectDestinationFolder()
        .clickMoveButton()
        .navigateToMainPageByBreadcrumbs()
        .clickFolderName("testFolder")
        .getNestedFolder("testPipeline")
        .click();

        WebElement breadcrumbBar = getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']"));
        String breadcrumbText = breadcrumbBar.getText().replaceAll("\\W"," > ");
        assertEquals(breadcrumbText, "Dashboard > testFolder > testPipeline");

    }

}
