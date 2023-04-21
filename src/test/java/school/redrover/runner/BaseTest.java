package school.redrover.runner;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public abstract class BaseTest {

    private WebDriver driver;

    @BeforeMethod
    protected void beforeMethod(Method method) {
        BaseUtils.logf("Run %s.%s", this.getClass().getName(), method.getName());
        try {
            clearData();
            startDriver();
            getWeb();
            loginWeb();
        } catch (Exception e) {
            closeDriver();
            throw new RuntimeException(e);
        }
    }

    protected void clearData() {
        BaseUtils.log("Clear data");
        JenkinsUtils.clearData();
    }

    protected void loginWeb() {
        BaseUtils.log("Login");
        ProjectUtils.login(driver);
    }

    protected void getWeb() {
        BaseUtils.log("Get web page");
        ProjectUtils.get(driver);
    }

    protected void startDriver() {
        BaseUtils.log("Browser open");
        driver = BaseUtils.createDriver();
    }

    protected void stopDriver() {
        try {
            ProjectUtils.logout(driver);
        } catch (Exception ignore) {
        }

        closeDriver();
    }

    protected void closeDriver() {
        if (driver != null) {
            driver.quit();
            BaseUtils.log("Browser closed");
        }
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        stopDriver();
        BaseUtils.logf("Execution time is %o sec\n\n", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000);
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
