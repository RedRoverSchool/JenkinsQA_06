package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.ManageJenkinsPage;
import school.redrover.runner.BaseTest;

public class Footer2Test extends BaseTest {

    @Test
    public void testRestApiLink() {
      String restApiTitle = new MainPage(getDriver()).clickOnRestApiLink().getRestApiPageTitle();

        Assert.assertEquals(restApiTitle,"REST API");
    }

    @Test
    public void testJenkinsSiteOpenOnManageJenkinsPage()  {
       new MainPage(getDriver()).clickOnManageJenkinsMenu();

      String jenkinsSiteTitle = new ManageJenkinsPage(getDriver())
               .clickOnJenkinsVersionInTheFooter()
               .getJenkinsSiteTitle();

        Assert.assertEquals(jenkinsSiteTitle, "Jenkins");
    }
}
