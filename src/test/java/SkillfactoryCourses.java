import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SkillfactoryCourses {
    //------------------------------WebDriver and Constructor----------------------------------------

    public WebDriver webDriver;

    public SkillfactoryCourses() {

    }

    public SkillfactoryCourses(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //------------------------------Locators and variables---------------------------------------------
    By courseITSpecialistSNuljaLocator = By.xpath("//li[@id='START']//a[contains(href,it-specialist-proforientaciya)]");
    By courseTestirovwikNaJavaLocator = By.xpath("//li[@id='QAJA']//a[contains(href,java-qa-engineer-testirovshik-po)]");

    By julyPromo = By.xpath("//div[@data-elem-id='1688037360644']");
    //------------------------------Other variables----------------------------------------


    String thisPageURL = "https://skillfactory.ru/courses";

    boolean courseVisibilityITSpecialistSNulja;
    boolean courseTestirovwikNaJava;



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

    public void checkCourseVisiblity_ITSpecialistSNulja(){
        WebElement course = webDriver.findElement(courseITSpecialistSNuljaLocator);



        if (course.isDisplayed()) {
            courseVisibilityITSpecialistSNulja = true;
            System.out.println("Element is visible");


        } else {

            courseVisibilityITSpecialistSNulja = false;
            System.out.println("Element is not visible");
        }


        }

    public void checkCourseVisiblity_TestirovwikNaJava(){
        WebElement course = webDriver.findElement(courseTestirovwikNaJavaLocator);
        if (course.isDisplayed()) {
            courseTestirovwikNaJava = true;
            System.out.println("Element is visible");


        } else {

            courseTestirovwikNaJava = false;
            System.out.println("Element is not visible");
        }
    }

    public void findJulyPromotion(){
        webDriver.findElement(julyPromo);
    }




}


