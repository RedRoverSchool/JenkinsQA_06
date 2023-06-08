package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseModel;

public class GitHubPage extends BaseModel {

    public GitHubPage(WebDriver driver) {
        super(driver);
    }

    public String githubSignInText() {
         return getDriver().findElement(By.xpath("//a[normalize-space(text())= 'Sign in']")).getText();
    }
}
