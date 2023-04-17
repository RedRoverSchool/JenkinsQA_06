package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupOlesyaTests extends BaseTest {
    private final String URL = "https://www.saucedemo.com/";
    private final String LOGIN = "standard_user";
    private final String MAIN_PAGE = "https://www.saucedemo.com/inventory.html";
    private final String PASSWORD = "secret_sauce";
    private WebDriverWait wait;

    protected WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait;
    }

    private void loginToSite(String login) {
        getDriver().get("https://www.saucedemo.com/");
        getDriver().findElement(By.name("user-name")).sendKeys(login);
        getDriver().findElement(By.name("password")).sendKeys(PASSWORD);
        getDriver().findElement(By.name("login-button")).click();
    }

    private void loginToSite() {
        getDriver().get(URL);
        getDriver().findElement(By.name("user-name")).sendKeys(LOGIN);
        getDriver().findElement(By.name("password")).sendKeys(PASSWORD);
        getDriver().findElement(By.name("login-button")).click();
    }

    public List<WebElement> getListItems(By by) {

        return getDriver().findElements(by);
    }

    public List<String> productNames(){
        List<WebElement> el = getDriver().findElements(By.xpath("//div[@class = 'inventory_item_name']"));
        return el.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void sortElements(String visibleText){
        Select dropdownMenu = new Select(getDriver().findElement(By.className("product_sort_container")));
        dropdownMenu.selectByVisibleText(visibleText);
    }

    public void choiceItem(String item){
        getDriver()
                .findElement(By.id(String.format("%s", item))).click();
    }

    public void shoppingCart(){
        getDriver().findElement(By.className("shopping_cart_link")).click();
    }

    public void clickCheckout(){
        getDriver().findElement(By.id("checkout")).click();
    }

    public void reactBurgerMenu(){
        getDriver().findElement(By.id("react-burger-menu-btn")).click();
    }

    public void fillOutOrderForm(String name, String surname, String postcode){
        getDriver().findElement(By.id("first-name")).sendKeys(name);
        getDriver().findElement(By.id("last-name")).sendKeys(surname);
        getDriver().findElement(By.id("postal-code")).sendKeys(postcode);
        getDriver().findElement(By.id("continue")).click();
    }

    public List <String> getListOfItemInCart(){
        WebElement cartList = getDriver().findElement(By.className("cart_list"));
        List<WebElement> cartItems = cartList.findElements(By.className("inventory_item_name"));
        return cartItems.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<Double> getListBeforeSorting(String sortName) {
        List<WebElement> beforeFilterPrice = getDriver().findElements(By.className("inventory_item_price"));
        List<Double> beforeFilterPriceList = new ArrayList<>();

        for (WebElement e : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }

        WebElement funnelIcon = getDriver().findElement(By.className("select_container"));
        funnelIcon.click();

        Select drpOrder = new Select(getDriver().findElement(By.className("product_sort_container")));
        drpOrder.selectByVisibleText(sortName);
        Collections.sort(beforeFilterPriceList);

        return beforeFilterPriceList;
    }

    public List<Double> getListAfterSorting() {
        List<WebElement> afterFilterPrice = getDriver().findElements(By.className("inventory_item_price"));
        List<Double> afterFilterPriceList = new ArrayList<>();

        for (WebElement e : afterFilterPrice) {
            afterFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }
        return afterFilterPriceList;
    }


    @Test
    public void standardUserLoginTest() {
        loginToSite(LOGIN);

        Assert.assertEquals(getDriver().getCurrentUrl(), MAIN_PAGE);
    }

    @Test
    public void nsergeevaTest() {
        loginToSite(LOGIN);

        WebElement addtocartButton = getDriver().findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']"));
        addtocartButton.click();

        WebElement removeButton = getDriver().findElement(By.xpath("//*[@id='remove-sauce-labs-backpack']"));
        Assert.assertEquals(removeButton.getText(), "Remove");

        WebElement cartbutton = getDriver().findElement(By.xpath("//*[@id='shopping_cart_container']/a"));
        cartbutton.click();
        Assert.assertEquals(getDriver().getTitle(), "Swag Labs");

        WebElement cartQuantity = getDriver().findElement(By.xpath("//*[@class='cart_quantity']"));

        WebElement cartremovebutton = getDriver().findElement(By.xpath("//*[@id='remove-sauce-labs-backpack']"));
        cartremovebutton.click();
        Assert.assertTrue(getDriver().findElements(By.xpath("//*[@id='remove-sauce-labs-backpack']")).isEmpty());
    }

    @Test
    public void continueShoppingTest()  {

        loginToSite(LOGIN);

        getDriver().findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        getDriver().findElement(By.xpath("//span[@class='shopping_cart_badge']")).click();
        getDriver().findElement(By.id("continue-shopping")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), MAIN_PAGE);
    }

    @Test
    public void sortingByPriceLowToHighTest() {
        loginToSite(LOGIN);
        List<Double> expectedResult = getListBeforeSorting("Price (low to high)");
        Assert.assertEquals(getListAfterSorting(), expectedResult);
        getDriver().quit();
    }

    @Test
    public void sortingByPriceHighToLowTest() {
        loginToSite(LOGIN);
        List<Double> expectedResult = getListBeforeSorting("Price (high to low)");
        Collections.reverse(expectedResult);
        Assert.assertEquals(getListAfterSorting(), expectedResult);
        getDriver().quit();
    }

    @Test
    public void sortByNameTest() {
        loginToSite(LOGIN);

        WebElement sortButton = getDriver().findElement(By.className("product_sort_container"));
        sortButton.click();

        WebElement NameZToA = getDriver().findElement(
                By.xpath("//*[@id='header_container']/div[2]/div/span/select/option[2]"));
        NameZToA.click();

        Assert.assertEquals(getDriver().findElement(By.className("inventory_item_name")).getText(),
                "Test.allTheThings() T-Shirt (Red)");
    }

    @Test
    public void continueShopping2Test() {
        loginToSite(LOGIN);

        WebElement addToCart = getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCart.click();

        WebElement shoppingCartLink = getDriver().findElement(By.className("shopping_cart_link"));
        shoppingCartLink.click();

        WebElement continueShopping = getDriver().findElement(By.id("continue-shopping"));
        continueShopping.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), MAIN_PAGE);
    }

    @Test
    public void goToAllItemsTest(){
        loginToSite(LOGIN);

        WebElement shopButton = getDriver().findElement(By.className("shopping_cart_link"));
        shopButton.click();

        reactBurgerMenu();

        WebElement allItemsLink = getDriver().findElement(By.id("inventory_sidebar_link"));
        getWait().until(ExpectedConditions.visibilityOf(allItemsLink));
        allItemsLink.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), MAIN_PAGE);
    }

    @Test
    public void lockedOutUserLoginTest() {
        loginToSite("locked_out_user");

        Assert.assertEquals(getDriver().findElement(By.xpath("//h3")).getText(), "Epic sadface: Sorry, this user has been locked out.");

    }

    @Test
    public void problemUserLoginTest() {

        loginToSite("problem_user");

        List<WebElement> listPhoto = getDriver().findElements(By.xpath("//div[@class = 'inventory_item']//a/img"));

        List<String> list = new ArrayList<>();

        for (WebElement w : listPhoto) {
            list.add(w.getAttribute("src"));
        }

        for (String l : list) {
            Assert.assertEquals(l, "https://www.saucedemo.com/static/media/sl-404.168b1cce.jpg");
        }
    }

    @Test
    public void checkItemsTest() {
        List<String> expectedResults = List.of(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)");

        loginToSite(LOGIN);

        List<WebElement> itemsList =  getDriver().findElements(By.xpath("//div[@class = 'inventory_item_name']"));
        List<String> itemsNamesList = new ArrayList<>();

        for (WebElement w : itemsList) {
            itemsNamesList.add(w.getText());
        }

        Assert.assertEquals(itemsNamesList, expectedResults);
    }

    @Test
    public void checkSortingTest() {
        List<String> expectedResults = List.of(
                "Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Onesie",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Bike Light",
                "Sauce Labs Backpack");

        loginToSite(LOGIN);

        WebElement sortingButton = getDriver().findElement(By.xpath("//span[@class = 'active_option']"));
        if (!getDriver().findElement(By.xpath("//span[@class = 'active_option']")).getText().equals("Name (A to Z)")) {
            sortingButton.click();
        }

        Select sorting = new Select(getDriver().findElement(By.xpath("//select[@class = 'product_sort_container']")));
        sorting.selectByIndex(1);

        List<WebElement> itemsList = getDriver().findElements(By.xpath("//div[@class = 'inventory_item_name']"));
        List<String> itemsNamesList = new ArrayList<>();

        for (WebElement w : itemsList) {
            itemsNamesList.add(w.getText());
        }

        Assert.assertEquals(itemsNamesList, expectedResults);
    }

    @Test
    public void addingToCardTest() {
        loginToSite(LOGIN);

        WebElement backPackButton = getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement shoppingCardLinkButton = getDriver().findElement(By.id("shopping_cart_container"));

        Assert.assertTrue(shoppingCardLinkButton.getText().isEmpty());
        Assert.assertEquals(backPackButton.getText(), "Add to cart");

        backPackButton.click();

        Assert.assertEquals(shoppingCardLinkButton.getText(), "1");
        Assert.assertEquals(getDriver().findElement(By.id("remove-sauce-labs-backpack")).getText(), "Remove");
    }

    @Test
    public void finishOrderTest(){
        loginToSite(LOGIN);
        choiceItem("add-to-cart-sauce-labs-bolt-t-shirt");
        shoppingCart();

        clickCheckout();

        fillOutOrderForm("name", "surname", "414525");
        getDriver().findElement(By.id("finish")).click();

        String finishMessage = getDriver().findElement(By.className("complete-header")).getText();
        String expectedFinishMessage = "Thank you for your order!";

        Assert.assertEquals(finishMessage, expectedFinishMessage);
        getDriver().quit();
    }

    @Test
    public void sortByNameAZTest(){
        loginToSite(LOGIN);

        sortElements("Price (low to high)");
        List<String> firstOrderItems = productNames();
        Collections.sort(firstOrderItems);

        sortElements("Name (A to Z)");
        List<String> sortOrderItems = productNames();

        Assert.assertEquals(firstOrderItems, sortOrderItems);
    }

    @Test
    public void sortByNameZATest(){
        loginToSite(LOGIN);

        List<String> firstOrderItems = productNames();
        firstOrderItems.sort(Collections.reverseOrder());

        sortElements("Name (Z to A)");
        List<String> sortOrderItems = productNames();

        Assert.assertEquals(firstOrderItems, sortOrderItems);
        getDriver().quit();
    }

    @Test
    public void testLogOut() {
        loginToSite(LOGIN);

        reactBurgerMenu();

        WebElement logOut = getDriver().findElement(By.id("logout_sidebar_link"));
        getWait().until(ExpectedConditions.visibilityOf(logOut));
        logOut.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void testRemoveFromCart() {
        loginToSite(LOGIN);
        choiceItem("add-to-cart-sauce-labs-backpack");
        shoppingCart();

        WebElement removeButton = getDriver().findElement(By.name("remove-sauce-labs-backpack"));

        Assert.assertEquals(removeButton.getText(), "Remove");

        WebElement cartButton = getDriver().findElement(By.id("shopping_cart_container"));
        cartButton.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.saucedemo.com/cart.html");

        getListOfItemInCart();
        Assert.assertFalse(getListOfItemInCart().isEmpty());
        Assert.assertEquals(getListOfItemInCart().get(0), "Sauce Labs Backpack");

        WebElement cartRemoveButton = getDriver().findElement(By.name("remove-sauce-labs-backpack"));
        cartRemoveButton.click();

        WebElement cartListAfterRemove = getDriver().findElement(By.className("cart_list"));
        List<WebElement> cartItemsAfterRemove = cartListAfterRemove.findElements(By.className("cart_item"));
        Assert.assertTrue(cartItemsAfterRemove.isEmpty());
    }

    @Test
    public void checkSocialMediaLinkTest(){
        String socialMedia = "Facebook";
        loginToSite(LOGIN);

        WebElement socialMediaLink = getDriver().findElement(By.xpath("//a[contains(text(), '" + socialMedia + "')]"));
        socialMediaLink.click();

        Set<String> windowsHandles =  getDriver().getWindowHandles();
        List<String> list = new ArrayList<>(windowsHandles);
        getDriver().switchTo().window(list.get(1));

        Assert.assertTrue(getDriver()
                .getCurrentUrl()
                .contains(socialMedia.toLowerCase()) && getDriver().getCurrentUrl()
                .contains(URL.substring(12, 17)));
    }

        @Test
        public void testUGLogOut(){
        loginToSite(LOGIN);

        reactBurgerMenu();

        WebElement logOut = getDriver().findElement(By.id("logout_sidebar_link"));
        getWait().until(ExpectedConditions.visibilityOf(logOut));
        logOut.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.saucedemo.com/");
        }

    @Test
    public void checkLoginWithIncorrectPasswordTest(){
        getDriver().get(URL);
        WebElement loginField = getDriver().findElement(By.id("user-name"));
        WebElement passwordField = getDriver().findElement(By.id("password"));
        WebElement loginButton = getDriver().findElement(By.name("login-button"));
        WebElement loginsList = getDriver().findElement(By.xpath("//div[@id = 'login_credentials']"));
        String[] users = loginsList.getText().split("\n");
        List<String> usersLogins = List.of(users);

        for (int i = 1; i < usersLogins.size(); i++) {
            loginField.clear();
            loginField.sendKeys(usersLogins.get(i).trim());
            passwordField.clear();
            passwordField.sendKeys("wrongPassword");
            loginButton.click();
            Assert.assertEquals(getDriver().findElement(By.xpath("//h3")).getText(),
                    "Epic sadface: Username and password do not match any user in this service");
        }
    }

    @Test
    public void logOutUlTest() {
        loginToSite(LOGIN);

        reactBurgerMenu();

        WebElement logOut = getDriver().findElement(By.id("logout_sidebar_link"));
        getWait().until(ExpectedConditions.visibilityOf(logOut));
        logOut.click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void resetAppStateTest() {
        loginToSite(LOGIN);

        choiceItem("add-to-cart-sauce-labs-backpack");
        getDriver().findElement(By.className("shopping_cart_badge")).click();
        reactBurgerMenu();

        WebElement resetSidebarLink = getDriver().findElement(By.id("reset_sidebar_link"));
        getWait().until(ExpectedConditions.visibilityOf(resetSidebarLink));
        resetSidebarLink.click();

        shoppingCart();

        WebElement cartListAfterRemove = getDriver().findElement(By.className("cart_list"));
        List<WebElement> cartItemsAfterRemove = cartListAfterRemove.findElements(By.className("cart_item"));

        Assert.assertTrue(cartItemsAfterRemove.isEmpty());

    }
}

