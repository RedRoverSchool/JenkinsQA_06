package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseModel;
import school.redrover.model.base.BasePage;

public class MultibranchPipelinePage extends BaseModel {
    public MultibranchPipelinePage(WebDriver driver) {
        super(driver);
    }

    public MainPage navigateToMainPageByBreadcrumbs() {
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//ol[@id='breadcrumbs']//li[1]")))).click();

        return new MainPage(getDriver());
    }
    public RenameMultibranchPipelinePage renameMultibranchPipelinePage () {
        getDriver().findElement(By.xpath("//body/div[@id='page-body']/div[@id='side-panel']/div[@id='tasks']/div[8]/span[1]/a[1]")).click();
        return new RenameMultibranchPipelinePage(getDriver());
    }
    public WebElement multibranchPipeline() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//body/div[@id='page-body']/div[@id='main-panel']/h1[1]"))));
    }
    public WebElement getNestedMultibranchPipeline() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//body/div[@id='page-body']/div[@id='main-panel']/h1[1]")));
    }

    public WebElement getNestedFolder(String nameFolder) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'job/" + nameFolder + "/')]")));
    }
}
