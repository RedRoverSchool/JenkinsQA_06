package school.redrover.runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.time.Duration;

public abstract class BaseTest {

    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;

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

    public WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }
        return wait5;
    }

    public WebDriverWait getWait2() {
        if (wait2 == null) {
            wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        }
        return wait2;
    }

    public WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait10;
    }
}
