import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tests {

    public WebDriver webDriver;


    @Before
    public void init() {
        WebDriverManager.chromedriver()
                .setup();
        webDriver = new ChromeDriver();
    }


    @BeforeEach

    public void beforeEach() {

        /*
        Have a question:
        If I write webDriver.navigate().to("https://skillfactory.ru/") or webDriver.get("https://skillfactory.ru/")
        under @BeforeEach - it opens blank page in Chrome.
        If I write same commands under @Test - it opens skillfactory web page
        Could you please explain why?
         */

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }



    /*
    1
    Test contact form with valid data. Should submit form with no errors
    Positive scenario

    NOTE: Test may fail due to reCAPTCHA is triggered after form submission. Selenium can not verify assert.Equals method.
    Please check browser or try manually.
     */

    @Test
    public void contactFormPositiveTest() {
        SkillfactoryContactForm skillfactoryContactForm = new SkillfactoryContactForm(webDriver);
        skillfactoryContactForm.openWebsite();
        System.out.println("Test: ContactFormPositiveTest");
        System.out.println("Test may fail due to reCAPTCHA is triggered after form submission. Selenium can not verify assert.Equals method.");
        System.out.println("Please check browser or try manually.");
        skillfactoryContactForm.contactFormPositiveScenario();
        Assert.assertEquals(skillfactoryContactForm.contactFormSuccessMessageExpected, webDriver.findElement(By.xpath(skillfactoryContactForm.locatorForContactFormSuccessMessageActualMessage)).getText());


    }

        /*
    2
    Contact form, negative test. Checks, that incorrect email returns error
     */

    @Ignore
    @Test
    public void contactFormWrongEmail() {
        SkillfactoryContactForm skillfactoryContactForm = new SkillfactoryContactForm(webDriver);
        skillfactoryContactForm.openWebsite();
        skillfactoryContactForm.contactFormName("Gleb");
        skillfactoryContactForm.contactFormEmail("wrongmail.com");
        skillfactoryContactForm.contactFormPhoneNumber("4991234123");
        skillfactoryContactForm.contactFormClickSubmitButton();
        if (webDriver.findElement(skillfactoryContactForm.contactFormWrongEmailMessage).isDisplayed()) {
            System.out.println("Error message shown:");
        }
        System.out.println(webDriver.findElement(skillfactoryContactForm.contactFormWrongEmailMessage).getText());

    }

    /*
    3
    Contact form, negative test. Checks, that incorrect phone returns an error. Phone number too short
     */
    @Ignore
    @Test
    public void contactFormWrongPhone() {
        SkillfactoryContactForm skillfactoryContactForm = new SkillfactoryContactForm(webDriver);
        skillfactoryContactForm.openWebsite();
        skillfactoryContactForm.contactFormName("Gleb");
        skillfactoryContactForm.contactFormEmail("test@email.com");
        skillfactoryContactForm.contactFormPhoneNumber("499123412");
        skillfactoryContactForm.contactFormClickSubmitButton();
        if (webDriver.findElement(skillfactoryContactForm.contactFormWrongPhoneNumber).isDisplayed()) {
            System.out.println("Error message shown:");
        }
        System.out.println(webDriver.findElement(skillfactoryContactForm.contactFormWrongPhoneNumber).getText());
    }

    /*
    4
    Contact form positive test.
    Choose another country - Latvia for phone number
     */

    @Ignore
    @Test
    public void contactFormPhoneNumberForLatvia() {
        SkillfactoryContactForm skillfactoryContactForm = new SkillfactoryContactForm(webDriver);
        skillfactoryContactForm.openWebsite();
        skillfactoryContactForm.contactFormName("Gleb");
        skillfactoryContactForm.contactFormEmail("test@email.com");
        skillfactoryContactForm.contactFormPhoneNumberLatvia();
        Assert.assertEquals("+371", webDriver.findElement(skillfactoryContactForm.contactFormGetLatviaCode).getText());
    }

    /*
    5
    Сontact form, negative test.
    Do not agree for Data processing. Should return error message
    */

    @Ignore
    @Test
    public void contactFormNoDataProcessing() {
        SkillfactoryContactForm skillfactoryContactForm = new SkillfactoryContactForm(webDriver);
        skillfactoryContactForm.openWebsite();
        skillfactoryContactForm.contactFormName("Gleb");
        skillfactoryContactForm.contactFormEmail("test@email.com");
        skillfactoryContactForm.contactFormPhoneNumber("499123412");
        skillfactoryContactForm.contactFormClickDataProcessingCheckbox();
        skillfactoryContactForm.contactFormClickSubmitButton();
        Assert.assertEquals(skillfactoryContactForm.contactFormNoDataProcessingErrorMessage, webDriver.findElement(skillfactoryContactForm.contactFormCheckBoxForDataProcessingErrorMessage).getText());
    }

    /*

    Check, that website shows all courses after click on "All courses" button.
    Can not do this test, as there is no unique attribute for link with course name. And script fetches all links

    For example:
    Current element:
    <a href="https://skillfactory.ru/python-fullstack-web-developer" target="_blank" style="color: inherit" rel="noopener">Fullstack-разработчик на&nbsp;Python</a>
    Element with unique attribute:
    <a href="https://skillfactory.ru/python-fullstack-web-developer" target="_blank" style="color: inherit" rel="noopener" coursename="1">Fullstack-разработчик на&nbsp;Python</a>
     */
    @Ignore
    @Test
    public void mainPageAllCourses() {
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        skillfactoryMainPage.openWebsite();
        skillfactoryMainPage.clickAllCoursesButton();
        String url = "";
        List<WebElement> allURLs = webDriver.findElements(By.xpath("//div[@class='t396__artboard rendered' and @data-artboard-recid='567832210']//a"));
        System.out.println("Total links on the Wb Page: " + allURLs.size());
        Iterator<WebElement> iterator = allURLs.iterator();
        while (iterator.hasNext()) {
            url = iterator.next().getText();
            System.out.println(url);
        }


    }
    /*
    6
    Check, that browser opens new tab with www.habr.ru, when click on Habr button in footer
    */

    @Test
    public void testLinkHabr() {
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        skillfactoryMainPage.openWebsite();
        skillfactoryMainPage.clickOnHabrLink();
        Assert.assertEquals(skillfactoryMainPage.habrLinkUrl, webDriver.getCurrentUrl());
        System.out.println("Correct link is" + skillfactoryMainPage.linkToHabr);
    }

    /*
    7
    Check, that browser opens new tab with skillfactory youtube channel, when click on Youtube button in footer
     */

    @Test
    public void testLinkYoutube() {
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        skillfactoryMainPage.clickOnYoutubeLink();

        // Assert. We use if as youtube throws 2 different links

        if (webDriver.getCurrentUrl().equals(skillfactoryMainPage.youtubeLinkUrl)) {
            Assert.assertEquals(skillfactoryMainPage.youtubeLinkUrl, webDriver.getCurrentUrl());

        } else {
            Assert.assertEquals(skillfactoryMainPage.youtubeLinkUrlLong, webDriver.getCurrentUrl());

        }

    }

    /*
    8
    Check, that browser opens new tab with skillfactory Telegram channel, when click on Telegram button in footer
    Flacky test, for unknown reason sometimes Telegram button in unclickable
     */

    @Test
    public void testLinkTelegram() {
        System.out.println("Flacky test, for unknown reason sometimes Telegram button in unclickable.");
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        skillfactoryMainPage.clickOnTelegramLink();
        //skillfactoryMainPage.clickOnTelegramLink();
        System.out.println(webDriver.getTitle() + " - title");
        System.out.println(webDriver.getCurrentUrl() + " - url");
        Assert.assertEquals(skillfactoryMainPage.telegramLinkUrl, webDriver.getCurrentUrl());

    }

    /*
    9
    Positive test. Sign up for email in footer. Provide valid email address
    Test may fail due to Captcha check
     */

    @Test
    public void signUpForEmailPositive() {
        System.out.println("Test may fail due to Captcha check");
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        skillfactoryMainPage.openWebsite();
        skillfactoryMainPage.signUpForEmailFooterPositive();
        Assert.assertTrue(skillfactoryMainPage.signUpForEmailPositiveFooter_successMessage.equals(webDriver.findElement(skillfactoryMainPage.signUpForEmailPositiveFooter_successMessageFromWebsite).getText()));
    }

   /*
    10
    Negative test. Sign up for email in footer. Provide invalid email address
    Error message should appear
     */

    @Test
    public void signUpForEmailNegative() {
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        skillfactoryMainPage.openWebsite();
        skillfactoryMainPage.signUpForEmailFooterNegative();
        Assert.assertTrue(skillfactoryMainPage.signUpForEmailFooterNegative_errorMessage.equals(webDriver.findElement(skillfactoryMainPage.signUpForEmailFooterNegative_errorMessage_locator).getText()));
    }

    /*
    11
    Positive Test. Sign up for QA with Java course

    */

    @Test
    public void signUpForQAcourse(){

    }


}
