package school.redrover.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/cucumber",
        glue = {"school.redrover.cucumber", "school.redrover.runner"},
        plugin = {"pretty"},
        tags = "not @ignore")
public class CucumberTest extends AbstractTestNGCucumberTests {
}


