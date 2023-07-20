import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SkillfactoryContactForm {

    //------------------------------WebDriver and Constructor----------------------------------------

    public WebDriver webDriver;

    public SkillfactoryContactForm() {

    }

    public SkillfactoryContactForm(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    //------------------------------Locators and variables---------------------------------------------
    //Contact form

    By nameField = By.xpath("//form[@id='form562643444']//input[@aria-label='name' and @name='name']");

    By emailField = By.xpath("//form[@id='form562643444']//input[@aria-label='email' and @name='email']");

    By phoneNumberField = By.xpath("//form[@id='form562643444']//input[@type='tel' and @name='tildaspec-phone-part[]']");

    By submitButton = By.xpath("//form[@id='form562643444']//button[@type='submit' and @class='t-submit']");

    By successPopUpMessage = By.xpath("//div[@id='tildaformsuccesspopuptext']");


    String locatorForContactFormSuccessMessageActualMessage = "//div[@id='tildaformsuccesspopuptext']";

    By contactFormWrongEmailMessage = By.xpath("//div[@class='t-input-error' and contains(text(),'Please enter a valid email address')]");

    By contactFormWrongPhoneNumber = By.xpath("//div[@class='t-input-error' and contains(text(),'Value is too short')]");

    By contactFormChooseCountry = By.xpath("//form[@id='form562643444']//span[@class='t-input-phonemask__select-triangle']");

    By contactFormChooseCountryLatvia = By.xpath("//form[@id='form562643444']//div[@class='t-input-phonemask__options-item' and contains(@data-phonemask-name,'Latvia')]");

    By contactFormGetLatviaCode = By.xpath("//form[@id='form562643444']//span[@class='t-input-phonemask__select-code']");

    By contactFormCheckBoxForDataProcessing = By.xpath("//form[@id='form562643444']//div[@class='t-checkbox__indicator']");

    By contactFormCheckBoxForDataProcessingErrorMessage = By.xpath("//div[@class='t-input-error' and contains(text(),'Required field')]");

    //------------------------------Other variables----------------------------------------

    String contactFormSuccessMessageExpected = "Спасибо! Мы позвоним вам в течение дня с 10 до 19 и поможем выбрать направление для обучения"; // Ожидаемое сообщение при успешном заполнении формы
    String contactFormNoDataProcessingErrorMessage = "Required field";

    String correctName = "Test user";
    String emptyName = "";
    String correctEmail = "testuser@gmail.com";
    String invalidEmail = "testuser#123.com";
    String phoneNumberRU = "4991234123";


    //------------------------------Methods----------------------------------------

    // Common methods

    //Open website
    public void openWebsite() {
        webDriver.get("https://skillfactory.ru/");
    }


    // Wait for webElement by Xpath
    public void waitVisibilityOfElementLocated_Xpath(By waitFor) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitFor));
    }

    // Contact Form

    // Type name
    public void contactFormName(String name) {
        webDriver.findElement(nameField).sendKeys(name);
    }

    // Type email

    public void contactFormEmail(String email) {
        webDriver.findElement(emailField).sendKeys(email);
    }

    // Type phone number

    public void contactFormPhoneNumber(String phoneNumber) {
        webDriver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    // Contact form click submit button

    public void contactFormClickSubmitButton() {
        webDriver.findElement(submitButton).click();
    }

    // Contact Form positive scenario
    public void contactFormPositiveScenario() {
        contactFormName("Gleb");
        contactFormEmail("Glebtest@test.com");
        contactFormPhoneNumber("4991234123");
        webDriver.findElement(submitButton).click();
        waitVisibilityOfElementLocated_Xpath(successPopUpMessage);
    }

    //Get actual message for contactFormPositiveScenario
    public void contactFormPositiveScenario_getActualMessage() {
        webDriver.findElement(By.xpath(locatorForContactFormSuccessMessageActualMessage)).getText();
    }

    //Choose Latvia for phone number

    public void contactFormPhoneNumberLatvia() {
        webDriver.findElement(contactFormChooseCountry).click();
        webDriver.findElement(contactFormChooseCountryLatvia).click();
    }

    // Click on Data processing checkbox

    public void contactFormClickDataProcessingCheckbox() {
        webDriver.findElement(contactFormCheckBoxForDataProcessing).click();
    }

    //Get no data processing error message


    public void contactFormDataProcessingCheckboxErrorMessage() {
        webDriver.findElement(contactFormCheckBoxForDataProcessingErrorMessage).getText();
    }

}


