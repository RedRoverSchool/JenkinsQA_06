package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.BaseUtils;

import java.util.List;

public class CheckUsersBuilds extends BaseTest {
    private static String[] projectsName = new String[]{"DemoJob", "TeleAgain", "AnimalsShop", "SophariaForever", "NeverAndToday"};
    private static String name = "User1";
    private static String pass = "pechki-plavocki";
    private static String emailAdr = "katerina@a0a0.ru";
    private static String myRep = "git@github.com:KaterinaNa/JenkinsDemoComparing.git";


    //все сборки должны попадать на главную страницу Dashboard > Build History.
    @Test
    public void checkAllBuild () throws InterruptedException {
        String[] projectsName = new String[]{"DemoJob", "TeleAgain", "AnimalsShop", "SophariaForever", "NeverAndToday"};
        addNewJobsBeforeTesting();

        WebElement allBuildsButton = getDriver().findElement(By.xpath("//a[@href='/view/all/builds']"));
        allBuildsButton.click();

        List<WebElement> buildLinks = getDriver().findElements(By.xpath("//a[@class='jenkins-table__link model-link' and contains(@href,'job')]"));
        for (int i = 0; i < buildLinks.size(); i++){
            String nameProjectBiuilt = buildLinks.get(i).getText();
            String expectedProjectName = projectsName[projectsName.length - i -1];

            Assert.assertEquals(nameProjectBiuilt, expectedProjectName);
        }
    }

    //показывать список сборок, время с момента сборки и результат статуса;
    @Test
    public void checkAllBuildInfo () throws InterruptedException {
        String[] projectsName = new String[]{"DemoJob", "TeleAgain", "AnimalsShop", "SophariaForever", "NeverAndToday"};
        int countOfBuild  = 0;
        addNewJobsBeforeTesting();

        WebElement allBuildsButton = getDriver().findElement(By.xpath("//a[@href='/view/all/builds']"));
        allBuildsButton.click();

        List<WebElement> buildLinks = getDriver().findElements(By.xpath("//a[@class='jenkins-table__link model-link' and contains(@href,'job')]"));
        List<WebElement> buildTimes  = getDriver().findElements((By.xpath("//table[@class='jenkins-table  sortable']//td[contains(@data, 'Z')]")));
        List<WebElement> buildStatus  = getDriver().findElements((By.xpath("//table[@class='jenkins-table  sortable']//td[text()]")));

        if (buildTimes.size() == buildLinks.size() && buildStatus.size() == buildLinks.size()) {
            for (int i = 0; i < buildLinks.size(); i++) {
                String nameProjectBuilt = buildLinks.get(i).getText();
                String timeProjectBuilt = buildTimes.get(i).getText();
                String statusProjectBuilt = buildStatus.get(i).getText();
                if (!nameProjectBuilt.isEmpty() && !timeProjectBuilt.isEmpty() && !nameProjectBuilt.isEmpty() && !statusProjectBuilt.isEmpty()) {
                    countOfBuild++;

                }
            }
        }
        Assert.assertEquals(countOfBuild, projectsName.length);
    }

    //открывать сборки от каждого ID пользователя на странице Users;
    @Test
    public void openingBuildsFromEveryUsers () throws InterruptedException {
        String[] projectsName = new String[]{"DemoJob", "TeleAgain", "AnimalsShop", "SophariaForever", "NeverAndToday"};
        Actions action = new Actions(getDriver());
        JavascriptExecutor js = (JavascriptExecutor)getDriver();

        int countUserBuilds = 0;
        int countAdminBuild = 0;

        addNewJobsBeforeTesting();

        WebElement allPeopleButton = getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']"));
        allPeopleButton.click();

        WebElement peopleUser1 = getDriver().findElement(By.xpath("//a[@href='/user/user1/']"));
        peopleUser1.click();

        WebElement peopleUser1Builds = getDriver().findElement(By.xpath("//a[@href='/user/user1/builds']"));
        peopleUser1Builds.click();

        List<WebElement> buildUserLinks = getDriver().findElements(By.xpath("//a[@class='jenkins-table__link model-link' and contains(@href,'job')]"));
        List<WebElement> buildUserTimes  = getDriver().findElements((By.xpath("//table[@class='jenkins-table  sortable']//td[contains(@data, 'Z')]")));
        List<WebElement> buildUserStatus  = getDriver().findElements((By.xpath("//table[@class='jenkins-table  sortable']//td[text()]")));

        if (buildUserTimes.size() == buildUserLinks.size() && buildUserStatus.size() == buildUserLinks.size()) {
            for (int i = 0; i < buildUserLinks.size(); i++) {
                String nameProjectBuilt = buildUserLinks.get(i).getText();
                String timeProjectBuilt = buildUserTimes.get(i).getText();
                String statusProjectBuilt = buildUserStatus.get(i).getText();
                if (!nameProjectBuilt.isEmpty() && !timeProjectBuilt.isEmpty() && !nameProjectBuilt.isEmpty() && !statusProjectBuilt.isEmpty()) {
                    countUserBuilds++;
                }
            }
        }

        WebElement anotherAllPeopleButton = getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']"));
        anotherAllPeopleButton.click();

        WebElement peopleAdmin = getDriver().findElement(By.xpath("//a[@href='/user/admin/']"));
        peopleAdmin.click();

        WebElement peopleAdminBuilds = getDriver().findElement(By.xpath("//a[@href='/user/admin/builds']"));
        peopleAdminBuilds.click();

        List <WebElement> buildAdminLinks = getDriver().findElements(By.xpath("//a[@class='jenkins-table__link model-link' and contains(@href,'job')]"));
        List <WebElement> buildAdminTimes  = getDriver().findElements((By.xpath("//table[@class='jenkins-table  sortable']//td[contains(@data, 'Z')]")));
        List <WebElement> buildAdminStatus  = getDriver().findElements((By.xpath("//table[@class='jenkins-table  sortable']//td[text()]")));

        if (buildAdminTimes.size() == buildAdminLinks.size() && buildAdminStatus.size() == buildAdminLinks.size()) {
            for (int i = 0; i < buildAdminLinks.size(); i++) {
                String nameProjectBuilt = buildAdminLinks.get(i).getText();
                String timeProjectBuilt = buildAdminLinks.get(i).getText();
                String statusProjectBuilt = buildAdminLinks.get(i).getText();
                if (!nameProjectBuilt.isEmpty() && !timeProjectBuilt.isEmpty() && !nameProjectBuilt.isEmpty() && !statusProjectBuilt.isEmpty()) {
                    countAdminBuild++;
                }
            }
        }
        int countOfAllBuilds = countUserBuilds + countAdminBuild;
        Assert.assertEquals(countOfAllBuilds, projectsName.length);
    }

