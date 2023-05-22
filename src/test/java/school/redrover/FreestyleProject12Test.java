package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.NewJobPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import static org.testng.Assert.*;

public class FreestyleProject12Test extends BaseTest {

    private static final String PROJECT_NAME = "A-freestyle-project";

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
}