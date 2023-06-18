package school.redrover.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MainPage;
import school.redrover.model.MovePage;
import school.redrover.model.RenamePage;

import java.time.Duration;

public abstract class BaseJobPage<Self extends BaseJobPage<?>> extends BaseMainHeaderPage<Self> {

    @FindBy(xpath = "//*[@id='description']/div[1]")
    private WebElement descriptionEmpty;

    public BaseJobPage(WebDriver driver) {
        super(driver);
    }

    private void clickEditDescription() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='description-link']"))).click();
    }

    private void enterDescription(String description) {
        getDriver().findElement(By.name("description")).sendKeys(description);
    }

    private void clearDescriptionField() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
    }

    protected void setupClickConfigure() {
        getWait10().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.linkText("Configure")))).click();
    }

    public abstract BaseConfigPage<?,?> clickConfigure();

    public String getJobName() {
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

    public Self addDescriptionAndSave(String description) {
        clickEditDescription();
        enterDescription(description);
        clickSaveButton();
        return (Self) this;
    }

    public Self removeOldDescriptionAndAddNewAndSave(String newDescription) {
        clearDescriptionField();
        clickEditDescription();
        enterDescription(newDescription);
        clickSaveButton();
        return (Self) this;
    }

    public String getPreviewDescription(String description) {
        clickEditDescription();
        enterDescription(description);
        getDriver().findElement(By.xpath("//a[@class = 'textarea-show-preview']")).click();
        return getDriver().findElement(By.xpath("//*[@class = 'textarea-preview']")).getText();
    }

    public boolean isDescriptionEmpty(){

        return descriptionEmpty.getText().isEmpty();
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
