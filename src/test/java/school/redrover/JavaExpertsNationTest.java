package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getDriver;
import static org.testng.Assert.*;

public class JavaExpertsNationTest extends BaseTest {

    private static final String email = "test" + Math.round(Math.random()*10000) + "@mail.com";

    @Test
    public void testPageTitle(){
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = getDriver().getTitle();

        assertEquals("Web form", title, "Test page title is fail");
    }

    @Test
    public void testFormSubmit(){
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();

        assertEquals("Received!", value, "Form submit is fail");
    }

    @Ignore
    @Test
    public void testRegisterUser(){
        getDriver().get("http://selenium1py.pythonanywhere.com/en-gb/accounts/login/");

        WebElement registration_email = getDriver().findElement(By.cssSelector("input[name='registration-email']"));
        registration_email.sendKeys(email);

        WebElement password1 = getDriver().findElement(By.cssSelector("input[name='registration-password1']"));
        password1.sendKeys(email);

        WebElement password2 = getDriver().findElement(By.cssSelector("input[name='registration-password2']"));
        password2.sendKeys(email);

        getDriver().findElement(By.cssSelector("button[name='registration_submit']")).click();

        assertFalse(getDriver().findElements(By.cssSelector(".alertinner.wicon")).isEmpty(),
                "User registration is fail");
    }

    @Ignore
    @Test(dependsOnMethods = "testRegisterUser")
    public void testLoginUser(){
        getDriver().get("http://selenium1py.pythonanywhere.com/en-gb/accounts/login/");

        WebElement login_email = getDriver().findElement(By.cssSelector("input[name='login-username']"));
        login_email.sendKeys(email);

        WebElement password = getDriver().findElement(By.cssSelector("input[name='login-password']"));
        password.sendKeys(email);

        getDriver().findElement(By.cssSelector("button[name='login_submit']")).click();

        assertFalse(getDriver().findElements(By.cssSelector(".alertinner.wicon")).isEmpty(),
                "User login is fail");
    }
    @Test
    public void testShowBasket(){
        getDriver().get("http://selenium1py.pythonanywhere.com/ru/");

        assertFalse(getDriver().findElements(By.cssSelector("span.btn-group")).isEmpty());
    }
    @Test
    public void testShowSearchField(){
        getDriver().get("http://selenium1py.pythonanywhere.com/ru/");
        assertFalse(getDriver().findElements(By.id("id_q")).isEmpty());
        assertFalse(getDriver().findElements(By.cssSelector("input[value='Найти']")).isEmpty());
    }
    @Test(dependsOnMethods = "testShowSearchField")
    public void testSearchGoods(){
        String searchText = "The shellcoder's handbook";
        getDriver().get("http://selenium1py.pythonanywhere.com/ru/");

        WebElement searchField = getDriver().findElement(By.id("id_q"));
        searchField.sendKeys(searchText);

        WebElement searchButton = getDriver().findElement(By.cssSelector("input[value='Найти']"));
        searchButton.click();

        assertEquals(getDriver().findElements(By.linkText(searchText)).size(), 1);

    }
    @Test(dependsOnMethods = "testShowSearchField")
    public void testAddToBasket(){
        String searchText = "The shellcoder's handbook";
        getDriver().get("http://selenium1py.pythonanywhere.com/ru/");

        WebElement searchField = getDriver().findElement(By.id("id_q"));
        searchField.sendKeys(searchText);

        WebElement searchButton = getDriver().findElement(By.cssSelector("input[value='Найти']"));
        searchButton.click();

        assertEquals(getDriver().findElements(By.linkText(searchText)).size(), 1);

        WebElement addingButton = getDriver().findElement(By.cssSelector("button[type='submit'].btn.btn-primary"));
        addingButton.click();

        assertFalse(getDriver().findElements(By.cssSelector
                (" div.alert-success:first-of-type div.alertinner")).isEmpty());
        assertEquals(getDriver().findElement(By.cssSelector(" div.alertinner p strong")).getText(),
                getDriver().findElement(By.cssSelector(" div.product_price p.price_color")).getText());
    }

    @Ignore
    @Test(dependsOnMethods = "testShowSearchField")
    public void testChangeAmountOfGoods(){
        String searchText = "The shellcoder's handbook";
        getDriver().get("http://selenium1py.pythonanywhere.com/ru/basket/");

        WebElement searchField = getDriver().findElement(By.id("id_q"));
        searchField.sendKeys(searchText);

        WebElement searchButton = getDriver().findElement(By.cssSelector("input[value='Найти']"));
        searchButton.click();

        assertEquals(getDriver().findElements(By.linkText(searchText)).size(), 1);

        WebElement addingButton = getDriver().findElement(By.cssSelector("button[type='submit'].btn.btn-primary"));
        addingButton.click();

        assertFalse(getDriver().findElements(By.cssSelector
                (" div.alert-success:first-of-type div.alertinner")).isEmpty());
        assertEquals(getDriver().findElement(By.cssSelector(" div.alertinner p strong")).getText(),
                getDriver().findElement(By.cssSelector(" div.product_price p.price_color")).getText());

        WebElement watchBasketButton = getDriver().findElement(By.cssSelector("p a[href='/ru/basket/'].btn.btn-info"));
        watchBasketButton.click();

        WebElement inputField = getDriver().findElement(By.id("id_form-0-quantity"));
        inputField.clear();
        inputField.sendKeys("10");

        WebElement updateButton = getDriver().findElement(By.cssSelector("span button.btn-default[type='submit']"));
        updateButton.click();

        double originPrice = Double.parseDouble(getDriver().findElement(By.cssSelector("div.col-sm-1 p.price_color.align-right"))
                .getText().replaceAll("£", "").replace(',', '.'))*10;
        String newPriceString = getDriver().findElement(By.cssSelector("tbody tr:nth-child(2) td.align-right"))
                .getText().replaceAll(" £", "")
                .replace(',', '.').replace(" ", "");
        double newPrice = Double.parseDouble(newPriceString);
        assertEquals(newPrice, originPrice);

    }
}
