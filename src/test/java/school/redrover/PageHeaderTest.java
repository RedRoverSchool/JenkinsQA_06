package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class PageHeaderTest extends BaseTest {

    @Test
    public void testClickLogoToReturnToDashboardPage() {

        TestUtils.createFreestyleProject(this, "New Item 1", true);
        TestUtils.createFolder(this, "New Item 2", false);

        WebElement goToUserIdPage = getDriver()
                .findElement(By.xpath("//a[@href='/user/admin']//*[not(self::button)]"));
        goToUserIdPage.click();

        WebElement clickJenkinsLogoToReturnToDashboardPage = getDriver()
                .findElement(By.xpath("//div[@class='logo']/a"));
        clickJenkinsLogoToReturnToDashboardPage.click();

        List<WebElement> ifBreadcrumbBarMenuListConsistsOfDashboardWord = getDriver()
                .findElements(By.xpath("//div[@id='breadcrumbBar']/descendant::text()/parent::*"));
            for (WebElement i : ifBreadcrumbBarMenuListConsistsOfDashboardWord) {
                assertEquals(i.getText(), "Dashboard");
            }

        List<String> expectedCreatedItemsList = Arrays.asList("New Item 1", "New Item 2");
        List<WebElement> actualItemsList = getDriver()
                .findElements(By.xpath("//td/a[@class='jenkins-table__link model-link inside']/span"));
            for (int i = 0; i < actualItemsList.size(); i++) {
                assertEquals(actualItemsList.get(i).getText(), expectedCreatedItemsList.get(i));
            }
    }
}
