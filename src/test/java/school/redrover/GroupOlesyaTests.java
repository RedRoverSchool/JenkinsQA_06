package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class GroupOlesyaTests {
    private final String URL = "https://www.saucedemo.com/";
    private final String LOGIN = "standard_user";
    private final String NEW_PAGE = "https://www.saucedemo.com/inventory.html";
    private final String PASSWORD = "secret_sauce";
    private static WebDriver driver;

    static WebDriver createDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

    protected static WebDriver getDriver() {
        driver = createDriver();
        return driver;
    }

    private void loginToSite(String login, String password) {
        getDriver().get("https://www.saucedemo.com/");
        driver.findElement(By.name("user-name")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login-button")).click();
    }

    public void standardUserLogin() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        driver = new ChromeDriver(chromeOptions);

        driver.get(URL);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("login-button")).click();
    }

    public List<WebElement> getListItems() {
        standardUserLogin();

        return driver.findElements(By.xpath("//div[@class = 'inventory_item_name']"));
    }

    public List<WebElement> getListItems(By by) {

        return driver.findElements(by);
    }

    public String getSortingStatus() {

        return driver.findElement(By.xpath("//span[@class = 'active_option']")).getText();
    }

    public List<String> productNames(){
        List<WebElement> el = driver.findElements(By.xpath("//div[@class = 'inventory_item_name']"));
        return el.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void sortElements(String visibleText){
        Select dropdownMenu = new Select(driver.findElement(By.className("product_sort_container")));
        dropdownMenu.selectByVisibleText(visibleText);
    }

    public void choiceItem(String item){
        driver
                .findElement(By.id(String.format("%s", item))).click();
    }

    public void shoppingCart(){
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    public void clickCheckout(){
        driver.findElement(By.id("checkout")).click();
    }

    public void fillOutOrderForm(String name, String surname, String postcode){
        driver.findElement(By.id("first-name")).sendKeys(name);
        driver.findElement(By.id("last-name")).sendKeys(surname);
        driver.findElement(By.id("postal-code")).sendKeys(postcode);
        driver.findElement(By.id("continue")).click();
    }

    public List <String> getListOfItemInCart(){
        WebElement cartList = driver.findElement(By.className("cart_list"));
        List<WebElement> cartItems = cartList.findElements(By.className("inventory_item_name"));
        return cartItems.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<Double> getListBeforeSorting(String sortName) {
        List<WebElement> beforeFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> beforeFilterPriceList = new ArrayList<>();

        for (WebElement e : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }

        WebElement funnelIcon = driver.findElement(By.className("select_container"));
        funnelIcon.click();

        Select drpOrder = new Select(driver.findElement(By.className("product_sort_container")));
        drpOrder.selectByVisibleText(sortName);
        Collections.sort(beforeFilterPriceList);

        return beforeFilterPriceList;
    }

    public List<Double> getListAfterSorting() {
        List<WebElement> afterFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> afterFilterPriceList = new ArrayList<>();

        for (WebElement e : afterFilterPrice) {
            afterFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }
        return afterFilterPriceList;
    }


    @Test
    public void standardUserLoginTest() {
        standardUserLogin();

        Assert.assertEquals(driver.getCurrentUrl(), NEW_PAGE);
        driver.quit();
    }

    @Test
    public void nsergeevaTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.saucedemo.com/inventory.html");

        WebElement username = driver.findElement(By.name("user-name"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.name("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        WebElement addtocartButton = driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
        addtocartButton.click();

        WebElement removeButton = driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
        Assert.assertEquals(removeButton.getText(), "Remove");

        WebElement cartbutton = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        cartbutton.click();
        Assert.assertEquals(driver.getTitle(), "Swag Labs");

        WebElement cartQuantity = driver.findElement(By.xpath("//*[@class=\"cart_quantity\"]"));

        WebElement cartremovebutton = driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
        cartremovebutton.click();
        Assert.assertTrue(driver.findElements(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]")).isEmpty());

        driver.quit();
    }

    @Test
    public void checkSortingByPriceLowToHigh() { //Stoyana's Test

        standardUserLogin();

        List<WebElement> beforeFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> beforeFilterPriceList = new ArrayList<>();

        for (WebElement e : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }

        WebElement funnelIcon = driver.findElement(By.className("select_container"));
        funnelIcon.click();

        Select drpOrder = new Select(driver.findElement(By.className("product_sort_container")));
        drpOrder.selectByVisibleText("Price (low to high)");

        List<WebElement> afterFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> afterFilterPriceList = new ArrayList<>();

        for (WebElement e : afterFilterPrice) {
            afterFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }
        Collections.sort(beforeFilterPriceList);

        Assert.assertEquals(beforeFilterPriceList, afterFilterPriceList);

        driver.quit();
    }
    @Test
    //testing continue shopping button
    
    public void testContinueShopping()  {

        loginToSite(LOGIN, PASSWORD);

        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).click();
        driver.findElement(By.id("continue-shopping")).click();



        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        driver.quit();
    }

    @Test
    public void checkSortinByPriceLowToHigh() { //Stoyana's Test
        loginToSite(LOGIN, PASSWORD);
        List<Double> expectedResult = getListBeforeSorting("Price (low to high)");
        Assert.assertEquals(getListAfterSorting(), expectedResult);
        driver.quit();
    }

    @Test
    public void checkSortingByPriceHighToLow() { //Stoyana's Test

        standardUserLogin();
        List<WebElement> beforeFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> beforeFilterPriceList = new ArrayList<>();

        for (WebElement e : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }

        WebElement funnelIcon = driver.findElement(By.className("select_container"));
        funnelIcon.click();

        Select drpOrder = new Select(driver.findElement(By.className("product_sort_container")));
        drpOrder.selectByVisibleText("Price (high to low)");

        List<WebElement> afterFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> afterFilterPriceList = new ArrayList<>();

        for (WebElement e : afterFilterPrice) {
            afterFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }
        Collections.sort(beforeFilterPriceList);
        Collections.reverse(beforeFilterPriceList); //reverse the sorted list

        Assert.assertEquals(beforeFilterPriceList, afterFilterPriceList);

        driver.quit();
        loginToSite(LOGIN, PASSWORD);
        List<Double> expectedResult = getListBeforeSorting("Price (high to low)");
        Collections.reverse(expectedResult);
        Assert.assertEquals(getListAfterSorting(), expectedResult);
        driver.quit();
    }

    @Test
    public void testSortByName() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement login = driver.findElement(By.name("user-name"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.name("login-button"));

        login.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        button.click();

        WebElement sortButton = driver.findElement(By.className("product_sort_container"));
        sortButton.click();

        WebElement NameZToA = driver.findElement(
                By.xpath("//*[@id='header_container']/div[2]/div/span/select/option[2]"));
        NameZToA.click();

        Assert.assertEquals(driver.findElement(By.className("inventory_item_name")).getText(),
                "Test.allTheThings() T-Shirt (Red)");

        driver.quit();
    }

    public void test9ContinueShopping() {
        loginToSite(LOGIN, PASSWORD);

        WebElement addToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCart.click();

        WebElement shoppingCartLink = driver.findElement(By.className("shopping_cart_link"));
        shoppingCartLink.click();

        WebElement continueShopping = driver.findElement(By.id("continue-shopping"));
        continueShopping.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        driver.quit();
    }

    @Test
    public void test10GoToAllItems() throws InterruptedException {
        loginToSite(LOGIN, PASSWORD);

        WebElement shopButton = driver.findElement(By.className("shopping_cart_link"));
        shopButton.click();

        WebElement burgerMenuLink = driver.findElement(By.id("react-burger-menu-btn"));
        burgerMenuLink.click();

        Thread.sleep(3000);

        WebElement allItemsLink = driver.findElement(By.id("inventory_sidebar_link"));
        allItemsLink.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        driver.quit();
    }

    @Test
    public void LockedOutUserLoginTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        driver = new ChromeDriver(chromeOptions);

        driver.get(URL);
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h3")).getText(), "Epic sadface: Sorry, this user has been locked out.");
        driver.quit();
    }

    @Test
    public void problemUserLoginTest() {

        loginToSite("problem_user", PASSWORD);

        List<WebElement> listPhoto = driver.findElements(By.xpath("//div[@class = 'inventory_item']//a/img"));

        List<String> list = new ArrayList<>();

        for (WebElement w : listPhoto) {
            list.add(w.getAttribute("src"));
        }

        for (String l : list) {
            Assert.assertEquals(l, "https://www.saucedemo.com/static/media/sl-404.168b1cce.jpg");
        }
        driver.quit();
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

        standardUserLogin();

        List<WebElement> itemsList = getListItems();
        List<String> itemsNamesList = new ArrayList<>();

        for (WebElement w : itemsList) {
            itemsNamesList.add(w.getText());
        }

        Assert.assertEquals(itemsNamesList, expectedResults);
        driver.quit();
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

        standardUserLogin();

        WebElement sortingButton = driver.findElement(By.xpath("//span[@class = 'active_option']"));
        if (!getSortingStatus().equals("Name (A to Z)")) {
            sortingButton.click();
        }

        Select sorting = new Select(driver.findElement(By.xpath("//select[@class = 'product_sort_container']")));
        sorting.selectByIndex(1);

        List<WebElement> itemsList = driver.findElements(By.xpath("//div[@class = 'inventory_item_name']"));
        List<String> itemsNamesList = new ArrayList<>();

        for (WebElement w : itemsList) {
            itemsNamesList.add(w.getText());
        }

        Assert.assertEquals(itemsNamesList, expectedResults);
        driver.quit();
    }

    @Test
    public void addingToCardTest() {
        standardUserLogin();

        WebElement backPackButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement shoppingCardLinkButton = driver.findElement(By.id("shopping_cart_container"));

        Assert.assertTrue(shoppingCardLinkButton.getText().isEmpty());
        Assert.assertEquals(backPackButton.getText(), "Add to cart");

        backPackButton.click();

        Assert.assertEquals(shoppingCardLinkButton.getText(), "1");
        Assert.assertEquals(driver.findElement(By.id("remove-sauce-labs-backpack")).getText(), "Remove");
        driver.quit();
    }

    @Test
    public void finishOrderTest(){
        loginToSite(LOGIN, PASSWORD);
        choiceItem("add-to-cart-sauce-labs-bolt-t-shirt");
        shoppingCart();

        clickCheckout();

        fillOutOrderForm("name", "surname", "414525");
        driver.findElement(By.id("finish")).click();

        String finishMessage = driver.findElement(By.className("complete-header")).getText();
        String expectedFinishMessage = "Thank you for your order!";

        Assert.assertEquals(finishMessage, expectedFinishMessage);
    }

    @Test
    public void sortByNameAZTest(){
        loginToSite(LOGIN, PASSWORD);

        sortElements("Price (low to high)");
        List<String> firstOrderItems = productNames();
        Collections.sort(firstOrderItems);

        sortElements("Name (A to Z)");
        List<String> sortOrderItems = productNames();

        Assert.assertEquals(firstOrderItems, sortOrderItems);
    }

    @Test
    public void sortByNameZATest(){
        loginToSite(LOGIN, PASSWORD);

        List<String> firstOrderItems = productNames();
        firstOrderItems.sort(Collections.reverseOrder());

        sortElements("Name (Z to A)");
        List<String> sortOrderItems = productNames();

        Assert.assertEquals(firstOrderItems, sortOrderItems);
    }

    @Ignore
    @Test
    public void testLogOut() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        loginToSite(LOGIN, PASSWORD);

        WebElement burgerMenuLink = driver.findElement(By.id("react-burger-menu-btn"));
        burgerMenuLink.click();

        Thread.sleep(3000);

        WebElement logOut = driver.findElement(By.id("logout_sidebar_link"));
        logOut.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

        driver.quit();

    }
    @Test
    public void testRemoveFromCart() {
        loginToSite(LOGIN, PASSWORD);
        choiceItem("add-to-cart-sauce-labs-backpack");
        shoppingCart();

        WebElement removeButton = driver.findElement(By.name("remove-sauce-labs-backpack"));

        Assert.assertEquals(removeButton.getText(), "Remove");

        WebElement cartButton = driver.findElement(By.id("shopping_cart_container"));
        cartButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");

        getListOfItemInCart();
        Assert.assertFalse(getListOfItemInCart().isEmpty());
        Assert.assertEquals(getListOfItemInCart().get(0), "Sauce Labs Backpack");

        WebElement cartRemoveButton = driver.findElement(By.name("remove-sauce-labs-backpack"));
        cartRemoveButton.click();

        WebElement cartListAfterRemove = driver.findElement(By.className("cart_list"));
        List<WebElement> cartItemsAfterRemove = cartListAfterRemove.findElements(By.className("cart_item"));
        Assert.assertTrue(cartItemsAfterRemove.isEmpty());
    }

    @Test
    public void checkSocialMediaLinkTest(){
        String socialMedia = "Facebook";
        loginToSite(LOGIN, PASSWORD);

        WebElement socialMediaLink = driver.findElement(By.xpath("//a[contains(text(), '" + socialMedia + "')]"));
        socialMediaLink.click();

        Set<String> windowsHandles =  driver.getWindowHandles();
        List<String> list = new ArrayList<>(windowsHandles);
        driver.switchTo().window(list.get(1));

        Assert.assertTrue(driver.getCurrentUrl().contains(socialMedia.toLowerCase()) && driver.getCurrentUrl().contains(URL.substring(12, 17)));
    }
    @Test
        public void testUGLogOut() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement login = driver.findElement(By.name("user-name"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.name("login-button"));

        login.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        button.click();

        WebElement dropDown = driver.findElement(By.id("react-burger-menu-btn"));
        dropDown.click();

        Thread.sleep(2000);

        WebElement logOut = driver.findElement(By.id("logout_sidebar_link"));
        logOut.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

        }
}

