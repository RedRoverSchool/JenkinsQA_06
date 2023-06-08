package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class NodePage extends BaseMainHeaderPage<NodePage> {
    public NodePage(WebDriver driver) {
        super(driver);
    }

    public String getNodeDescription() {
        return getWait2().until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//div[@id='description']/div[1]")))
                .getText();
    }
}
