package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;

public class RenamePage <JobTypePage extends BasePage<?>> extends BaseMainHeaderPage {

    private final JobTypePage jobTypePage;

    public RenamePage(JobTypePage jobTypePage) {
        super(jobTypePage.getDriver());
        this.jobTypePage = jobTypePage;
    }

    public RenamePage<JobTypePage> enterNewName(String name) {
        WebElement inputTextbox = getDriver().findElement(By.name("newName"));
        inputTextbox.clear();
        inputTextbox.sendKeys(name);
        return this;
    }

    public JobTypePage submitNewName() {
        getDriver().findElement(By.name("Submit")).click();
        return jobTypePage;
    }

    public String getErrorMessage() {
        getDriver().findElement(By.xpath("//li[text()='Rename']")).click();

        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error"))).getText();
    }

    public CreateItemErrorPage clickRenameButton() {
        getDriver().findElement(By.name("Submit")).click();
        return new CreateItemErrorPage(getDriver());
    }
}
