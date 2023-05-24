package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.NewJobPage;
import school.redrover.model.PipelinePage;
import school.redrover.runner.BaseTest;

public class PipelineProject7Test extends BaseTest {
    private final String pipelineProjectName = "ProjectPipeline";

    @Test
    public void testCreatePipelineProjectNewItem() {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem();

        PipelinePage pipelinePage = new NewJobPage(getDriver())
                .enterItemName(pipelineProjectName)
                .selectPipelineAndOk()
                .clickSaveButton();

        Assert.assertEquals(pipelinePage.getProjectName(), "Pipeline " + pipelineProjectName);
    }

    @DataProvider(name = "wrong-characters")
    public Object[][] providerWrongCharacters() {
        return new Object[][]{{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"?"}, {"|"}, {">"}, {"["}, {"]"}};
    }

    @Test(dataProvider = "wrong-characters")
    public void testWrongCharactersBeforeNameProject(String wrongCharacters) {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(wrongCharacters);
        Assert.assertEquals(newJobPage.getItemInvalidMessage(),"» ‘" + wrongCharacters + "’ is an unsafe character");
        Assert.assertFalse(newJobPage.isOkButtonEnabled());
    }

    @Test
    public void testDotBeforeNameProject() {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(".");

        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» “.” is not an allowed name");
    }

    @Test
    public void testCreatePipelineDashboardSliderNewItem(){
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickOnSliderDashboardInDropDownMenu()
                .clickNewItemInDashboardDropDownMenu();

        PipelinePage PipelinePage = new NewJobPage(getDriver())
                .enterItemName(pipelineProjectName)
                .selectPipelineAndOk()
                .clickSaveButton();

        MainPage mainPage = new PipelinePage(getDriver())
                .clickDashboard();

        Assert.assertEquals(mainPage.getProjectNameMainPage(pipelineProjectName), pipelineProjectName);
    }

    @Test
    public void testCreateAJobPipeline(){
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickCreateAJob();

        PipelinePage PipelinePage = new NewJobPage(getDriver())
                .enterItemName(pipelineProjectName)
                .selectPipelineAndOk()
                .clickSaveButton();

        MainPage mainPage = new PipelinePage(getDriver())
                .clickDashboard();

        Assert.assertEquals(mainPage.getProjectNameMainPage(pipelineProjectName), pipelineProjectName);
    }
}
