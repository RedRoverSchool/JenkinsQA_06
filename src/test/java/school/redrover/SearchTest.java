package school.redrover;

import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void testSearchBoxInsensitive() {
        boolean isSearchResultContainsText = new MainPage(getDriver()).getHeader()
                .clickAdminDropdownMenu()
                .openConfigureTabFromAdminDropdownMenu()
                .selectInsensitiveSearch()
                .saveConfig()
                .getHeader()
                .typeToSearch("built")
                .isSearchResultContainsText("built");

        Assert.assertTrue(isSearchResultContainsText, "Wrong search result");
    }
}
