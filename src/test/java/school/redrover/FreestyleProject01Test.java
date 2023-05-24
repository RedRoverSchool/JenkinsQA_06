package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import school.redrover.model.FreestyleProjectPage;
import school.redrover.model.MainPage;
import school.redrover.model.NewJobPage;
import school.redrover.runner.BaseTest;

public class FreestyleProject01Test extends BaseTest {
    private static final String FREESTYLE_NAME = "Freestyle";

    @Test
    public void testCreateFreestyleProject(){
        FreestyleProjectPage freestyleProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSave();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), "Project " + FREESTYLE_NAME);
    }

    @Test
    public void testNameAndDescAreDisplayed(){
        FreestyleProjectPage freestyleProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .addDescription("Description")
                .clickSave();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), "Project " + FREESTYLE_NAME);
        Assert.assertEquals(freestyleProjectPage.getDescription(), "Description");
    }

    @Test
    public void testDisableFreestyleProject(){
        String message = "This project is currently disabled";

        FreestyleProjectPage freestyleProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSave()
                .clickTheDisableProjectButton();

                Assert.assertEquals(freestyleProjectPage.getWarningMessage(), message);
                Assert.assertTrue(freestyleProjectPage.isProjectEnableButtonDisplayed());
        }

    @Test
    public void testEnableFreestyleProject(){
        FreestyleProjectPage freestyleProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSave()
                .clickTheDisableProjectButton()
                .clickTheEnableProjectButton();

                Assert.assertTrue(freestyleProjectPage.isProjectDisabledButtonDisplayed());
    }

    @Test
    public void testBuildNowProject() {
        FreestyleProjectPage freestyleProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSave()
                .selectBuildNow();

        Assert.assertEquals(freestyleProjectPage.getBuildsQuantity(), 1);

    }

    @Test(dataProvider = "invalidSymbols")
    public void testCreatingProjectWithInvalidName(String invalidSymbols){
       String validationMessage = new MainPage(getDriver())
                .clickNewItem()
                .selectFreestyleProject()
                .enterItemName(invalidSymbols)
                .getItemInvalidMessage();

       boolean okButton = new NewJobPage(getDriver())
               .isOkButtonEnabled();

        Assert.assertEquals(validationMessage, "» ‘" + invalidSymbols + "’ is an unsafe character");
        Assert.assertFalse(okButton);
    }

    @DataProvider(name = "invalidSymbols")
    public Object[][] invalidSymbols(){
        return new Object[][] {{"!"},{"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {":"}, {";"}, {"/"}, {"|"}, {"?"}, {"<"}, {">"}};
    }
}
