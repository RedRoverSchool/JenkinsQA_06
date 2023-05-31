package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class ConsoleOutputPage extends BaseMainHeaderPage<ConsoleOutputPage> {

    public ConsoleOutputPage(WebDriver driver) {
        super(driver);
    }

    public String getConsoleOutputText(){
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//pre[@class='console-output']")))
                .getText();
    }

    public String getParameterFromConsoleOutput(String consoleText, String containParameterText) {
        String[] split = consoleText.split("\n");
        for (String str : split) {
            if (str.contains(containParameterText)) {
                return str;
            }
        }
        return null;
    }

    public String getStartedByUser() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'model-link--float')]")))
                .getText();
    }
}
