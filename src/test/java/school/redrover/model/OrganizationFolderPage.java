package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseJobPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class OrganizationFolderPage extends BaseJobPage<OrganizationFolderPage> {

    public OrganizationFolderPage(WebDriver driver) {
        super(driver);
    }
    @Override
    public OrganizationFolderConfigPage clickConfigure() {
        setupClickConfigure();
        return new OrganizationFolderConfigPage(this);
    }

    public String getTextFromDisableMessage() {

        return getDriver().findElement(By.xpath("//form[@method='post']")).getText();
    }

    public String getTextFromDescription() {

        return getDriver().findElement(By.xpath("//div[@id='view-message']")).getText();
    }

    public OrganizationFolderPage clickDisableButton() {
        getDriver().findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']")).click();

        return this;
    }

    public DeletePage<MainPage> clickDeleteOrganizationFolderSideMenu() {
        getWait2().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='tasks']//a[@href='/job/OrgFolderNew/delete']"))).click();
        return new DeletePage<>(getDriver(), new MainPage(getDriver()));
    }
}
