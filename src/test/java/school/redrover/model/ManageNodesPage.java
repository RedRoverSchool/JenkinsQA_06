package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

import java.util.List;

public class ManageNodesPage extends BaseMainHeaderPage<ManageNodesPage> {

    public ManageNodesPage(WebDriver driver) {
        super(driver);
    }

    public NewNodePage clickNewNodeButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='new']"))).click();
        return new NewNodePage(getDriver());
    }

    public String getNodeName(String nodeName) {
        return getWait2().until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//tr[@id='node_" + nodeName + "']/td/a")))
                .getText();
    }

    public boolean isNodePresent(String nodeName) {
        List<WebElement> nodeOptions = getDriver().findElements(By.xpath("//a[@class='jenkins-table__link model-link inside']"));
        for (WebElement ele : nodeOptions) {
            if (ele.getText().equals(nodeName)) {
                return true;
            }
        }
        return false;
    }
}
