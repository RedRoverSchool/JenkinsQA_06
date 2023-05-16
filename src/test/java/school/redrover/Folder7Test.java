package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.util.Arrays;
import java.util.List;

public class Folder7Test extends BaseTest {
    private static final By NAME=By.name("name");
    private final String BEFORERENAME = "folder1";
    private final String AFTERRENAME = "folder2";

        public void fillFolderNameField (String name) {
            getDriver().findElement(By.id("jenkins-home-link")).click();
            getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"))));
            getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

            WebElement nameinput=getDriver().findElement(NAME);
            getWait5().until(ExpectedConditions.visibilityOf(nameinput));
            nameinput.sendKeys(name);
        }

        public void createBaseFolder (String name) {
            fillFolderNameField(name);

            WebElement folderButton = getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"));
            new Actions(getDriver())
                    .scrollToElement(folderButton)
                    .perform();
            folderButton.click();

            getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("ok-button")))).click();
        }

        public void renameFolder (String beforename, String aftername) {
            getDriver().findElement(By.id("jenkins-home-link")).click();
            getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//a[normalize-space()='"+beforename+"']")))).click();

            WebElement renamebutton = getDriver().findElement(By.xpath("//a[normalize-space()='Rename']"));
            getWait2().until(ExpectedConditions.visibilityOf(renamebutton)).click();

            WebElement inputrename = getDriver().findElement(By.name("newName"));
            getWait2().until(ExpectedConditions.visibilityOf(inputrename)).clear();
            inputrename.sendKeys(aftername);

            getDriver().findElement(By.name("Submit")).click();
        }
        @DataProvider(name="folderNames")
        public Object[][] provideFolderNames(){
            return new Object[][]
                    {{"folder1"}, {" Spaces "}, {"123"}};
        }

        @Test(dataProvider = "folderNames")
        public void testCreateFolderPositive (String folderName){
                createBaseFolder(folderName);
                folderName = folderName.trim();
                getWait2().until(ExpectedConditions.textToBe(By.xpath("//h1[text()='Configuration']"), "Configuration"));
                getDriver().findElement(By.xpath("//a[normalize-space()='"+folderName+"']")).click();

                Assert.assertEquals(getDriver().findElement(By.xpath("//a[normalize-space()='"+folderName+"']")).getText(),folderName);
        }

        @Test
        public void testCreateFolderNegative () {
            List<String> negativevalues = Arrays.asList(" ");
            for (String value : negativevalues) {
                createBaseFolder(value);
                Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Error");
            }
        }

        @DataProvider(name="special-symbols")
        public Object[][] provideSpecialSymbols(){
            return new Object[][]
                    {{"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}};
        }

        @Test(dataProvider ="special-symbols")
        public void testCreateFolderSpecSymbols (String specvalue) {
                fillFolderNameField(specvalue);
                getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("itemname-invalid"))));
                Assert.assertEquals(getDriver().findElement(By.id("itemname-invalid")).getText(), "» ‘"+specvalue+"’ is an unsafe character");

                WebElement nameinput=getDriver().findElement(NAME);
                nameinput.clear();
        }

        @Test(dependsOnMethods = "testCreateFolderPositive")
        public void testRenameFolderPositive (){
            renameFolder(BEFORERENAME, AFTERRENAME);
            Assert.assertEquals(getDriver().findElement(By.xpath("//a[normalize-space()='"+AFTERRENAME+"']")).getText(),AFTERRENAME);
        }

        @Test
        public void testRenameFolderNegative (){
            createBaseFolder(BEFORERENAME);
            renameFolder(BEFORERENAME, BEFORERENAME);
            Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Error");
            Assert.assertEquals(getDriver().findElement(By.cssSelector("div[id='main-panel'] p")).getText(), "The new name is the same as the current name.");
        }
}
