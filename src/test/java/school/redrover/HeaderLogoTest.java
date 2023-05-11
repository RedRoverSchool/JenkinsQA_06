package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

    public class HeaderLogoTest extends BaseTest {

        @Test
        public void testHeaderLogo() {

            WebElement newItemFreeStyleProject = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
            newItemFreeStyleProject.click();

            getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("New Item 1");
            getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
            getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
            getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();

            WebElement goToDashboard1 = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
            goToDashboard1.click();

            WebElement newItemFolder = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
            newItemFolder.click();

            getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("New Item 2");
            getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']")).click();
            getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
            getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();

            WebElement goToDashboard2 = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
            goToDashboard2.click();

            WebElement goToUserIdPage = getDriver().findElement(By.xpath("//a[@href='/user/admin']//*[not(self::button)]"));
            goToUserIdPage.click();

            WebElement clickJenkinsLogo = getDriver().findElement(By.xpath("//a[@id='jenkins-home-link']"));
            clickJenkinsLogo.click();

            List<WebElement> breadcrumbBarMenuList = getDriver().findElements(By.xpath("//div[@id='breadcrumbBar']/descendant::text()/parent::*"));
            for (WebElement i : breadcrumbBarMenuList) {
                assertEquals(i.getText(), "Dashboard");
            }

            List<String> expectedCreatedItemsList = Arrays.asList("New Item 1", "New Item 2");
            List<WebElement> actualItemsList = getDriver().findElements(By.xpath("//td/a[@class='jenkins-table__link model-link inside']/span"));
            for (int i = 0; i < actualItemsList.size(); i++) {
                assertEquals(actualItemsList.get(i).getText(), expectedCreatedItemsList.get(i));
            }
        }
    }


