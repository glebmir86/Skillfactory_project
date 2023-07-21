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
        closeBrowser();

    }

        /*
    2
    Contact form, negative test. Checks, that incorrect email returns error
     */


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
        closeBrowser();
    }

    /*
    3
    Contact form, negative test. Checks, that incorrect phone returns an error. Phone number too short
     */

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
        closeBrowser();
    }

    /*
    4
    Contact form positive test.
    Choose another country - Latvia for phone number
     */


    @Test
    public void contactFormPhoneNumberForLatvia() {
        SkillfactoryContactForm skillfactoryContactForm = new SkillfactoryContactForm(webDriver);
        skillfactoryContactForm.openWebsite();
        skillfactoryContactForm.contactFormName("Gleb");
        skillfactoryContactForm.contactFormEmail("test@email.com");
        skillfactoryContactForm.contactFormPhoneNumberLatvia();
        Assert.assertEquals("+371", webDriver.findElement(skillfactoryContactForm.contactFormGetLatviaCode).getText());
        closeBrowser();
    }

    /*
    5
    Сontact form, negative test.
    Do not agree for Data processing. Should return error message
    */


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
        System.out.println("Flacky test. In case of failure try to rerun");
        closeBrowser();
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
        closeBrowser();


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
        closeBrowser();
    }

    /*
    7
    Check, that browser opens new tab with skillfactory youtube channel, when click on Youtube button in footer
     */

    @Test
    public void testLinkYoutube() {
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        skillfactoryMainPage.clickOnYoutubeLink();

        // Assert. We use if as youtube throws different links

        if (webDriver.getCurrentUrl().equals(skillfactoryMainPage.youtubeLinkUrl)) {
            Assert.assertEquals(skillfactoryMainPage.youtubeLinkUrl, webDriver.getCurrentUrl());

        } else {
            Assert.assertEquals(skillfactoryMainPage.youtubeLinkUrlLong, webDriver.getCurrentUrl());

        }
        closeBrowser();
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
        closeBrowser();
    }

    /*
    9
    Positive test. Sign up for Skillfactory newsletter in footer. Provide valid email address
    Test may fail due to Captcha check
     */

    @Test
    public void signUpForEmailPositive() {
        System.out.println("Test may fail due to Captcha check. In case of failure try to rerun, or check manually");
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        skillfactoryMainPage.openWebsite();
        skillfactoryMainPage.signUpForEmailFooterPositive();
        Assert.assertTrue(skillfactoryMainPage.signUpForEmailPositiveFooter_successMessage.equals(webDriver.findElement(skillfactoryMainPage.signUpForEmailPositiveFooter_successMessageFromWebsite).getText()));
        closeBrowser();
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
        System.out.println("Flacky test. In case of failure try to rerun");
        closeBrowser();
    }

    /*
    11
    Positive Test. Check that we can navigate to QA with Java course page

    */

    @Test
    public void openPageQAcourseWithJava() {
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        skillfactoryMainPage.openWebsiteMaximizeWindow();

        // Click button for QA related courses and go to https://skillfactory.ru/courses/testirovanie
        Courses_testirovanie courses_testirovanie = skillfactoryMainPage.clickQACoursesButton();

        // Relationship between two pages
        Java_qa_engineer_testirovshik java_qa_engineer_testirovshik = courses_testirovanie.qa_JavaCourseButton_click();

        // Wait for web page to load
        java_qa_engineer_testirovshik.waitVisibilityOfElementLocated_Xpath(java_qa_engineer_testirovshik.randomMenu);


        // To do another page relation and Assert.
        Assert.assertEquals(java_qa_engineer_testirovshik.courseLink, webDriver.getCurrentUrl());
        closeBrowser();
    }

   /*
    12
    Positive Test. Sign up for QA with Java course

    */

    @Test
    public void signUpQAcourseWithJava() {
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        skillfactoryMainPage.openWebsiteMaximizeWindow();

        // Click button for QA related courses and go to https://skillfactory.ru/courses/testirovanie
        Courses_testirovanie courses_testirovanie = skillfactoryMainPage.clickQACoursesButton();

        // Relationship between two pages
        Java_qa_engineer_testirovshik java_qa_engineer_testirovshik = courses_testirovanie.qa_JavaCourseButton_click();

        // Wait for web page to load
        java_qa_engineer_testirovshik.waitVisibilityOfElementLocated_Xpath(java_qa_engineer_testirovshik.randomMenu);

        // Enter valid test data and submit
        java_qa_engineer_testirovshik.zapisatsaNaKursMethod();
        java_qa_engineer_testirovshik.waitVisibilityOfElementLocated_Xpath(java_qa_engineer_testirovshik.qaCourseSignUpForm);
        java_qa_engineer_testirovshik.signUpFormPositive();


        System.out.println("Flacky test. In case of failure try to rerun");

        Assert.assertEquals("Спасибо! Мы получили вашу заявку на курс.", webDriver.findElement(java_qa_engineer_testirovshik.signUpFormSuccessMessage).getText());
        closeBrowser();
    }

    /*
    13
    Negative Test. Sign up for QA with Java course with wrong email
    */

    @Test
    public void signUpQAcourseWithJavaWrongEmail() {
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        skillfactoryMainPage.openWebsiteMaximizeWindow();

        // Click button for QA related courses and go to https://skillfactory.ru/courses/testirovanie
        Courses_testirovanie courses_testirovanie = skillfactoryMainPage.clickQACoursesButton();

        // Relationship between two pages
        Java_qa_engineer_testirovshik java_qa_engineer_testirovshik = courses_testirovanie.qa_JavaCourseButton_click();

        // Wait for web page to load
        java_qa_engineer_testirovshik.waitVisibilityOfElementLocated_Xpath(java_qa_engineer_testirovshik.randomMenu);

        // Enter valid test data and submit
        java_qa_engineer_testirovshik.zapisatsaNaKursMethod();
        java_qa_engineer_testirovshik.waitVisibilityOfElementLocated_Xpath(java_qa_engineer_testirovshik.qaCourseSignUpForm);
        java_qa_engineer_testirovshik.signUpFormNegativeWrongEmail();

        // Show URL
        System.out.println("This URL is - " + webDriver.getCurrentUrl());

        // Assert
        String errorMessage = webDriver.findElement(java_qa_engineer_testirovshik.signUpFormErrorMessageEmail).getText();

        Assert.assertEquals(java_qa_engineer_testirovshik.signUpFormErrorMessageEmailString, errorMessage);

        System.out.println("Error message shown: " + errorMessage);
        closeBrowser();
    }

    /*
    14
    Negative Test. Sign up for QA with Java course with wrong Name
    */

    @Test
    public void signUpQAcourseWithJavaWrongName() {
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        Java_qa_engineer_testirovshik java_qa_engineer_testirovshik = new Java_qa_engineer_testirovshik(webDriver);
        skillfactoryMainPage.goToQAEngeneeringCoursePage();
        java_qa_engineer_testirovshik.signUpFormNegativeWrongName();

        String errorMessage = webDriver.findElement(java_qa_engineer_testirovshik.signUpFormErrorMessageName).getText();

        Assert.assertEquals(java_qa_engineer_testirovshik.signUpFormErrorMessageNameStringPlease, errorMessage);

        System.out.println("Actual error message: " + errorMessage);

        closeBrowser();
    }

    /*
    15
    Positive Test. When sign up for the course, in contact form click on promocode. Field for promocode input should get visible

    Here no Assert is required. If we can enter text in promocode field, it means field exists.
    */

    @Test
    public void signUpQAcourseCheckPromocodeField() {
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        Java_qa_engineer_testirovshik java_qa_engineer_testirovshik = new Java_qa_engineer_testirovshik(webDriver);
        skillfactoryMainPage.goToQAEngeneeringCoursePage();
        java_qa_engineer_testirovshik.signUpFormCheckPromocodeField();
        closeBrowser();
    }


    /*
    16
    Test https://skillfactory.ru/java-qa-engineer-testirovshik-po
    Check, that you can move mouse coursor to "Курсы" and navigate to "Все онлайн курсы"
    */

    @Test
    /*
    Question:
    Why if I try to click on the element via Actions it returns error?
    Line of code to click on the element:
    new Actions(webDriver).click(coursesButton).perform();
    Error:
    org.openqa.selenium.interactions.MoveTargetOutOfBoundsException: move target out of bounds

    If I try to click on element via command:
    webDriver.findElement(coursesButtonLocator).click();

    It executes without any errors.
     */
    public void checkKursiMenuVseOnlineKursi() {
        Java_qa_engineer_testirovshik java_qa_engineer_testirovshik = new Java_qa_engineer_testirovshik(webDriver);
        java_qa_engineer_testirovshik.goToThisPageURL();
        SkillfactoryCourses skillfactoryCourses = java_qa_engineer_testirovshik.goToCourseMenuAllOnlineCourses();
        Assert.assertEquals(skillfactoryCourses.thisPageURL, webDriver.getCurrentUrl());


        closeBrowser();
    }

    /*
    17
    https://skillfactory.ru/java-qa-engineer-testirovshik-po
    Check that course programme section "Тестирование web" expands
     */
    @Test
    public void courseProgrammeTestirovanieWebSectionExpands() {
        Java_qa_engineer_testirovshik java_qa_engineer_testirovshik = new Java_qa_engineer_testirovshik(webDriver);
        java_qa_engineer_testirovshik.goToThisPageURL();
        java_qa_engineer_testirovshik.courseProgrammeSectionTestirovanieWebExpands();

        // Check if expand section element is visible: Create variable and if statement

        WebElement description = webDriver.findElement(java_qa_engineer_testirovshik.courseProgrammeTestirovanieWebDescription);

        boolean expand;

        if (description.isDisplayed()) {
            expand = true;
            System.out.println("Element is visible");


        } else {

            expand = false;
            System.out.println("Element is not visible");
        }
        Assert.assertTrue(expand);


        closeBrowser();
    }

    /*
    18
    https://skillfactory.ru/java-qa-engineer-testirovshik-po
    Check that course programme section "Автоматизация web c помощью Selenium" expands
     */

    @Test
    public void courseProgrammeAvtomatizacijaWebSPomowjuSeleniumSectionExpands() {
        Java_qa_engineer_testirovshik java_qa_engineer_testirovshik = new Java_qa_engineer_testirovshik(webDriver);
        java_qa_engineer_testirovshik.goToThisPageURL();
        java_qa_engineer_testirovshik.courseProgrammeSectionAvtomatizacijaWebSPomowjuSeleniumExpands();

        // Check if expand section element is visible: Create variable and if statement

        WebElement description = webDriver.findElement(java_qa_engineer_testirovshik.courseProgrammeAvtomatizacijaSeleniumDescription);

        boolean expand;

        if (description.isDisplayed()) {
            expand = true;
            System.out.println("Element is visible");


        } else {

            expand = false;
            System.out.println("Element is not visible");
        }
        Assert.assertTrue(expand);

        closeBrowser();
    }

    /*
    19
    https://skillfactory.ru/courses
    Test if course IT Специалист с нуля is visible
     */

    @Test
    public void courseITSpecialistSNuljaIsVisible() {
        SkillfactoryCourses skillfactoryCourses = new SkillfactoryCourses(webDriver);
        skillfactoryCourses.goToThisPageURL();

        skillfactoryCourses.checkCourseVisiblity_ITSpecialistSNulja();
        Assert.assertTrue(skillfactoryCourses.courseVisibilityITSpecialistSNulja);


        closeBrowser();
    }

     /*
    20
    https://skillfactory.ru/courses
    Test if course Teстировщик на Java is visible
     */

    @Test
    public void course_TestirovshikNaJava_IsVisible() {
        SkillfactoryCourses skillfactoryCourses = new SkillfactoryCourses(webDriver);
        skillfactoryCourses.goToThisPageURL();
        skillfactoryCourses.checkCourseVisiblity_TestirovwikNaJava();

        Assert.assertTrue(skillfactoryCourses.courseTestirovwikNaJava);

        closeBrowser();
    }

    /*
    21
    https://skillfactory.ru/courses
    Check "Only in July" promo is visible
     */

    @Test
    public void checkJulyPromo() {
        SkillfactoryCourses skillfactoryCourses = new SkillfactoryCourses(webDriver);
        skillfactoryCourses.goToThisPageURL();
        skillfactoryCourses.findJulyPromotion();

        closeBrowser();
    }

    /*
    22
    https://skillfactory.ru/
    Check if chat opens, when you click on chat button
     */

    @Test
    public void openChatWindow() {
        SkillfactoryMainPage skillfactoryMainPage = new SkillfactoryMainPage(webDriver);
        skillfactoryMainPage.openWebsite();
        skillfactoryMainPage.openChat();


        closeBrowser();
    }


    public void closeBrowser() {
        webDriver.close();
    }


}
