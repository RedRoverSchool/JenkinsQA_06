package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.Arrays;
import java.util.List;
public class ZeroBugTest extends BaseTest {
    @Test (enabled = false)
    public void testSearchSelenium() {
        getDriver().get("https://www.google.com/");
        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("selenium");
        textBox.sendKeys(Keys.RETURN);
        WebElement text = getDriver().findElement(By.xpath("//h3[text() = 'Selenium']"));
        Assert.assertEquals(text.getText(), "Selenium");
    }
    @Test (enabled = false)
    public void testGetWebFormTitle() {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");
        String title = getDriver().getTitle();
        Assert.assertEquals("Web form", title);
        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));
        textBox.sendKeys("Selenium");
        submitButton.click();
        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
    }
    @Test
    public void testGetTitle_1() {
        getDriver().get("https://askomdch.com/");
        String expectedHPResult = "AskOmDch";
        String actualHPResultHP = getDriver().findElement(By.xpath("//h1[@class='site-title']/.")).getText();
        Assert.assertEquals(actualHPResultHP,expectedHPResult,"Wrong text from header!");

        String expectedTextBtnMen = "Men";
        String actualTextBtnMen = "";
        List<WebElement> list = getDriver().findElements(By.xpath("//li[@id='menu-item-1226']/..//li"));
        for (WebElement element: list) {
            if (element.getText().equals(expectedTextBtnMen)) {
                actualTextBtnMen = element.getText();
                break;
            }
        }
        Assert.assertEquals(actualTextBtnMen, expectedTextBtnMen, "Element " + expectedTextBtnMen + " is not found" );
        WebElement men = getDriver().findElement(By.xpath("//*[@id='menu-item-1228']"));
        men.click();
        String textFromMenHeader = "Men";
        String actualTextFromMenPage = getDriver().findElement(By.xpath("//h1[.='Men']")).getText();
        Assert.assertEquals(actualTextFromMenPage,textFromMenHeader,"Wrong text from header");
    }

    @Test
    public void testGetTitle_2() throws InterruptedException {
        getDriver().get("https://askomdch.com/");
        String expectedHeaderHP = "Featured Products";
        String actualHeaderHP = getDriver().findElement(By.cssSelector(".wp-block-group__inner-container>h2")).getText();
        Assert.assertEquals(actualHeaderHP,expectedHeaderHP,"Header from Home Page do not match");

        String expectedMen = "Men";
        String actualHeaderMen = "";
        List <WebElement> listTabsHP = getDriver().findElements(By.xpath("//*[@class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']//li"));
        for (WebElement tab:listTabsHP){
            if(tab.getText().equals(expectedMen)){
                tab.click();
                actualHeaderMen = getDriver().findElement(By.cssSelector(".woocommerce-products-header>h1")).getText();
                break;
            }
        }
        Assert.assertEquals(actualHeaderMen,expectedMen,"Text from header of Men Page do not match");
    }
    @Test (enabled = false)
    public void testSearchProduct() {
        getDriver().get("https://askomdch.com/");
        String expectedResult = "jeans";
        getDriver().findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        getDriver().findElement(By.id("woocommerce-product-search-field-0")).sendKeys(expectedResult);
        getDriver().findElement(By.xpath("//button[@value='Search']")).click();
        String actualResult = getDriver().findElement(By.xpath("//h1[contains(.,'jeans')]")).getText();
        Assert.assertTrue(actualResult.contains(expectedResult),"Search product is not working");
    }

    @Test
    public void task_31 (){

        int [] a = new int[4];
        int [] b = new int[3];

        a[0]=1;
        a[1]=2;
        a[2]=3;
        a[3]=4;

        b[0]=1;
        b[1]=2;
        b[2]=3;

        if(a[0]==b[0]){
            System.out.println("First element of an arrays are equals");
        } else if (a.length-1==b.length-1){
            System.out.println("Last element of an arrays are equals");
        } else {
            System.out.println("First or last element of an arrays is not equals!");


        }
    }
}
