package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class JenkinsBreadcrumbsTest extends BaseTest {

    //private final By dashboardButton = By.xpath("//a[@href='/']/button[@class='jenkins-menu-dropdown-chevron']");

    @FindBy(css = ".jenkins-breadcrumbs__list-item")
    private WebElement getDashboardLink;

    @FindBy(xpath = "//a[@href='/']/button[@class='jenkins-menu-dropdown-chevron']")
    private WebElement dashboardChevron;


//    public void mouseHover(WebElement element) {
//        Actions actions = new Actions(getDriver());
//        actions.moveToElement(element).build().perform();
//    }
//
//    public void mouseHoverAndClick(WebElement element) {
//        Actions actions = new Actions(getDriver());
//        actions.moveToElement(element)
//                .click()
//                .build()
//                .perform();
//    }


@Test
    public void testBreadcrumbMenuVisibleWhenClickOnDashboard(){

    new Actions(getDriver())
        .moveToElement(getDashboardLink)
                .click()
                //.build()
                .perform();
    }































}
