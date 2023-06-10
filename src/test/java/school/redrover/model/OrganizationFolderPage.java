package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseJobPage;
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

    public String getTextFromDisableMessage(){

        return getDriver().findElement(By.xpath("//form[@method='post']")).getText();
    }

    public String getTextFromDescription(){

        return getDriver().findElement(By.xpath("//div[@id='view-message']")).getText();
    }

    public OrganizationFolderPage clickDisableButton(){
        getDriver().findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']")).click();

        return this;
    }
}
