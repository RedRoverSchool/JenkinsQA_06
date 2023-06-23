package school.redrover.runner;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.net.URI;


public class CucumberDriver {

    private static URI uri;
    private static WebDriver driver;

    @Before
    public static void before(Scenario scenario) {
        if (!scenario.getUri().equals(uri)) {
            JenkinsUtils.clearData();
            uri = scenario.getUri();
        }

        driver = ProjectUtils.createDriver();

        ProjectUtils.get(driver);
        JenkinsUtils.login(driver);
    }

    @After
    public static void after(Scenario scenario) {
        if(scenario.isFailed() && ProjectUtils.isServerRun()) {
            ProjectUtils.takeScreenshot(driver, scenario.getName(), "CucumberTest");
        }

        JenkinsUtils.logout(driver);
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
