package school.redrover.model.component;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MainPage;
import school.redrover.model.ManageJenkinsPage;
import school.redrover.model.MyViewsPage;
import school.redrover.model.NewJobPage;
import school.redrover.model.PeoplePage;
import school.redrover.model.base.BaseComponent;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainBreadcrumbComponent<Page extends BasePage<?, ?>> extends BaseComponent<Page> {

    @FindBy(xpath = "//a[contains(text(), 'Dashboard')]")
    private WebElement dashboard;

    @FindBy(xpath = "//div[@id = 'breadcrumbBar']//a[@href='/']//button")
    private WebElement sliderDashboard;

    @FindBy(xpath = "//*[@id='yui-gen4']/a/span")
    private WebElement manageJenkinsInSliderDashboard;

    @FindBy(xpath = "//div[@id='breadcrumbBar']")
    private WebElement fullBreadcrumb;

    @FindBy(xpath = "//div[@id='breadcrumb-menu']")
    private WebElement dropdownMenu;

    @FindBy(xpath = "//a[text()='Dashboard']/button")
    private WebElement dashboardButton;

    @FindBy(xpath = "//a[contains(span, 'Manage Jenkins')]")
    private WebElement manageJenkinsSubmenu;

    @FindBy(css = "#breadcrumb-menu>div:first-child>ul>li")
    private List<WebElement> dropDownMenu;

    @FindBy(xpath = "//li/a/span[contains(text(), 'People')]")
    private WebElement people;

    @FindBy(xpath = "//div[@id='breadcrumb-menu']/div/ul/li/a")
    private WebElement newItem;

    @FindBy(xpath = "//li/a/span[contains(text(), 'My Views')]")
    private WebElement myViews;

    public MainBreadcrumbComponent(Page page) {
        super(page);
    }

    private void  openDropDownMenuDashboard() {
        Actions actions = new Actions(getDriver());

        actions.moveToElement(dashboard).perform();
        actions.moveToElement(sliderDashboard).perform();
        sliderDashboard.sendKeys(Keys.RETURN);
    }

    public ManageJenkinsPage clickManageJenkinsOnDropDownMenu() {
        openDropDownMenuDashboard();

        getWait5().until(ExpectedConditions.elementToBeClickable(manageJenkinsInSliderDashboard)).click();

        return new ManageJenkinsPage(getDriver());
    }

    public String getFullBreadcrumbText() {
        return getWait5()
                .until(ExpectedConditions.visibilityOf(fullBreadcrumb))
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
        getWait2().until(ExpectedConditions.elementToBeClickable(dashboard)).click();
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

        dropdownMenu.findElement(By.xpath(".//span[contains(text(), '" + optionText + "')]")).click();

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
        getWait2().until(ExpectedConditions.visibilityOf(dashboardButton)).sendKeys(Keys.RETURN);

        return this;
    }

    public <PageFromSubMenu extends BaseMainHeaderPage<?>> PageFromSubMenu selectAnOptionFromDashboardManageJenkinsSubmenuList(
            String menuItem, PageFromSubMenu pageFromSubMenu) {

        getDashboardDropdownMenu();

        new Actions(getDriver())
                .moveToElement(manageJenkinsSubmenu)
                .pause(500)
                .moveToElement(getDriver().findElement(By.xpath("//span[contains(text(), '" + menuItem + "')]")))
                .click()
                .perform();

        return pageFromSubMenu;
    }

    public List<String> getMenuList() {

        List<String> menuList = new ArrayList<>();
        for (WebElement el : dropDownMenu) {
            menuList.add(el.getAttribute("innerText"));
        }
        return menuList;
    }

    public PeoplePage openPeoplePageFromDashboardDropdownMenu () {
        getDashboardDropdownMenu();
        people.click();
        return new PeoplePage(getDriver());
    }

    public NewJobPage clickNewItemDashboardDropdownMenu(){
        getDashboardDropdownMenu();
        newItem.click();
        return new NewJobPage(getDriver());
    }

    public MyViewsPage openMyViewsPageFromDashboardDropdownMenu () {
        getDashboardDropdownMenu();
        myViews.click();
        return new MyViewsPage(getDriver());
    }

    public MainBreadcrumbComponent<?> moveToManageJenkinsLink() {
        new Actions(getDriver()).moveToElement(manageJenkinsSubmenu).perform();
        return this;
    }

    public void clickManageJenkinsSubmenu(String locator) {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath(locator))).click();
    }

    public String getPageName () {
        return  getDriver()
                .findElement(By.xpath("//h1"))
                .getText();
    }

    public void clickOkOnPopUp () {
        getDriver()
                .switchTo()
                .alert()
                .accept();
    }


}


