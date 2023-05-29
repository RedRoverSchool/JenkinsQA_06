package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class ConsoleOutputPage extends BaseMainHeaderPage {

    public ConsoleOutputPage(WebDriver driver) {
        super(driver);
    }

    public String getConsoleOutputText(){
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//pre[@class='console-output']")))
                .getText();
    }
}
