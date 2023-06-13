import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    //------------------------------Other variables----------------------------------------
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

    // Click on All course button and go to /courses page
    public SkillfactoryCourses clickAllCoursesButton(){
        webDriver.findElement(allCoursesButton).click();
        return new SkillfactoryCourses(webDriver);
    }
}
