package school.redrover;

import school.redrover.runner.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchTest extends BaseTest {
    @Test
    public void testSearchBoxInsensitive() {
        getDriver().findElement(By.xpath("//div[@class=\'login page-header__hyperlinks\']//a[@class=\'model-link\']")).click();

        getDriver().findElement(By.xpath("//a[@href='/user/admin/configure']")).click();
        WebElement checkBoxInsensitiveSearch = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='insensitiveSearch']")));

        Assert.assertEquals(checkBoxInsensitiveSearch.getAttribute("checked"), "true");

        WebElement searchBox = getDriver().findElement(By.id("search-box"));
        searchBox.sendKeys("built");
        WebElement searchItem = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='yui-ac-bd']/ul/li[1]")));

        Assert.assertEquals(searchItem.getText(), "Built-In Node");

        searchBox.clear();
        searchBox.sendKeys("Built");
        WebElement searchItem2 = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='yui-ac-bd']/ul/li[1]")));

        Assert.assertEquals(searchItem2.getText(), "Built-In Node");
    }
}
