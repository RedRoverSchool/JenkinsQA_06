package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class FolderPage extends BasePage {

    public final WebElement HEADING1 = getDriver().findElement(By.xpath("//h1"));

    public FolderPage(WebDriver driver) {
        super(driver);
    }

    public FolderPage configure(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(2)")).click();
        return this;
    }

    public NewJobPage newItem(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(3)")).click();
        return new NewJobPage(getDriver());
    }

    public FolderPage deleteFolder(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(4)")).click();
        return this;
    }

    public FolderPage people(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(5)")).click();
        return this;
    }

    public FolderPage buildHistory(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(6)")).click();
        return this;
    }

    public FolderPage rename(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(7)")).click();
        return this;
    }

    public FolderPage credentials(){
        getDriver().findElement(By.cssSelector("#tasks>:nth-child(8)")).click();
        return this;
    }

    public NewViewFolderPage newView(){
        getDriver().findElement(By.xpath("//div[@class='tab']")).click();
        return new NewViewFolderPage(getDriver());
    }

    public FolderPage addDescription(String description){
        getDriver().findElement(By.xpath("//div[@class='tab']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name='description']"))).sendKeys(description);
        getDriver().findElement(By.cssSelector("[name='Submit']")).click();
        return this;
    }

    public NewJobPage newJob(){
        getDriver().findElement(By.cssSelector("[href='newJob']")).click();
        return new NewJobPage(getDriver());
    }

    public MainPage navigateToMainPageByBreadcrumbs() {
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//ol[@id='breadcrumbs']//li[1]")))).click();
        return new MainPage(getDriver());
    }
}
