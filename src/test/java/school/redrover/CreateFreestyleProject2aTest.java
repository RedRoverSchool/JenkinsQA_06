package school.redrover;

import school.redrover.runner.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateFreestyleProject2aTest extends BaseTest {
    @Test
    public void testCreateFreestyleProject() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob'][@class='task-link ']")).click();
        getDriver().findElement(By.xpath("//input[@id = 'name']")).sendKeys("Project One1");
        getDriver().findElement(By.xpath("//li [@class = 'hudson_model_FreeStyleProject'][@role = 'radio']")).click();
        getDriver().findElement(By.xpath("//button [@type = 'submit'][@id = 'ok-button']")).click();
        getDriver().findElement(By.xpath("//textarea [@name = 'description']"))
                .sendKeys("Проект по автоматизации тестирования");
        getDriver().findElement(By.xpath("//button [@ name = 'Submit']")).click();
        getDriver().findElement(By.xpath("//div[@id = 'breadcrumbBar']//li[@class = 'jenkins-breadcrumbs__list-item']//a[@class = 'model-link'][text() = 'Dashboard']"))
                .click();
        String projectName = getDriver().findElement(By.xpath("//a[@class = 'jenkins-table__link model-link inside']"))
                .getText();
        Assert.assertEquals(projectName, "Project One1");
    }
}
