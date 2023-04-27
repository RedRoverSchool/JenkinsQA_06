package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class PeoplePageTest extends BaseTest {

    public static List<String> ListText(List<WebElement> elementList) {
        List<String> stringList = new ArrayList<>();
        for (WebElement element : elementList) {
            stringList.add(element.getText());
        }
        return stringList;
    }

    @Test
    public void testViewPeoplePage() {
        getDriver().findElement(By.xpath("//span/a[@href='/asynchPeople/']")).click();
        WebElement nameOfPeoplePageHeader = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(nameOfPeoplePageHeader.getText(), "People");
    }

    @Test
    public void testViewIconButtonsPeoplePage() {
        List expectedIconButtonsNames = List.of("S" + "\n" + "mall", "M" + "\n" + "edium", "L" + "\n" + "arge");

        getDriver().findElement(By.xpath("//span/a[@href='/asynchPeople/']")).click();
        List<WebElement> iconButtons = getDriver().findElements(By.xpath("//div[@class='jenkins-icon-size']//ol/li"));
        List<String> actualIconButtonsNames = ListText(iconButtons);

        Assert.assertEquals(actualIconButtonsNames, expectedIconButtonsNames);
    }

    @Test
    public void testSortArrowModeChangesAfterClickingSortHeaderButton() {
        getDriver().findElement(By.xpath("//span/a[@href='/asynchPeople/']")).click();

        WebElement userIdBtnNoSortArrowBeforeClick = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span"));
        Assert.assertTrue(userIdBtnNoSortArrowBeforeClick.getText().isEmpty());

        getDriver().findElement(By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]")).click();

        String userIdBtnSortArrowUpAfterFirstClick = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span")).getText();
        Assert.assertEquals(userIdBtnSortArrowUpAfterFirstClick, "  ↑");

        getDriver().findElement(By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]")).click();

        String userIdBtnSortArrowDownAfterSecondClick = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span")).getText();
        Assert.assertEquals(userIdBtnSortArrowDownAfterSecondClick, "  ↓");

        getDriver().findElement(By.xpath("//a[@class='sortheader'][contains(text(), 'Name')]")).click();

        WebElement userIdBtnNoArrowAfterAnotherButtonClick = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span"));
        Assert.assertTrue(userIdBtnNoArrowAfterAnotherButtonClick.getText().isEmpty());
    }
}
