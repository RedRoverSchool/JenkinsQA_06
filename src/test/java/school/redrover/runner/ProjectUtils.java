package school.redrover.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static school.redrover.runner.BaseUtils.PREFIX_PROP;


public final class ProjectUtils {

    private static final String PROP_PORT = PREFIX_PROP + "port";

    private static final String PROP_ADMIN_USERNAME = PREFIX_PROP + "admin.username";

    private static final String PROP_ADMIN_PAS = PREFIX_PROP + "admin.password";

    public static void get(WebDriver driver) {
        driver.get(getUrl());
    }

    static String getUrl() {
        return String.format("http://localhost:%s/", BaseUtils.getProperties().getProperty(PROP_PORT));
    }

    static void login(WebDriver driver) {
        driver.findElement(By.name("j_username")).sendKeys(BaseUtils.getProperties().getProperty(PROP_ADMIN_USERNAME));
        driver.findElement(By.name("j_password")).sendKeys(BaseUtils.getProperties().getProperty(PROP_ADMIN_PAS));
        driver.findElement(By.name("Submit")).click();
    }

    static void logout(WebDriver driver) {
        get(driver);

        driver.findElement(By.xpath("//a[@href='/logout']")).click();
    }

    static String getUserName() {
        return BaseUtils.getProperties().getProperty(PROP_ADMIN_USERNAME);
    }

    static String getPassword() {
        return BaseUtils.getProperties().getProperty(PROP_ADMIN_PAS);
    }
}