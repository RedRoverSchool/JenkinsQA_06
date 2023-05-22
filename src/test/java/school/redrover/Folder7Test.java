package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    private final String FOLDERNAME = "folder1";
    private final String AFTERRENAME = "folder2";

        public void fillFolderNameField (String name, String wayToCreate) {
            getDriver().findElement(By.id("jenkins-home-link")).click();

            if (wayToCreate.equals("leftside")) {
                getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"))));
                getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
            }
            else if (wayToCreate.equals("dropdown")) {
                getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.linkText("Dashboard"))));

                WebElement dashboard = getDriver().findElement(By.linkText("Dashboard"));
                WebElement pointer = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']//button[@class=\"jenkins-menu-dropdown-chevron\"]"));
                new Actions(getDriver())
                        .moveToElement(dashboard)
                        .perform();
                pointer.sendKeys(Keys.RETURN);

                new Actions (getDriver())
                        .click(getDriver().findElement(By.xpath("//ul[@class='first-of-type']//span[text()='New Item']")))
                        .perform();
            }
            else {
                System.out.println("wayToCreate is incorrect. Please use leftside or dropdown");
            }

            getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(NAME))).sendKeys(name);
        }

        public void createFolder (String name, String wayToCreate){
            fillFolderNameField(name, wayToCreate);

            WebElement folderTypeButton = getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"));
            new Actions(getDriver())
                    .scrollToElement(folderTypeButton)
                    .perform();
            folderTypeButton.click();

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

        @DataProvider(name= "folderNames")
        public Object[][] provideFolderNames(){
            return new Object[][]
                    {{"folder1"}, {" Spaces "}, {"123"}};
        }

        @Test(dataProvider = "folderNames")
        public void testCreateFolderByLeftside(String folderName){
            createFolder(folderName,"leftside" );
            folderName = folderName.trim();
            getWait2().until(ExpectedConditions.textToBe(By.xpath("//h1[text()='Configuration']"), "Configuration"));
            getDriver().findElement(By.xpath("//a[normalize-space()='"+folderName+"']")).click();

            Assert.assertEquals(getDriver().findElement(By.xpath("//a[normalize-space()='"+folderName+"']")).getText(),folderName);
        }

        @Test
        public void testcreateFolderFromDropdown(){
            createFolder(FOLDERNAME, "dropdown");
            Assert.assertEquals(getDriver().findElement(By.linkText(FOLDERNAME)).getText(),FOLDERNAME);
        }

        @Test
        public void testCreateFolderNegative () {
            List<String> negativevalues = Arrays.asList(" ");
            for (String value : negativevalues) {
                createFolder(value,"leftside" );
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
                fillFolderNameField(specvalue,"leftside");
                getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("itemname-invalid"))));
                Assert.assertEquals(getDriver().findElement(By.id("itemname-invalid")).getText(), "» ‘"+specvalue+"’ is an unsafe character");

                WebElement nameinput=getDriver().findElement(NAME);
                nameinput.clear();
        }

        @Test(dependsOnMethods = "testCreateFolderByLeftside")
        public void testRenameFolderPositive (){
            renameFolder(FOLDERNAME, AFTERRENAME);
            Assert.assertEquals(getDriver().findElement(By.xpath("//a[normalize-space()='"+AFTERRENAME+"']")).getText(),AFTERRENAME);
        }

        @Test
        public void testRenameFolderNegative (){
            createFolder(FOLDERNAME, "leftside");
            renameFolder(FOLDERNAME, FOLDERNAME);
            Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Error");
            Assert.assertEquals(getDriver().findElement(By.cssSelector("div[id='main-panel'] p")).getText(), "The new name is the same as the current name.");
        }
}
