package school.redrover.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;
import school.redrover.model.jobs.FreestyleProjectPage;

public class DeletePage<ParentPage extends BasePage<?,?>> extends BaseMainHeaderPage<DeletePage<ParentPage>> {

    @FindBy(name = "Submit")
    private WebElement deleteYesButton;

    private final ParentPage parentPage;

    public DeletePage(ParentPage parentPage) {
        super(parentPage.getDriver());
        this.parentPage = parentPage;
    }

    public ParentPage clickYesButton() {
        deleteYesButton.click();
        return parentPage;
    }

    public FreestyleProjectPage clickYesButtonForDropDownMainPage() {
        deleteYesButton.click();
        return new FreestyleProjectPage(getDriver());
    }
}
