package school.redrover;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.FreestyleProjectPage;
import school.redrover.model.MainPage;
import school.redrover.model.NewJobPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class FreestyleProject12Test extends BaseTest {

    private static final String PROJECT_NAME = "A freestyle project";
    private static final String PROJECT_DESCRIPTION = "A FP description";

    @DataProvider(name = "specialCharacters")
    public static Object[][] specialCharacters() {
        return new Object[][]{{'&'}, {'>'}, {'<'}, {'!'}, {'@'}, {'#'},
                {'$'}, {'%'}, {'^'}, {'*'}, {'['}, {']'}, {'\\'}, {'|'},
                {';'}, {':'}, {'/'}, {'?'}};
    }

    @Test(dataProvider = "specialCharacters")
    public void testCreateWithInvalidName(Character specialCharacter) {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(String.valueOf(specialCharacter))
                .selectFreestyleProject();

        assertEquals(newJobPage.getItemInvalidMessage(), String.format("» ‘%s’ is an unsafe character", specialCharacter));
        assertFalse(newJobPage.isOkButtonEnabled());
    }

    @Test
    public void testCreateWithExistingName() {
        TestUtils.createFreestyleProject(this, PROJECT_NAME, true);

        String itemAlreadyExistsMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(PROJECT_NAME)
                .selectFreestyleProject()
                .clickOkToCreateWithExistingName()
                .getErrorMessage();

        assertEquals(itemAlreadyExistsMessage,
                String.format("A job already exists with the name ‘%s’", PROJECT_NAME));
    }

    @Test
    public void testCreatedProjectIsOnDashboard() {
        TestUtils.createFreestyleProject(this, PROJECT_NAME, true);

        assertEquals(new MainPage(getDriver()).getJobName(PROJECT_NAME), PROJECT_NAME);
    }

    @Test
    public void testCreateWithDescription() {
        TestUtils.createFreestyleProject(this, PROJECT_NAME, false);

        String projectDescription = new FreestyleProjectPage(getDriver())
                .clickAddDescription()
                .addDescription(PROJECT_DESCRIPTION)
                .clickSave()
                .getDescription();

        assertEquals(projectDescription, PROJECT_DESCRIPTION);
    }
}