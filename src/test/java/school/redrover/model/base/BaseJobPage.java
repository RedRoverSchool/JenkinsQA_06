package school.redrover.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MainPage;
import school.redrover.model.MovePage;
import school.redrover.model.RenamePage;

import java.time.Duration;

public abstract class BaseJobPage<Self extends BaseJobPage<?>> extends BaseMainHeaderPage<BaseJobPage<?>> {

    public BaseJobPage(WebDriver driver) {
        super(driver);
    }

    protected void setupClickConfigure() {
        getWait10().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.linkText("Configure")))).click();
    }

    public abstract BaseConfigPage<?, Self> clickConfigure();

    public String getName() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#main-panel>h1"))).getText();
    }

    public RenamePage<BaseJobPage<Self>> clickRename() {
        getDriver().findElement(By.linkText("Rename")).click();
        return new RenamePage<>(this);
    }

    public MainPage clickDelete() {
        getDriver().findElement(By.partialLinkText("Delete ")).click();
        getDriver().switchTo().alert().accept();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
        return new MainPage(getDriver());
    }

    public String getDescription() {
        return getDriver().findElement(By.xpath("//div[@id='description']")).getText();
    }

    public MovePage<Self> clickMoveOnSideMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.cssSelector("[href$='/move']")))).click();
        return new MovePage<>((Self)this);
    }

    public Self changeDescription(String newDescription) {
        getDriver().findElement(By.cssSelector("#description-link")).click();
        WebElement textInput = getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.cssSelector("textarea[name='description']"))));
        textInput.clear();
        textInput.sendKeys(newDescription);
        return (Self)this;
    }
}
