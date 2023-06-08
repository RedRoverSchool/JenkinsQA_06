package school.redrover.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MainPage;
import school.redrover.model.MovePage;
import school.redrover.model.RenamePage;

import java.time.Duration;

public abstract class BaseJobPage<Self extends BaseJobPage<?>> extends BaseMainHeaderPage<Self> {

    public BaseJobPage(WebDriver driver) {
        super(driver);
    }

    protected void setupClickConfigure() {
        getWait10().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.linkText("Configure")))).click();
    }

    public abstract BaseConfigPage clickConfigure();

    public String getProjectName() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#main-panel>h1"))).getText();
    }

    public RenamePage<Self> clickRename() {
        getDriver().findElement(By.linkText("Rename")).click();
        return new RenamePage<>((Self)this);
    }

    public MainPage clickDelete() {
        getDriver().findElement(By.partialLinkText("Delete ")).click();
        getDriver().switchTo().alert().accept();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
        return new MainPage(getDriver());
    }

    public Self clickEditDescription() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='description-link']"))).click();
        return (Self) this;
    }

    public Self enterDescription(String description) {
        getDriver().findElement(By.name("description")).sendKeys(description);
        return (Self) this;
    }

    public Self clearDescriptionField() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        return (Self) this;
    }

    public String getDescription() {
        return getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText();
    }

    public MovePage<Self> clickMoveOnSideMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.cssSelector("[href$='/move']")))).click();
        return new MovePage<>((Self)this);
    }

    public Self changeDescriptionWithoutSaving(String newDescription) {
        getDriver().findElement(By.cssSelector("#description-link")).click();
        WebElement textInput = getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.cssSelector("textarea[name='description']"))));
        textInput.clear();
        textInput.sendKeys(newDescription);
        return (Self)this;
    }

    public Self clickSaveButton() {
        getDriver().findElement(By.xpath("//button[text() = 'Save']")).click();
        return (Self)this;
    }
}
