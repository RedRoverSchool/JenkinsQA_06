package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.NewJobPage;
import school.redrover.runner.BaseTest;

public class FreestyleProjectAlexEvtuhTest extends BaseTest {

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
