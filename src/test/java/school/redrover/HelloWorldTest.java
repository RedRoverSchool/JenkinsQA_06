package school.redrover;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class HelloWorldTest {

    @Test
    public void getUrlKovalenko (){
        for (int i=0;i<3;i++){
            WebDriver driver = new ChromeDriver();
            driver.get("http:/google.com");
            driver.quit();
        }
    }

}
