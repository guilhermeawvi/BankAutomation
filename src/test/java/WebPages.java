import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class WebPages extends PageObject {
    private final String DOCUMENT_ID = "Y123456D";
    private final String FILE_PATH = System.getProperty("user.dir")+"//Screenshot//";

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @FindBy(xpath ="//div[@class='buttons']/a[1]/span")
    private WebElement newClient;
    @FindBy(xpath = "//div[@class='icondescriptionmodule__bottom ']/a")
    private WebElement startButton;
    //@FindBy(xpath = "//img[@class='c-radiobutton-image-icon__img margin-bottom-xsmall margin-top-xxsmall c-microillustration--small centered-block']")
    @FindBy(xpath = "//p[@class='c-data-tag--info c-data-tag--right']")
    private WebElement elementToShow;
    @FindBy(xpath = "//img[@class='c-radiobutton-image-icon__img margin-bottom-xsmall margin-top-xxsmall c-microillustration--small centered-block']")
    private WebElement twoHolders;
    @FindBy(className = "c-button--secondary")
    private WebElement holdersNextButton;
    @FindBy(xpath = "//button[@class='cookiesgdpr__acceptbtn btn__basic btn__medium-blue']")
    private WebElement cookiesPath;
    @FindBy(id="input-identity-document")
    private WebElement idField;
    @FindBy(xpath = "//span[@class='c-checkbox__text ']")
    private WebElement checkBox;

    public WebPages(WebDriver driver) {
        super(driver);
    }

    //Accept cookies
    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(this.cookiesPath)).click();
    }

    //New client button
    public void newClient() {
        this.newClient.click();
    }
    //Start button
    public void startProcess() {
        wait.until(ExpectedConditions.elementToBeClickable(this.startButton)).click();
    }
    public void selectHolders() {
        wait.until(ExpectedConditions.elementToBeClickable(this.twoHolders)).click();
        this.holdersNextButton.click();
    }
    public void inputId() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(this.idField)).sendKeys(DOCUMENT_ID);
        this.checkBox.click();
        scrollView(this.checkBox);
        Thread.sleep(500);
    }

    //Read all hyperlinks and search for "wikipedia"
    /*public void readSearchResults() {
        List<WebElement> searchResults = this.searchResultContainer.findElements(this.searchResultHeader);
        int size = searchResults.size();
        for (int i = 0; i < size; i++) {
            if (searchResults.get(i).getAttribute("href").contains("wikipedia")) {
                searchResults.get(i).click();
                break;
            }
        }
    }*/

    //check if account Holder page is loaded
    public boolean isAccountHoldersPageOpened(){
        //Assertion
        return this.elementToShow.isDisplayed();
    }

    //Scroll page to view year information
    public void scrollView(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

    }

    //Take screenshot
    public void takeScreenShot(String fileName)   {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(FILE_PATH+fileName+".png");
        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot path: "+destFile);
        } catch (Exception e) {
            e.getMessage();
        }
    }


}