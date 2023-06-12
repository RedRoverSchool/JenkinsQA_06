package school.redrover.model.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.*;
import school.redrover.model.base.BaseComponent;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;

public class MainBreadcrumbComponent<Page extends BasePage<?, ?>> extends BaseComponent<Page> {

    public MainBreadcrumbComponent(Page page) {
        super(page);
    }

    public String getFullBreadcrumbText() {
        return getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//div[@id='breadcrumbBar']")))
                .getText()
                .replaceAll("\\n", " > ")
                .trim();
    }

    public int countBreadcrumbItems()  {

        return this
                .getFullBreadcrumbText()
                .replaceAll("[^>]", "")
                .trim()
                .length() + 1;

    }

    public MainPage navigateToDashboardByBreadcrumb() {

        getListItemOfBreadcrumb("Dashboard").click();
        return new MainPage(getDriver());

    }

    public WebElement getListItemOfBreadcrumb(String listItemName) {

        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[@class='jenkins-breadcrumbs__list-item']" +
                        "/a[contains(text(), '" + listItemName + "')]"
                )
            )
        );
    }

    public MainBreadcrumbComponent<Page> openDropdownMenuOfListItem(String listItemName) {

        Actions actions = new Actions(getDriver());
        final WebElement listItem = this.getListItemOfBreadcrumb(listItemName);
        final WebElement chevron = listItem.findElement(By.xpath("./button"));

        actions.moveToElement(listItem).perform();
        actions.moveToElement(chevron).perform();
        chevron.click();

        return this;

    }

    public <ReturnedPage extends BaseMainHeaderPage<?>> ReturnedPage clickBreadcrumbItem(String listItemName, ReturnedPage pageToReturn){

        getListItemOfBreadcrumb(listItemName).click();
        return pageToReturn;

    }

    public <ReturnedPage extends BaseMainHeaderPage<?>> ReturnedPage clickDropdownOption(String optionText, ReturnedPage pageToReturn) {

        WebElement dropdownMenu = getDriver().findElement(By.xpath("//div[@id='breadcrumb-menu']"));
        WebElement option = dropdownMenu.findElement(By.xpath(".//span[contains(text(), '" + optionText + "')]"));

        option.click();

        return pageToReturn;

    }

}


