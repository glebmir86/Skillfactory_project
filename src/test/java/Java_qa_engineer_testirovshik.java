import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Java_qa_engineer_testirovshik {

    //------------------------------Intro----------------------------------------
    /*  Web page - https://skillfactory.ru/java-qa-engineer-testirovshik-po

     */

    //------------------------------WebDriver and Constructor----------------------------------------

    public WebDriver webDriver;

    public Java_qa_engineer_testirovshik() {

    }

    public Java_qa_engineer_testirovshik(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //------------------------------Locators and variables---------------------------------
    By randomMenu = By.xpath("//*[@id=\"rec225644025\"]/div/div/div[2]");
    By zapisatsaNaKursButton = By.xpath("//div[@class='t396']//div[@data-artboard-recid='359656457']//a[contains(text(),'Записаться на курс')]");

    By qaCourseSignUpForm = By.xpath("//form[@id='form581372890']");
    By signUpFormName = By.xpath("//form[@id='form581372890']//input[@id='input_1495810359387']");
    By signUpFormEmail = By.xpath("//form[@id='form581372890']//input[@id='input_1495810354468']");
    By signUpFormPhone = By.xpath("//form[@id='form581372890']//input[@type='tel']");
    By signUpFormSubmitButton = By.xpath("//form[@id='form581372890']//button[@class='t-submit']");
    By signUpFormSuccessMessage = By.xpath("//*[@id='rec600702796']//h1[@class='tn-atom']//span");

    By signUpFormErrorMessageEmail = By.xpath("//form[@id='form581372890']//div[@class='t-form__errorbox-middle']//p[@class='t-form__errorbox-item js-rule-error js-rule-error-email']");

    By signUpFormErrorMessageName = By.xpath("//form[@id='form581372890']//div[@class='t-form__errorbox-middle']//p[@class='t-form__errorbox-item js-rule-error js-rule-error-req']");

    By signUpFormPromocodeButton = By.xpath("//div[@id='rec581372890']//a[@class='promolink']");

    By getSignUpFormPromocodeField = By.xpath("//div[@id='rec581372890']//input[@name='promocode']");

    By coursesButtonLocator = By.xpath("//div[@data-artboard-recid='359656457']//div[@data-elem-id='1644913854327']");

    By coursesMenuAllCourses = By.xpath("//div[@class='t978__content']//a[@href='https://skillfactory.ru/courses']");

    By courseProgrammeTestirovanieWeb = By.xpath("//div[@id='rec518304329']//span[contains(text(),'Тестирование web')]");

    By courseProgrammeTestirovanieWebDescription = By.xpath("//div[@id='rec518304329']//div[@id='accordion1_518304329']//div[@field='li_descr__1669247395604']");

    By courseProgrammeAvtomatizacijaSelenium = By.xpath("//div[@id='rec518305069']//button[@aria-controls='accordion4_518305069']");

    By courseProgrammeAvtomatizacijaSeleniumDescription = By.xpath("//div[@id='accordion4_518305069']//div[@field='li_descr__1669247723184']");


    //------------------------------Other variables----------------------------------------
    String courseLink = "https://skillfactory.ru/java-qa-engineer-testirovshik-po";

    String signUpFormErrorMessageEmailString = "Please enter a valid email address";

    String signUpFormErrorMessageNameStringPlease = "Please fill out all required fields";
    String randomPromoCode = "ABC12345";

    String thisPageURL = "https://skillfactory.ru/java-qa-engineer-testirovshik-po";


    //------------------------------Methods----------------------------------------

    // Wait for webElement by Xpath
    public void waitVisibilityOfElementLocated_Xpath(By waitFor) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitFor));
    }

    // Go to this page URL

    public void goToThisPageURL() {
        webDriver.get(thisPageURL);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    // Click button - submit for QA course
    public void zapisatsaNaKursMethod() {
        webDriver.findElement(zapisatsaNaKursButton).click();
    }

    // Feel contact form with correct test data
    public void signUpFormPositive() {
        SkillfactoryContactForm contact = new SkillfactoryContactForm();
        webDriver.findElement(signUpFormName).sendKeys(contact.correctName);
        webDriver.findElement(signUpFormEmail).sendKeys(contact.correctEmail);
        webDriver.findElement(signUpFormPhone).sendKeys(contact.phoneNumberRU);
        webDriver.findElement(signUpFormSubmitButton).click();

    }

    // Feel contact form with invalid email address
    public void signUpFormNegativeWrongEmail() {
        SkillfactoryContactForm contact = new SkillfactoryContactForm();
        webDriver.findElement(signUpFormName).sendKeys(contact.correctName);
        webDriver.findElement(signUpFormEmail).sendKeys(contact.invalidEmail);
        webDriver.findElement(signUpFormPhone).sendKeys(contact.phoneNumberRU);
        webDriver.findElement(signUpFormSubmitButton).click();

    }

    // Feel sigh up form without name
    public void signUpFormNegativeWrongName() {
        SkillfactoryContactForm contact = new SkillfactoryContactForm();
        webDriver.findElement(signUpFormName).sendKeys(contact.emptyName);
        webDriver.findElement(signUpFormEmail).sendKeys(contact.correctEmail);
        webDriver.findElement(signUpFormPhone).sendKeys(contact.phoneNumberRU);
        webDriver.findElement(signUpFormSubmitButton).click();


    }

    public void signUpFormCheckPromocodeField() {
        webDriver.findElement(signUpFormPromocodeButton).click();
        webDriver.findElement(getSignUpFormPromocodeField).sendKeys(randomPromoCode);
    }

    public SkillfactoryCourses goToCourseMenuAllOnlineCourses() {
        webDriver.findElement(coursesButtonLocator).click();
        webDriver.findElement(coursesMenuAllCourses).click();
        return new SkillfactoryCourses(webDriver);
    }

    public void courseProgrammeSectionTestirovanieWebExpands() {
        webDriver.findElement(courseProgrammeTestirovanieWeb).click();


    }

    public void courseProgrammeSectionAvtomatizacijaWebSPomowjuSeleniumExpands() {
        webDriver.findElement(courseProgrammeAvtomatizacijaSelenium).click();


    }
}

