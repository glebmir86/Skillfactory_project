import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
    6
    Check, that website shows all courses after click on "All courses" button.
     */

    @Test
    public void mainPageAllCourses() {
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        skillfactoryMainPage.openWebsite();
        skillfactoryMainPage.clickAllCoursesButton();
    }
}
