package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseDashboardPage;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class MyViewsPage extends BaseDashboardPage<MyViewsPage> {

    public MyViewsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@href, '/configure')]")
    private WebElement configureView;

    @FindBy(xpath = "//input[@name = 'name']")
    private WebElement nameView;

    @FindBy(xpath = "//button[@name = 'Submit']")
    private WebElement submitView;

    @FindBy(xpath = "//h2")
    private WebElement statusMessage;

    @FindBy(xpath = "//div[@class = 'tab'][last()-1]")
    private WebElement inactiveLastCreatedMyView;

    @FindBy(xpath = "//a[@href = 'delete']")
    private WebElement deleteViewButton;

    @FindBy(xpath = "//div[@class='tabBar']//div[starts-with(@class, 'tab')]")
    private List<WebElement> allViews;

    public String getStatusMessageText() {

        return statusMessage.getText();
    }

    public MyViewsPage clickInactiveLastCreatedMyView() {
        TestUtils.click(this, inactiveLastCreatedMyView);

        return this;
    }

    public MyViewsPage editMyViewNameAndClickSubmitButton(String editedMyViewName) {
        TestUtils.click(this, configureView);
        TestUtils.sendTextToInput(this, nameView, editedMyViewName);
        TestUtils.click(this, submitView);

        return this;
    }

    public DeletePage<MyViewsPage> clickDeleteViewButton() {
        TestUtils.click(this, deleteViewButton);

        return new DeletePage<>(this);
    }

    public List<String> getListOfAllViews() {
        List<String> list = new ArrayList<>();
        List<WebElement> views = allViews;
        for (WebElement view : views) {
            list.add(view.getText());
        }

        return list;
    }
}
