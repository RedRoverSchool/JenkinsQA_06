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

public class GroupOlesyaTests {
    private final String URL = "https://www.saucedemo.com/";
    private final String PASSWORD = "secret_sauce";
    private WebDriver driverCha;

    private void loginToSite(WebDriver driver) {

        driver.get("https://www.saucedemo.com/");

        WebElement login = driver.findElement(By.name("user-name"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.name("login-button"));

        login.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        button.click();
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

    public String getSortingStatus() {

        return driverCha.findElement(By.xpath("//span[@class = 'active_option']")).getText();
    }

    @Test
    public void nsergeevaTest (){
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
         Assert.assertEquals(removeButton.getText(),"Remove");

         WebElement cartbutton = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
         cartbutton.click();
         Assert.assertEquals(driver.getTitle(), "Swag Labs");

         WebElement cartQuantity = driver.findElement(By.xpath("//*[@class=\"cart_quantity\"]"));

            //removing backpack from the cart:
         WebElement cartremovebutton = driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
         cartremovebutton.click();
         Assert.assertEquals(driver.findElements(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]")).isEmpty(), true);

         driver.quit();
    }

    @Test
    public void checkPriceLowToHigh() { //Stoyana's Test

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement login = driver.findElement(By.name("user-name"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.name("login-button"));

        login.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        button.click();

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
    public void checkPriceHighToLow() { //Stoyana's Test

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement login = driver.findElement(By.name("user-name"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.name("login-button"));

        login.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        button.click();

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
    }

    @Test
    public void test9ContinueShopping() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();
        loginToSite(driver);

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
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();
        loginToSite(driver);

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
    public void standardUserLoginTest() {
        standardUserLogin();

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
}
