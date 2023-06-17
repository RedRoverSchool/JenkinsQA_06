package school.redrover.model.Jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MainPage;
import school.redrover.model.JobsConfig.MultibranchPipelineConfigPage;
import school.redrover.model.RenamePage;
import school.redrover.model.base.BaseJobPage;

public class MultibranchPipelinePage extends BaseJobPage<MultibranchPipelinePage> {

    public MultibranchPipelinePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultibranchPipelineConfigPage clickConfigure() {
        return new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver()));
    }

    public MainPage navigateToMainPageByBreadcrumbs() {
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//ol[@id='breadcrumbs']//li[1]")))).click();

        return new MainPage(getDriver());
    }

    public RenamePage<MultibranchPipelinePage> renameMultibranchPipelinePage () {
        getDriver().findElement(By.xpath("//body/div[@id='page-body']/div[@id='side-panel']/div[@id='tasks']/div[8]/span[1]/a[1]")).click();
        return new RenamePage<>(this);
    }
  
    public String getDescription() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("view-message"))).getText();
    }

    public String getTextFromNameMultibranchProject(){

        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//body/div[@id='page-body']/div[@id='main-panel']/h1[1]"))).getText();
    }

    public MultibranchPipelineConfigPage clickConfigureSideMenu() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/a[contains(@href, 'configure')]"))).click();
        return new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver()));
    }

    public String getTextFromDisableMessage() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@method='post']"))).getText();
    }

    public String getDisplayedName() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']/h1"))).getText().trim();
    }

    public boolean defaultIconIsDisplayed() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("(//*[name()='svg'][@title='Folder'])[1]"))).isDisplayed();
    }

    public boolean metadataFolderIconIsDisplayed() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//h1/img"))).isDisplayed();
    }

    public boolean isDescriptionEmpty(){
        return getDriver().findElement(By.xpath("//*[@id='description']/div[1]")).getText().isEmpty();
    }
}
