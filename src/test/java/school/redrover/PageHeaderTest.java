package school.redrover;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;
import java.util.Arrays;
import java.util.List;


import static org.testng.Assert.*;


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

    @Test
    public void testNotificationAndSecurityIconsVisibilityOfIcons() {

        List<WebElement> visibilityOfIcons = getDriver()
                .findElements(By.xpath("//div[@class='login page-header__hyperlinks']//*[name()= 'svg']"));
        for (WebElement icons : visibilityOfIcons) {
            assertTrue(icons.isDisplayed());
        }
    }

    @Test
    public void testNotificationAndSecurityIconsButtonsChangeColorWhenMouseover() {
        List<WebElement> buttonsChangeColorWhenMouseover = getDriver()
                .findElements(By.xpath("//div[contains(@class,'am-container')]"));
        for (int i = 0; i < buttonsChangeColorWhenMouseover.size(); i++) {

            WebElement iconButtons = buttonsChangeColorWhenMouseover.get(i);

            String backgroundColor = iconButtons.getCssValue("color");
            new Actions(getDriver()).moveToElement(iconButtons).perform();
            String hoverColor = iconButtons.getCssValue("background-color");

            assertNotEquals(backgroundColor, hoverColor);
        }
    }

    @Test
    public void testNotificationAndSecurityIconsPopUpScreen() {
        List<WebElement> popUpScreen = getDriver()
                .findElements(By.xpath("//div[contains(@class,'am-container')]"));
        for (int i = 0; i < popUpScreen.size(); i++) {
            if (popUpScreen.get(i).isDisplayed()) {
                popUpScreen.get(i).click();

                assertTrue(getWait2().
                        until(ExpectedConditions.visibilityOfElementLocated
                                (By.xpath("//div[contains(@class,'am-list')]"))).isDisplayed());

                if (i < popUpScreen.size() - 1 && !popUpScreen.get(i++).isDisplayed()) {
                    break;
                }
            }
        }
    }

    @Test
    public void testNotificationAndSecurityIconsOpenManageJenkinslink () {
        List<WebElement> openManageJenkinslink = getDriver()
                .findElements(By.xpath("//div[contains(@class,'am-container')]"));
        for (int i = 0; i < openManageJenkinslink.size(); i++) {
            if (openManageJenkinslink.get(i).isDisplayed()) {
                openManageJenkinslink.get(i).click();

                getWait2().until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//a[normalize-space()='Manage Jenkins']"))).click();

                assertEquals(getWait2().until
                        (ExpectedConditions.presenceOfElementLocated
                                (By.xpath("//h1"))).getText(), "Manage Jenkins");

                getDriver().navigate().back();

                openManageJenkinslink = getDriver()
                        .findElements(By.xpath("//div[contains(@class,'am-container')]"));

                if (i < openManageJenkinslink.size() - 1 && !openManageJenkinslink.get(i++).isDisplayed()) {
                    break;
                }
            }
        }
    }
}
