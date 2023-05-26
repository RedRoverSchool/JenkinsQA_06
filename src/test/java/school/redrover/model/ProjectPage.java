package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

import java.time.Duration;

public class ProjectPage extends BasePage {

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    public MainPage navigateToHomePageUsingJenkinsIcon() {
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.cssSelector("#jenkins-head-icon")))).click();
        return new MainPage(getDriver());
    }

    public MainPage navigateToMainPageByBreadcrumbs() {
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                        .findElement(By.xpath("//ol[@id='breadcrumbs']//li[1]")))).click();
        return new MainPage(getDriver());
    }

    public WebElement getNameProject() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#main-panel>h1")));
    }

    public WebElement getEnableForm(){
        return getDriver().findElement(By.cssSelector("form#enable-project"));
    }

    public WebElement getDisableButton(){
        return getDriver().findElement(By.xpath("//form[@id='disable-project']/button"));
    }

    public RenameProjectPage clickRename() {
        getDriver().findElement(By.linkText("Rename")).click();
        return new RenameProjectPage(getDriver());
    }

    public ProjectPage enableProject(){
        getDriver().findElement(By.xpath("//form[@id='enable-project']/button")).click();
        return this;
    }

    public MainPage deleteProject(){
        getDriver().findElement(By.xpath("//a/span[text()='Delete Multi-configuration project']/..")).click();
        getDriver().switchTo().alert().accept();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
        return new MainPage(getDriver());
    }
    public WebElement projectsHeadline() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//h1[contains(text(),'Project')]"))));
    }
}
