package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseDashboardPage;

public class ViewPage extends BaseDashboardPage<ViewPage> {

    @FindBy(xpath = "//a[@href='delete']")
    private WebElement deleteView;

    public ViewPage(WebDriver driver) {
        super(driver);
    }

    public ListViewConfigPage clickEditListView(String nameProject) {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//*[@href='/view/%s/configure']", nameProject.replaceAll(" ","%20"))))).click();
        return new ListViewConfigPage(new ViewPage(getDriver()));
    }

    public DeletePage<MainPage> clickDeleteView() {
        getWait5().until(ExpectedConditions.elementToBeClickable(deleteView)).click();
        return new DeletePage<>(new MainPage(getDriver()));
    }

}
