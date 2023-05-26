package school.redrover;


import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.ViewPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class RenamePipelineTest extends BaseTest {
    private String projectName = "Project44";
    private String updatedProjectName = "Project48";
    @Test
    public void createPipeline(){
        TestUtils.createPipeline(this,projectName,true);
    }
   @Test(dependsOnMethods = "createPipeline")
    public void testRenamePipeline(){
       String renamePipelineFolder = new MainPage(getDriver())
               .clickPipelineProject(projectName)
               .clickRename()
               .clearNameField()
               .enterNewName(updatedProjectName)
               .clickRenameButton()
               .clickDashboard()
               .getJobName(updatedProjectName);

       Assert.assertEquals(renamePipelineFolder,updatedProjectName);
   }

}
