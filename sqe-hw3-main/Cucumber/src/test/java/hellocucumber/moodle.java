
package hellocucumber;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class moodle {
    private WebDriver driver;
    private WebDriverWait wait;


    public void initSession(String webDriver, String path) {
        System.setProperty(webDriver, path);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get("http://localhost/moodle");
        driver.get("http://localhost/moodle");
        System.out.println("Driver setup finished for - " + driver.getTitle());

    }

    public void goToLogin() {
        driver.findElement(By.linkText("Log in")).click();
    }

    public void enterLoginInfo(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']"))).sendKeys(username);
        driver.findElement(By.xpath("//*[@name='password' and @type='password']")).sendKeys(password);
        driver.findElement(By.id("loginbtn")).click();
    }

    public void WelcomeMessage() {
        driver.findElement(By.xpath("//*[contains(text(),'Welcome back,')]"));
    }

    public void loginSequence(String username, String password) {
        goToLogin();
        enterLoginInfo(username, password);
        WelcomeMessage();
    }

    public void logout() {
        driver.findElement(By.id("user-menu-toggle")).click();
        driver.findElement(By.linkText("Log out")).click();
    }

    public void editModeOn() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='checkbox' and @name='setmode']"))).click();
    }

    public void myCoursesTab() {
        WebElement myCoursesTab = driver.findElement(By.xpath("//*[contains(text(),'My courses') and @role='menuitem']"));
        myCoursesTab.click();
    }

    public void goToCourse(String courseName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='multiline' and contains(text(),'" + courseName + "')]"))).click();
    }

    public void terminateDriver() {
        driver.quit();
    }

    public boolean goToRecords() {
        try {
            WebElement elem = driver.findElement(By.linkText("Records"));
            elem.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void ChangeMyPermissions() {
        driver.findElement(By.xpath("//*[@id='user-menu-toggle']")).click();
        driver.findElement(By.xpath("//*[@href='http://localhost/moodle/course/switchrole.php?id=2&switchrole=-1&returnurl=%2Fcourse%2Fview.php%3Fid%3D2']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'Teacher')])"))).click();
        driver.findElement(By.xpath("//*[@name='setmode']")).click();
    }

    public void ChangeMyPermissionsToStudent() {
        driver.findElement(By.xpath("//*[@id='user-menu-toggle']")).click();
        driver.findElement(By.linkText("Switch role to...")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'Student')])"))).click();
    }

    public void removeRecord() {
        // TODO: 1/15/2023 ----> remove record
    }

    public void addRecord() {
        // TODO: 1/15/2023 ----> add record
    }

    public boolean watchRecord() {
        // TODO: 1/15/2023 ----> watch record
        return true;
    }
    public void checkIfRecordExists() {
        // TODO: 1/15/2023 ----> watch record

    }
}

