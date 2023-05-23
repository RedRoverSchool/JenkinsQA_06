package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class PipelinePage extends BasePage {

    public PipelinePage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickDashboard() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Dashboard']"))).click();
        return new MainPage(getDriver());
    }

    public String getProjectName() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']/h1"))).getText();
    }

    public PipelinePage clickRename() {
        getDriver().findElement(By.xpath("//a[contains(@href, '/confirm-rename')]")).click();
        return this;
    }

    public PipelinePage clearNameField() {
        getDriver().findElement(By.name("newName")).clear();
        return this;
    }

    public PipelinePage enterNewName(String newName) {
        getDriver().findElement(By.name("newName")).sendKeys(newName);
        return this;
    }

    public PipelinePage clickRenameButton() {
        getDriver().findElement(By.name("Submit")).click();
        return this;
    }

    public PipelinePage clickDeletePipeline() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@data-url,'/doDelete')]"))).click();
        return this;
    }

    public PipelinePage clickDisableProject() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Disable Project']"))).click();
        return this;
    }

    public MainPage acceptAlert() {
        getDriver().switchTo().alert().accept();
        return new MainPage(getDriver());
    }

    public WebElement getHeaderPipeline() {
        return getDriver().findElement(By.cssSelector("[class$='headline']"));
    }
}
