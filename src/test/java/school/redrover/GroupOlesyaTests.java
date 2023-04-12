package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class GroupOlesyaTests {
    private final String URL = "https://www.saucedemo.com/";
    private final String LOGIN = "standard_user";
    private final String NEW_PAGE = "https://www.saucedemo.com/inventory.html";
    private final String PASSWORD = "secret_sauce";
    private static WebDriver driverCha;

    static WebDriver createDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

    protected static WebDriver getDriver() {
        driverCha = createDriver();
        return driverCha;
    }

    private void loginToSite(String login, String password) {
        getDriver().get("https://www.saucedemo.com/");
        driverCha.findElement(By.name("user-name")).sendKeys(login);
        driverCha.findElement(By.name("password")).sendKeys(password);
        driverCha.findElement(By.name("login-button")).click();
    }

    public void standardUserLogin() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        driverCha = new ChromeDriver(chromeOptions);

        driverCha.get(URL);
        driverCha.findElement(By.id("user-name")).sendKeys("standard_user");
        driverCha.findElement(By.id("password")).sendKeys(PASSWORD);
        driverCha.findElement(By.id("login-button")).click();
    }

    public List<WebElement> getListItems() {
        standardUserLogin();

        return driverCha.findElements(By.xpath("//div[@class = 'inventory_item_name']"));
    }

    public List<WebElement> getListItems(By by) {

        return driverCha.findElements(by);
    }

    public String getSortingStatus() {

        return driverCha.findElement(By.xpath("//span[@class = 'active_option']")).getText();
    }

    public List<String> productNames(){
        List<WebElement> el = driverCha.findElements(By.xpath("//div[@class = 'inventory_item_name']"));
        return el.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void sortElements(String visibleText){
        Select dropdownMenu = new Select(driverCha.findElement(By.className("product_sort_container")));
        dropdownMenu.selectByVisibleText(visibleText);
    }

    public void choiceItem(String item){
        driverCha
                .findElement(By.id(String.format("%s", item))).click();
    }

    public void shoppingCart(){
        driverCha.findElement(By.className("shopping_cart_link")).click();
    }

    public void clickCheckout(){
        driverCha.findElement(By.id("checkout")).click();
    }

    public void fillOutOrderForm(String name, String surname, String postcode){
        driverCha.findElement(By.id("first-name")).sendKeys(name);
        driverCha.findElement(By.id("last-name")).sendKeys(surname);
        driverCha.findElement(By.id("postal-code")).sendKeys(postcode);
        driverCha.findElement(By.id("continue")).click();
    }

    public List <String> getListOfItemInCart(){
        WebElement cartList = driverCha.findElement(By.className("cart_list"));
        List<WebElement> cartItems = cartList.findElements(By.className("inventory_item_name"));
        return cartItems.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<Double> getListBeforeSorting(String sortName) {
        List<WebElement> beforeFilterPrice = driverCha.findElements(By.className("inventory_item_price"));
        List<Double> beforeFilterPriceList = new ArrayList<>();

        for (WebElement e : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }

        WebElement funnelIcon = driverCha.findElement(By.className("select_container"));
        funnelIcon.click();

        Select drpOrder = new Select(driverCha.findElement(By.className("product_sort_container")));
        drpOrder.selectByVisibleText(sortName);
        Collections.sort(beforeFilterPriceList);

        return beforeFilterPriceList;
    }

    public List<Double> getListAfterSorting() {
        List<WebElement> afterFilterPrice = driverCha.findElements(By.className("inventory_item_price"));
        List<Double> afterFilterPriceList = new ArrayList<>();

        for (WebElement e : afterFilterPrice) {
            afterFilterPriceList.add(Double.valueOf(e.getText().replace("$", "")));
        }
        return afterFilterPriceList;
    }


    @Test
    public void standardUserLoginTest() {
        standardUserLogin();

        Assert.assertEquals(driverCha.getCurrentUrl(), NEW_PAGE);
        driverCha.quit();
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
    //testing continue shopping button
    
    public void testContinueShopping()  {

        loginToSite(LOGIN, PASSWORD);

        driverCha.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        driverCha.findElement(By.xpath("//span[@class='shopping_cart_badge']")).click();
        driverCha.findElement(By.id("continue-shopping")).click();



        Assert.assertEquals(driverCha.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        driverCha.quit();
    }
    
    @Test
    public void checkSortingByPriceLowToHigh() { //Stoyana's Test
        loginToSite(LOGIN, PASSWORD);
        List<Double> expectedResult = getListBeforeSorting("Price (low to high)");
        Assert.assertEquals(getListAfterSorting(), expectedResult);
        driverCha.quit();
    }

    @Test
    public void checkSortingByPriceHighToLow() { //Stoyana's Test
        loginToSite(LOGIN, PASSWORD);
        List<Double> expectedResult = getListBeforeSorting("Price (high to low)");
        Collections.reverse(expectedResult);
        Assert.assertEquals(getListAfterSorting(), expectedResult);
        driverCha.quit();
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

        WebElement addToCart = driverCha.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCart.click();

        WebElement shoppingCartLink = driverCha.findElement(By.className("shopping_cart_link"));
        shoppingCartLink.click();

        WebElement continueShopping = driverCha.findElement(By.id("continue-shopping"));
        continueShopping.click();

        Assert.assertEquals(driverCha.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        driverCha.quit();
    }

    @Test
    public void test10GoToAllItems() throws InterruptedException {
        loginToSite(LOGIN, PASSWORD);

        WebElement shopButton = driverCha.findElement(By.className("shopping_cart_link"));
        shopButton.click();

        WebElement burgerMenuLink = driverCha.findElement(By.id("react-burger-menu-btn"));
        burgerMenuLink.click();

        Thread.sleep(3000);

        WebElement allItemsLink = driverCha.findElement(By.id("inventory_sidebar_link"));
        allItemsLink.click();

        Assert.assertEquals(driverCha.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        driverCha.quit();
    }

    @Test
    public void LockedOutUserLoginTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        driverCha = new ChromeDriver(chromeOptions);

        driverCha.get(URL);
        driverCha.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driverCha.findElement(By.id("password")).sendKeys(PASSWORD);
        driverCha.findElement(By.id("login-button")).click();

        Assert.assertEquals(driverCha.findElement(By.xpath("//h3")).getText(), "Epic sadface: Sorry, this user has been locked out.");
        driverCha.quit();
    }

    @Test
    public void ProblemUserLoginTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        driverCha = new ChromeDriver(chromeOptions);

        driverCha.get(URL);
        driverCha.findElement(By.id("user-name")).sendKeys("problem_user");
        driverCha.findElement(By.id("password")).sendKeys(PASSWORD);
        driverCha.findElement(By.id("login-button")).click();

        List<WebElement> listPhoto = driverCha.findElements(By.xpath("//div[@class = 'inventory_item']//a/img"));

        List<String> list = new ArrayList<>();

        for (WebElement w : listPhoto) {
            list.add(w.getAttribute("src"));
        }

        for (String l : list) {
            Assert.assertEquals(l, "https://www.saucedemo.com/static/media/sl-404.168b1cce.jpg");
        }
        driverCha.quit();
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
        driverCha.quit();
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

        WebElement sortingButton = driverCha.findElement(By.xpath("//span[@class = 'active_option']"));
        if (!getSortingStatus().equals("Name (A to Z)")) {
            sortingButton.click();
        }

        Select sorting = new Select(driverCha.findElement(By.xpath("//select[@class = 'product_sort_container']")));
        sorting.selectByIndex(1);

        List<WebElement> itemsList = driverCha.findElements(By.xpath("//div[@class = 'inventory_item_name']"));
        List<String> itemsNamesList = new ArrayList<>();

        for (WebElement w : itemsList) {
            itemsNamesList.add(w.getText());
        }

        Assert.assertEquals(itemsNamesList, expectedResults);
        driverCha.quit();
    }

    @Test
    public void addingToCardTest() {
        standardUserLogin();

        WebElement backPackButton = driverCha.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement shoppingCardLinkButton = driverCha.findElement(By.id("shopping_cart_container"));

        Assert.assertTrue(shoppingCardLinkButton.getText().isEmpty());
        Assert.assertEquals(backPackButton.getText(), "Add to cart");

        backPackButton.click();

        Assert.assertEquals(shoppingCardLinkButton.getText(), "1");
        Assert.assertEquals(driverCha.findElement(By.id("remove-sauce-labs-backpack")).getText(), "Remove");
        driverCha.quit();
    }

    @Test
    public void finishOrderTest(){
        loginToSite(LOGIN, PASSWORD);
        choiceItem("add-to-cart-sauce-labs-bolt-t-shirt");
        shoppingCart();

        clickCheckout();

        fillOutOrderForm("name", "surname", "414525");
        driverCha.findElement(By.id("finish")).click();

        String finishMessage = driverCha.findElement(By.className("complete-header")).getText();
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

    @Test
    public void testLogOut() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        loginToSite(LOGIN, PASSWORD);

        WebElement burgerMenuLink = driverCha.findElement(By.id("react-burger-menu-btn"));
        burgerMenuLink.click();

        Thread.sleep(3000);

        WebElement logOut = driverCha.findElement(By.id("logout_sidebar_link"));
        logOut.click();

        Assert.assertEquals(driverCha.getCurrentUrl(), "https://www.saucedemo.com/");

        driverCha.quit();

    }
    @Test
    public void testRemoveFromCart() {
        loginToSite(LOGIN, PASSWORD);
        choiceItem("add-to-cart-sauce-labs-backpack");
        shoppingCart();

        WebElement removeButton = driverCha.findElement(By.name("remove-sauce-labs-backpack"));

        Assert.assertEquals(removeButton.getText(), "Remove");

        WebElement cartButton = driverCha.findElement(By.id("shopping_cart_container"));
        cartButton.click();

        Assert.assertEquals(driverCha.getCurrentUrl(), "https://www.saucedemo.com/cart.html");

        getListOfItemInCart();
        Assert.assertFalse(getListOfItemInCart().isEmpty());
        Assert.assertEquals(getListOfItemInCart().get(0), "Sauce Labs Backpack");

        WebElement cartRemoveButton = driverCha.findElement(By.name("remove-sauce-labs-backpack"));
        cartRemoveButton.click();

        WebElement cartListAfterRemove = driverCha.findElement(By.className("cart_list"));
        List<WebElement> cartItemsAfterRemove = cartListAfterRemove.findElements(By.className("cart_item"));
        Assert.assertTrue(cartItemsAfterRemove.isEmpty());
    }
}

