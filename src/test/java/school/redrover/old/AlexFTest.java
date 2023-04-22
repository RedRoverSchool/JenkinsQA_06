package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;
@Ignore
public class AlexFTest extends BaseTest {

    public final static String[] ARRAY_EXPECTED_TITLES =
            {"Text input", "Password", "Textarea", "Disabled input", "Readonly input"};

    @Test
    public void testTitlesFirstColumnTextFields() {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");
        List<WebElement> textInputField = getDriver().findElements(By.xpath("(//*[@class = 'col-md-4 py-2'][1]/label)"));
        for (int i = 0; i < textInputField.size(); i++) {
            for (String s : ARRAY_EXPECTED_TITLES) {
                Assert.assertTrue(textInputField.get(i).getText().contains(s));
                i += 1;
            }
        }
    }
}