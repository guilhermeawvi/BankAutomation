import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestPlan {
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(testName = "Request a new bank account for two holders")
    public void testCase1() throws InterruptedException {

        driver.get(Utils.BASE_URL);
        //Create object of webPages Class
        WebPages webPages= new WebPages(driver);
        //Accept cookies
        webPages.acceptCookies();
        //Check if page is opened
        //Assert.assertTrue("Bank page not loaded",webPages.isPageOpened());
        //Hit new client button
        webPages.newClient();
        //Hit Start button
        webPages.startProcess();
        //Check if Holders selection page is loaded
        Assert.assertTrue("Select account holders page not loaded",webPages.isAccountHoldersPageOpened());
        //Select Two Holders
        webPages.selectHolders();
        //input Holders ID
        webPages.inputId();
    }
    @AfterMethod
    public void cleanUp(ITestResult result){
        WebPages webPages= new WebPages(driver);
        if(result.FAILURE==result.getStatus()){
            webPages.takeScreenShot(result.getName());
        }
        driver.manage().deleteAllCookies();
        driver.close();
    }
}