    private   void addNewJobsBeforeTesting () throws InterruptedException {
        String[] projectsName = new String[]{"DemoJob", "TeleAgain", "AnimalsShop", "SophariaForever", "NeverAndToday"};
        for (int i = 0; i < projectsName.length - 2; i++ ) {
            String newProjectName = projectsName[i];
            addNewJob(newProjectName);
        }
        createNewUser();
        BaseUtils.log("createNewUser");
        logoutAdmin();
        BaseUtils.log("logoutAdmin");
        loginUser();
        BaseUtils.log("loginUser");

        for (int i = projectsName.length - 2; i < projectsName.length; i++ ) {
            String newProjectName = projectsName[i];
            addNewJob(newProjectName);
        }
    }

    private void addNewJob (String newProjectName) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        Actions action = new Actions(getDriver());

        WebElement newItem = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        newItem.click();

        WebElement newName = getDriver().findElement(By.xpath("//div//input[@id= 'name']"));
        action.moveToElement(newName, 45, 0).sendKeys(newProjectName).perform();

        WebElement freeStyleProject = getDriver().findElement(By.xpath("//input[contains(@value, '.FreeStyleProject')]//ancestor::li"));
        freeStyleProject.click();

        WebElement okProject = getDriver().findElement(By.xpath("//div[@class='btn-decorator']"));
        System.out.println(okProject.getCssValue("aria-checked"));
        okProject.click();

        WebElement description = getWait10().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@class='jenkins-input   ']")));
        description.sendKeys("FirstProject");

        js.executeScript("window.scrollBy(0,500)", "");

        WebElement checkGit = getDriver().findElement(By.xpath("//label[@for='radio-block-1']"));
        checkGit.click();

        WebElement rUrl = getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='setting-main']/input[@name='_.url']")));
        rUrl.sendKeys(myRep);

        WebElement branchs = getDriver().findElement(By.xpath("//input[@default='*/master']"));
        branchs.clear();
        branchs.sendKeys("*/main");

        WebElement buttonSaveProject = getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']"));
        buttonSaveProject.click();

        WebElement backToMain = getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[@href='/']"));
        backToMain.click();

        js.executeScript("window.scrollBy(0,500)", "");

        WebElement projectsTable = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'dashboard']")));
        WebElement thisJobIs = getDriver().findElement(By.xpath("//*[contains(@title,'"+newProjectName+"')]"));
        thisJobIs.click();

        /*Boolean buildStatus = getWait5()
                .until((ExpectedConditions
                        .attributeContains(getDriver()
                        .findElement(By.xpath("//*[contains(@id,'"+newProjectName+"')]//*[local-name()='svg']")
                        ), "tooltip", "Success")));*/

        Thread.sleep(4000);
        getDriver().navigate().to(getDriver().getCurrentUrl());

        js.executeScript("window.scrollBy(0,-500)", "");
    }

    private void createNewUser() {
        WebElement newItem = getDriver().findElement(By.xpath("//a[@href = '/manage']"));
        newItem.click();
        Actions action = new Actions(getDriver());

        WebElement manageUser = getDriver().findElement(By.xpath("//a[@href='securityRealm/']"));
        manageUser.click();

        WebElement createUserButton = getDriver().findElement(By.xpath("//a[@class='jenkins-button jenkins-button--primary']"));
        createUserButton.click();

        WebElement newUserName = getDriver().findElement(By.xpath("//input[@name='username']"));
        newUserName.sendKeys(name);

        WebElement password = getDriver().findElement(By.xpath("//input[@name='password1']"));
        password.sendKeys(pass);

        WebElement confirmPassword = getDriver().findElement(By.xpath("//input[@name='password2']"));
        confirmPassword.sendKeys(pass);

        WebElement fullName = getDriver().findElement(By.xpath("//input[@name='fullname']"));
        fullName.sendKeys(name + name);

        WebElement email = getDriver().findElement(By.xpath("//input[@name='email']"));
        email.sendKeys(emailAdr);

        WebElement submitButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        submitButton.click();

        WebElement toStartPage = getDriver().findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']/a"));
        toStartPage.click();
    }

    private void logoutAdmin(){
        WebElement logoutButton = getDriver().findElement(By.xpath("//a[@href='/logout']"));
        logoutButton.click();
    }

    private void loginUser(){
        WebElement inputUserName = getDriver().findElement(By.xpath("//input[@name='j_username']"));
        inputUserName.sendKeys(name);

        WebElement inputPassword = getDriver().findElement(By.xpath("//input[@name='j_password']"));
        inputPassword.sendKeys(pass);

        WebElement submitButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        submitButton.click();
    }
}
