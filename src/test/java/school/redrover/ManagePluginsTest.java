package school.redrover;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

import java.util.List;

public class ManagePluginsTest extends BaseTest {

    @Test
    public void testFourTasksOnLeftsidePanel() {
        final List<String> expectedListOfTasks = List.of(new String[]{"Updates", "Available plugins", "Installed plugins", "Advanced settings"});
        List<String> actualListOfTasks = new MainPage(getDriver())
                .navigateToManageJenkinsPage()
                .clickManagePlugins()
                .checkFourTasksOnTheLeftsidePanel();

        Assert.assertEquals(actualListOfTasks, expectedListOfTasks);
    }
}
