import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SkillfactoryMainPage {

    //------------------------------WebDriver and Constructor----------------------------------------

    public WebDriver webDriver;

    public SkillfactoryMainPage() {

    }

    public SkillfactoryMainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //------------------------------Locators and variables---------------------------------------------

    By allCoursesButton = By.xpath("//a[contains(text(),'Все курсы') and @href='https://skillfactory.ru/courses']");

    //------------Footer

    By linkToHabr = By.xpath("//div[@data-elem-id='1676892134965']//a");
    By linkToYoutube = By.xpath("//div[@data-elem-id='1676892134988']//a");
    By youtubeAcceptCoockiesButton = By.xpath("//div[@class='qqtRac']//span[contains(text(),'Accept all')]");

    By linkToTelegram = By.xpath("//div[@data-elem-id='1676892135027']//a[@href='https://t.me/skillfactory']");

    By emailInputFooter = By.xpath("//div[@data-input-lid='1676893875873']//input");

    By emailInputFooterSubmitButton = By.xpath("//form[@id='form598180441']//button[@type='submit']");

    By signUpForEmailPositiveFooter_successMessageFromWebsite = By.xpath("//div[@class='t-form-success-popup__window']//div[@id='tildaformsuccesspopuptext']");

    By signUpForEmailFooterNegative_errorMessage_locator = By.xpath("//form[@id='form598180441']//div[@data-input-lid='1676893875873']//div[@class='t-input-error']");

    //------------------------------Other variables----------------------------------------
    String habrLinkUrl = "https://habr.com/ru/companies/skillfactory/articles/";
    String youtubeLinkUrl = "https://www.youtube.com/channel/UClOTq6XN8g7-16QLGnZ6_EA";
    String youtubeLinkUrlLong = "https://www.youtube.com/channel/UClOTq6XN8g7-16QLGnZ6_EA?cbrd=1";

    String telegramLinkUrl = "https://t.me/skillfactory";

    String signUpForEmailPositiveFooter_successMessage = "Thank you! Your data has been submitted.";
    String signUpForEmailFooterNegative_errorMessage = "Please enter a valid email address";



    String validEmail = "test@test.com";
    String invalidEmail = "wrong_email.com";




    //------------------------------Methods----------------------------------------

    // Common methods

    //Open website
    public void openWebsite() {

        webDriver.get("https://skillfactory.ru/");

    }

    //Open website and maximize window
    public void openWebsiteMaximizeWindow() {

        webDriver.get("https://skillfactory.ru/");
        webDriver.manage().window().maximize();

    }


    // Wait for webElement by Xpath
    public void waitVisibilityOfElementLocated_Xpath(By waitFor) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitFor));
    }


    // Click on All course button and go to /courses page
    public SkillfactoryCourses clickAllCoursesButton() {
        webDriver.findElement(allCoursesButton).click();
        return new SkillfactoryCourses(webDriver);
    }

    // Click on Habr Link in Footer
    public void clickOnHabrLink() {
        String originalWindow = webDriver.getWindowHandle();
        webDriver.findElement(linkToHabr).click();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    // Click on Youtube link in Footer
    public void clickOnYoutubeLink() {
        openWebsite();
        String originalWindow = webDriver.getWindowHandle();
        webDriver.findElement(linkToYoutube).click();

        // Print info about test

        System.out.println("Can be flacky test. If test failed, check in browser what link is shown there");
        System.out.println("Correct links are:");
        System.out.println(youtubeLinkUrl);
        System.out.println(youtubeLinkUrlLong);

        // to switch to new opened tab
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        webDriver.findElement(youtubeAcceptCoockiesButton).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void clickOnTelegramLink() {
        openWebsite();
        String originalWindow = webDriver.getWindowHandle();

        //Wait for element to be clickable
        WebDriverWait wait = new WebDriverWait(webDriver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(linkToTelegram));

        webDriver.findElement(linkToTelegram).click();

        // to switch to new opened tab
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

    }
// signup for newsletter in footer. Positive scenario
    public void signUpForEmailFooterPositive(){
        openWebsite();
        webDriver.findElement(emailInputFooter).sendKeys(validEmail);
        webDriver.findElement(emailInputFooterSubmitButton).click();

    }

    // signup for newsletter in footer with invalid email . Negative scenario
    public void signUpForEmailFooterNegative(){
        openWebsite();
        webDriver.findElement(emailInputFooter).sendKeys(invalidEmail);
        webDriver.findElement(emailInputFooterSubmitButton).click();
        waitVisibilityOfElementLocated_Xpath(signUpForEmailFooterNegative_errorMessage_locator);
        String signUpForEmailFooterNegative_errorMessage = webDriver.findElement(signUpForEmailFooterNegative_errorMessage_locator).getText();
    }



}
