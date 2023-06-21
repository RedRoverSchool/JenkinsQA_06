package school.redrover.model.component;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MainPage;
import school.redrover.model.NewJobPage;
import school.redrover.model.PeoplePage;
import school.redrover.model.base.BaseComponent;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

    public MainPage clickDashboardButton() {
        getWait2().until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[contains(text(), 'Dashboard')]"))).click();

        return new MainPage(getDriver());
    }

    private WebElement getListItemOfBreadcrumb(String listItemName) {

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

    private void hoverOver(By locator) {
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(locator))
                .pause(Duration.ofMillis(300))
                .perform();
    }

    public MainBreadcrumbComponent<Page> getDashboardDropdownMenu() {
        hoverOver(By.xpath("//a[text()='Dashboard']"));
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Dashboard']/button"))).sendKeys(Keys.RETURN);

        return this;
    }

    public <PageFromSubMenu extends BaseMainHeaderPage<?>> PageFromSubMenu selectAnOptionFromDashboardManageJenkinsSubmenuList(
            String menuItem, PageFromSubMenu pageFromSubMenu) {

        getDashboardDropdownMenu();

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By.xpath("//a[contains(span, 'Manage Jenkins')]")))
                .pause(500)
                .moveToElement(getDriver().findElement(By.xpath("//span[contains(text(), '" + menuItem + "')]")))
                .click()
                .perform();

        return pageFromSubMenu;
    }

    public List<String> getMenuList() {
        List<WebElement> dropDownMenu = getDriver().findElements(By.cssSelector("#breadcrumb-menu>div:first-child>ul>li"));
        List<String> menuList = new ArrayList<>();
        for (WebElement el : dropDownMenu) {
            menuList.add(el.getAttribute("innerText"));
        }
        return menuList;
    }

    public PeoplePage openPeoplePageFromDashboardDropdownMenu () {
        getDashboardDropdownMenu();
        getDriver().findElement(By.xpath("//li/a/span[contains(text(), 'People')]")).click();
        return new PeoplePage(getDriver());
    }

    public NewJobPage clickNewItemDashboardDropdownMenu(){
        getDashboardDropdownMenu();
        getDriver().findElement(By.xpath("//div[@id='breadcrumb-menu']/div/ul/li/a")).click();
        return new NewJobPage(getDriver());
    }

    public MainBreadcrumbComponent<?> moveToManageJenkinsLink() {
        new Actions(getDriver()).moveToElement(getDriver().findElement(By.cssSelector("#breadcrumb-menu a[href='/manage'] span"))).perform();
        return this;
    }

    public void clickManageJenkinsSubmenu(String locator) {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath(locator))).click();
    }
}


