import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Courses_testirovanie {
    //------------------------------WebDriver and Constructor----------------------------------------

    public WebDriver webDriver;

    public Courses_testirovanie() {

    }

    public Courses_testirovanie(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //------------------------------Locators and variables---------------------------------------------
    By qa_JavaCourseButton = By.xpath("//div[@data-elem-id='1679951555532']");

    //------------------------------Other variables----------------------------------------


    //------------------------------Methods----------------------------------------

    //-----------------------------Common methods

    public void switchToNextTab() {
        String originalWindow = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    //------------------------------Methods



    // Click on Java course button

    public Java_qa_engineer_testirovshik qa_JavaCourseButton_click() {
        webDriver.findElement(qa_JavaCourseButton).click();

        // Switch to next tab
        switchToNextTab();

        return new Java_qa_engineer_testirovshik(webDriver);
    }

}
