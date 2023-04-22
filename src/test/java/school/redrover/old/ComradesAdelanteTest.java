package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
@Ignore
public class ComradesAdelanteTest extends BaseTest {

    @Test
    public void testHeaderOpenWeather() {
        Reporter.log("Tests started 1", true);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get("https://openweathermap.org");

        WebElement textHeader = getDriver().findElement(
                By.xpath("//h1/span[@class='white-text']")
        );

        String expectedResult = "OpenWeather";
        String actualResult = textHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
        Reporter.log("Tests stop",true);
    }

    @Ignore
    @Test
    public void testHeaderSignInPage() throws InterruptedException {
        Reporter.log("Tests started 2", true);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        getDriver().get("https://openweathermap.org");

        WebElement linkSignIn = getDriver().findElement(
                By.xpath("//div/ul/li[@class='user-li']/a")
        );
        linkSignIn.sendKeys(Keys.RETURN);
        Thread.sleep(1500);

        WebElement textHeader = getDriver().findElement(
                By.xpath("//h3[@class='first-child']")
        );
        Thread.sleep(500);

        String expectedResult = "Sign In To Your Account";
        String actualResult = textHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
        Reporter.log("Tests stop",true);
    }

    @Test
    public void MariaLuchnikovaTest() {

        Reporter.log("Tests started 3", true);
        getDriver().get("https://www.saucedemo.com");

        WebElement login = getDriver().findElement(By.id("user-name"));
        login.sendKeys("standard_user");
        WebElement password = getDriver().findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = getDriver().findElement(By.id("login-button"));
        loginButton.click();

        //Thread.sleep(3000);

        WebElement Backpack = getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack"));
        Backpack.click();

        WebElement tShirt = getDriver().findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        tShirt.click();

        WebElement onesie = getDriver().findElement(By.id("add-to-cart-sauce-labs-onesie"));
        onesie.click();

        WebElement cart = getDriver().findElement(By.className("shopping_cart_link"));
        cart.click();

        List<WebElement> items = getDriver().findElements(By.className("cart_item"));

        int countItems = items.size();
        Assert.assertEquals(countItems, 3);
        Reporter.log("Tests stop",true);
    }
    @Ignore
    @Test
    public void nikolayMarkovTest() {
        getDriver().get("https://www.w3schools.com/");

        Reporter.log("Tests started 4", true);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement search;
        search = getDriver().findElement(By.xpath("//form/input[@id = 'search2']"));
        search.sendKeys("HTML Tutorial");
        search.sendKeys(Keys.RETURN);

        WebElement textBox;
        textBox = getDriver().findElement(By.xpath("//input[@name = 'ex1']"));
        textBox.sendKeys("title");
        textBox.sendKeys(Keys.RETURN);

        ArrayList<String> words = new ArrayList<>(getDriver().getWindowHandles());// Записываем  открытые вкладки {0,1,...}
        getDriver().switchTo().window(words.get(1));// Выбираем вкладку

        WebElement submit;
        submit = getDriver().findElement(By.xpath("//button[@id='answerbutton']"));
        submit.click();
        WebElement result;
        result = getDriver().findElement(By.xpath("//*[@id='assignmentCorrect']/h2"));

        String actualResultOne = result.getText();

        WebElement submitConfirm;
        submitConfirm = getDriver().findElement(By.xpath("//div[@class='exercisemenuinner']//a[2]"));
        submitConfirm.click();

        textBox = getDriver().findElement(By.xpath("//*[@id='assignmentcontainer']/input[1]"));
        textBox.sendKeys("250");
        WebElement textBoxOne = getDriver().findElement(By.xpath("//*[@id='assignmentcontainer']/input[2]"));
        textBoxOne.sendKeys("400");
        textBoxOne.sendKeys(Keys.RETURN);

        result = getDriver().findElement(By.xpath("//*[@id='assignmentCorrect']/h2"));

        String actualResultTwo = result.getText();

        submitConfirm = getDriver().findElement(By.xpath("//div[@class='exercisemenuinner']//a[3]"));
        submitConfirm.click();

        textBox = getDriver().findElement(By.xpath("//*[@id='assignmentcontainer']/input"));
        textBox.sendKeys("href=");
        textBox.sendKeys(Keys.RETURN);

        result = getDriver().findElement(By.xpath("//*[@id='assignmentCorrect']/h2"));

        String actualResultThree = result.getText();

        submitConfirm = getDriver().findElement(By.xpath("//div[@class='exercisemenuinner']//a[4]"));
        submitConfirm.click();

        textBox = getDriver().findElement(By.xpath("//*[@id='assignmentcontainer']/input"));
        textBox.sendKeys("alt");
        textBox.sendKeys(Keys.RETURN);

        result = getDriver().findElement(By.xpath("//*[@id='assignmentCorrect']/h2"));

        String actualResultFour = result.getText();

        String expectResult = "Correct!";

        List<WebElement> exerciseNamesList = getDriver().findElements(
                By.xpath("//*[@id=\"exercisemenu\"]/div[4]/div[1]/div[2]/a/i")
        );

        int actualResultNamesList = exerciseNamesList.size();
        int expectResultNamesList = 4;

        List<WebElement> exerciseTextList = getDriver().findElements(
                By.xpath("//*[@id=\"exercisemenu\"]/div[4]/div[1]/div[2]/a/text")
        );

        for (WebElement webElement : exerciseTextList) {
            Assert.assertTrue(webElement.getText().contains("Exercise"));
        }

        Assert.assertEquals(actualResultOne, expectResult);
        Assert.assertEquals(actualResultTwo, expectResult);
        Assert.assertEquals(actualResultThree, expectResult);
        Assert.assertEquals(actualResultFour, expectResult);
        Assert.assertEquals(actualResultNamesList, expectResultNamesList);

        Reporter.log("Tests stop",true);
    }
    @Test
    public void testFirstAddRemoveElementsAnnaG() {

        getDriver().get("http://the-internet.herokuapp.com/");
        String title = getDriver().getTitle();
        assertEquals("The Internet", title);

        WebElement findAddRemoveElementsButtom = getDriver().findElement
                (By.xpath("//a[@href='/add_remove_elements/']"));
        findAddRemoveElementsButtom.click();
        assertEquals("The Internet", title);

        WebElement addElementButton = getDriver().findElement
                (By.xpath("//div[@class='example']/button[@onclick='addElement()']"));
        addElementButton.click();
        addElementButton.click();
        addElementButton.click();

        WebElement deleteElementButton = getDriver().findElement
                (By.xpath("//button[@class='added-manually']"));
        deleteElementButton.click();

        List<WebElement> sizeOfDeleteElementButton = getDriver().findElements(
                By.className("added-manually"));
        int actualResultSizeOfDeleteElementButton = sizeOfDeleteElementButton.size();
        int expectResultSizeOfDeleteElementButton = 2;

        Assert.assertEquals(actualResultSizeOfDeleteElementButton,expectResultSizeOfDeleteElementButton);

    }

    @Test
    public void testSimpleDemoNickZanin() {

            getDriver().get("https://demoqa.com/");

            WebElement textElements = getDriver().findElement(By.xpath("//h5[normalize-space()='Elements']"));
            textElements.click();

            WebElement textCheckBox = getDriver().findElement(By.xpath("//span[normalize-space()='Check Box']"));
            textCheckBox.click();

            WebElement buttonExpandAll = getDriver().findElement(By.xpath("//button[@title='Expand all']"));
            buttonExpandAll.click();

            Assert.assertEquals("Notes", "Notes");

            WebElement buttonCollapseAll = getDriver().findElement(By.xpath("//button[@title='Collapse all']"));
            buttonCollapseAll.click();
    }
}
