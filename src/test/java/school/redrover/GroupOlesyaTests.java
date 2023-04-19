package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

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

    public List<String> getListOfProductNames(){
        List<WebElement> el = getDriver().findElements(By.xpath("//div[@class = 'inventory_item_name']"));
        return el.stream().map(WebElement::getText).collect(toList());
    }

    public void sortElements(String visibleText){
        Select dropdownMenu = new Select(getDriver().findElement(By.className("product_sort_container")));
        dropdownMenu.selectByVisibleText(visibleText);
    }

    public void addToShoppingCart(String item){
        getDriver()
                .findElement(By.id(String.format("%s", item))).click();
    }

    public void goToShoppingCartPage(){
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
        return cartItems.stream().map(WebElement::getText).collect(toList());
    }
    private void goToAllItemsInBurgerMenu() {
        WebElement allItemsLink = getDriver().findElement(By.id("inventory_sidebar_link"));
        getWait().until(ExpectedConditions.visibilityOf(allItemsLink));
        allItemsLink.click();
    }

    public List<Double> listOfPrice() {

        return getListItems(By.className("inventory_item_price"))
                .stream()
                .map(WebElement::getText)
                .map(n -> n.replace("$", ""))
                .map(s -> Double.parseDouble(s))
                .collect(toList());
    }

    public void selectTypeOfSortingItems(String typeOfSorting){
        Select sorting = new Select(getDriver().findElement(By.xpath("//select[@class = 'product_sort_container']")));
        sorting.selectByVisibleText(typeOfSorting);
    }

    @Test
    public void standardUserLoginTest() {
        loginToSite(LOGIN);

        Assert.assertEquals(getDriver().getCurrentUrl(), MAIN_PAGE);
    }

    public List<String> getTextList (List<WebElement> list) {
        return list.stream().map(WebElement::getText).collect(toList());
    }

    public void clickOnEachElement (List<WebElement> list) {
        for (WebElement w : list) {
            w.click();
        }
    }

    public void addItemstoCartbyXpath (By xpath) {
        List<WebElement> addproductstocart  = getDriver().findElements(xpath);
        clickOnEachElement(addproductstocart);
    }

    @Test
    public void testAddtoCart() {
        loginToSite(LOGIN);

        List<WebElement> addproducts  = getDriver().findElements(By.xpath("//div[@class = 'inventory_item_name']"));
        List<String> expectedlist= getTextList (addproducts);

        addItemstoCartbyXpath(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']"));

        goToShoppingCartPage();
        Assert.assertEquals(getListOfItemInCart(),expectedlist);
    }

    @Ignore /*Bug!*/
    @Test
    public void testChangeQuantityinCart() {
        loginToSite(LOGIN);

        addItemstoCartbyXpath(By.xpath("//div[@class = 'inventory_item_name']"));

        goToShoppingCartPage();

        WebElement cartQuantity = getDriver().findElement(By.xpath("//*[@class='cart_quantity']"));
        cartQuantity.clear();
        cartQuantity.sendKeys("2");

        getDriver().findElement(By.id("continue-shopping")).click();
        goToShoppingCartPage();

        Assert.assertEquals(cartQuantity.getText(), "2");

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

        List<Double> pricesBeforeSort = listOfPrice();
        Collections.sort(pricesBeforeSort);

        sortElements("Price (low to high)");
        List<Double> pricesAfterSort = listOfPrice();

        Assert.assertEquals(pricesAfterSort, pricesBeforeSort);
    }

    @Test
    public void sortingByPriceHighToLowTest() {
        loginToSite(LOGIN);

        List<Double> pricesBeforeSort = listOfPrice();
        pricesBeforeSort.sort(Collections.reverseOrder());

        sortElements("Price (high to low)");
        List<Double> pricesAfterSort = listOfPrice();

        Assert.assertEquals(pricesAfterSort, pricesBeforeSort);
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

        goToShoppingCartPage();

        reactBurgerMenu();

        goToAllItemsInBurgerMenu();

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
        selectTypeOfSortingItems("Name (A to Z)");

        Assert.assertEquals(getListOfProductNames(), expectedResults);
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
        selectTypeOfSortingItems("Name (Z to A)");

        Assert.assertEquals(getListOfProductNames(), expectedResults);
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
        addToShoppingCart("add-to-cart-sauce-labs-bolt-t-shirt");
        goToShoppingCartPage();

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
        List<String> firstOrderItems = getListOfProductNames();
        Collections.sort(firstOrderItems);

        sortElements("Name (A to Z)");
        List<String> sortOrderItems = getListOfProductNames();

        Assert.assertEquals(firstOrderItems, sortOrderItems);
    }

    @Test
    public void sortByNameZATest(){
        loginToSite(LOGIN);

        List<String> firstOrderItems = getListOfProductNames();
        firstOrderItems.sort(Collections.reverseOrder());

        sortElements("Name (Z to A)");
        List<String> sortOrderItems = getListOfProductNames();

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
        addToShoppingCart("add-to-cart-sauce-labs-backpack");
        goToShoppingCartPage();

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

        addToShoppingCart("add-to-cart-sauce-labs-backpack");
        getDriver().findElement(By.className("shopping_cart_badge")).click();
        reactBurgerMenu();

        WebElement resetSidebarLink = getDriver().findElement(By.id("reset_sidebar_link"));
        getWait().until(ExpectedConditions.visibilityOf(resetSidebarLink));
        resetSidebarLink.click();

        goToShoppingCartPage();

        WebElement cartListAfterRemove = getDriver().findElement(By.className("cart_list"));
        List<WebElement> cartItemsAfterRemove = cartListAfterRemove.findElements(By.className("cart_item"));

        Assert.assertTrue(cartItemsAfterRemove.isEmpty());
    }

    @Test
    public void flowOfPurchaseTest(){
        String randomString = RandomStringUtils.randomAlphabetic(5);
        String randomDigits = RandomStringUtils.randomNumeric(6);

        loginToSite(LOGIN);
        addToShoppingCart("add-to-cart-sauce-labs-backpack");
        goToShoppingCartPage();

        getDriver().findElement(By.id("checkout")).click();
        getDriver().findElement(By.id("first-name")).sendKeys(randomString);
        getDriver().findElement(By.id("last-name")).sendKeys(randomString);
        getDriver().findElement(By.id("postal-code")).sendKeys(randomDigits);
        getDriver().findElement(By.id("continue")).click();
        getDriver().findElement(By.id("finish")).click();

        Assert.assertEquals(getDriver().findElement(By.id("checkout_complete_container")).getText(),
                "Thank you for your order!\n" +
                        "Your order has been dispatched, and will arrive just as fast as the pony can get there!\n" +
                        "Back Home");
        Assert.assertTrue(getDriver().findElement(By.id("back-to-products")).isDisplayed());
    }
}

