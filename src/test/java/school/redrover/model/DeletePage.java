package school.redrover.model;

import org.openqa.selenium.By;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;

public class DeletePage<ParentPage extends BasePage<?,?>> extends BaseMainHeaderPage<DeletePage<ParentPage>> {

    private final ParentPage parentPage;

    public DeletePage(ParentPage parentPage) {
        super(parentPage.getDriver());
        this.parentPage = parentPage;
    }

    public ParentPage clickYesButton() {
        getDriver().findElement(By.name("Submit")).click();
        return parentPage;
    }
}
