package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class ViewPage extends BasePage {

    @FindBy(xpath = "//div[@id='description']/child::*")
    private WebElement description;

    @FindBy(xpath = "//input[@id = 'name']")
    private WebElement stringSearchItemName;

    @FindBy(xpath = "//span[normalize-space()='Pipeline']")
    private WebElement pipelineProject;

    @FindBy(xpath = "//button[@id = 'ok-button']")
    private WebElement saveButton;

    public ViewPage(WebDriver driver) {
        super(driver);
    }

    public ViewPage inputAnItemName(String text) {

        sendTextToInput(stringSearchItemName, text);
        return new ViewPage(getDriver());
    }

    public ViewPage clickPipelineProject() {
        click(pipelineProject);
        return new ViewPage(getDriver());
    }

    public ConfigurePage clickSaveButton() {
        click(saveButton);
        return new ConfigurePage(getDriver());
    }

    public ViewPage clickAddDescription() {
        getDriver().findElement(By.xpath("//a[@id='description-link']")).click();
        return this;
    }

    public ViewPage inputDescText(String desc) {
        new Actions(getDriver()).
                click(getDriver().findElement(By.xpath("//textarea[@name='description']"))).
                sendKeys(desc).
                perform();
        return this;
    }

    public ViewPage saveDescription() {
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        return this;
    }

    public String getDescriptionText() {
        return description.getText();
    }
}
