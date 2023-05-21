package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[@title='Schedule a Build for Test']")
    private WebElement playBuildForATestButton;

    @FindBy(xpath = "//span[text()='Build History']")
    private WebElement buildsHistoryButton;


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickPlayBuildForATestButton() {
        click(playBuildForATestButton);
        return new MainPage(getDriver());
    }

    public BuildsPage clickBuildsHistoryButton() {
        click(buildsHistoryButton);
        return new BuildsPage(getDriver());
    }

}
