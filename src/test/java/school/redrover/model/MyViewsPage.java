package school.redrover.model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseModel;

public class MyViewsPage extends BaseModel {

    public MyViewsPage(WebDriver driver) {
        super(driver);
    }

    public MyViewsPage clickCreateAJob() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='newJob']"))).click();
        return new MyViewsPage(getDriver());
    }

    public MyViewsPage enterAnItemName(String name) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(name);
        return this;
    }

    public MyViewsPage clickFreestyleProject() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hudson_model_FreeStyleProject"))).click();
        return this;
    }

    public MyViewsPage clickOkButton() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ok-button"))).click();
        return this;
    }

    public MyViewsPage clickSaveButton() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@formnovalidate = 'formNoValidate']"))).click();
        return this;
    }

    public MyViewsPage clickOnDashboardPage() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Dashboard"))).click();
        return this;
    }

    public MyViewsPage clickOnNewJob() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/view/all/newJob')]"))).click();
        return new MyViewsPage(getDriver());
    }

    public MyViewsPage clickOnDescription() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link"))).click();
        return new MyViewsPage(getDriver());
    }

    public MyViewsPage enterDescription (String name){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@name='description']"))).sendKeys(name);
        return new MyViewsPage(getDriver());
    }

    public MyViewsPage clickSaveButtonDescription(){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']"))).click();
        return new MyViewsPage(getDriver());
    }

    public String getTextFromDescription(){
        String getTextDescription = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='description']/div[not(@class)]"))).getText();
        return getTextDescription;
    }

    public MyViewsPage clearTextFromDescription() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@name='description']"))).clear();
        return new MyViewsPage(getDriver());
    }

    public MyViewsPage enterNewDescription (String name){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@name='description']"))).sendKeys(name);
        return new MyViewsPage(getDriver());
    }

    public NewJobPage clickNewItem() {
        getDriver().findElement(By.cssSelector(".task-link-wrapper>a[href$='newJob']")).click();
        return new NewJobPage(getDriver());
    }
}


