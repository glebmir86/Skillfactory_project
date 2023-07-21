Hi,

Purpose of this project is to cover https://skillfactory.ru/ with Selenium autotests. 

This readme file will help to navigate through Selenium autotests and describe purpose of each test.

It contains 21 test.

Class description:

Tests                           - class with all autotests
SkillfactoryMainPage            - https://skillfactory.ru/
SkillfactoryCourses             - https://skillfactory.ru/courses
Java_qa_engineer_testirovshik   - https://skillfactory.ru/java-qa-engineer-testirovshik-po
Courses_testirovanie            - https://skillfactory.ru/courses/testirovanie
SkillfactoryContactForm         - contact form on main page https://skillfactory.ru/

Each class contains:
WebDriver and Constructor
Locators and variables
Other variables
Methods

Template located in Template file

How to run tests:
Navigate to Tests class. Location in project: src/test/java/Tests.java 
Click on class with right mouse button
Click run tests. 


Autotest Description:

----Test contact form, where user can leave his contacts and wait for Skillfactory staff to reach them. This form is located on different places in main page

1. contactFormPositiveTest 
   Test contact form with valid data. Should submit form with no errors

2. contactFormWrongEmail 
   Contact form, negative test. Checks, that incorrect email returns error

3. contactFormWrongPhone
   Contact form, negative test. Checks, that incorrect phone returns an error. Phone number too short

4. contactFormPhoneNumberForLatvia
   Contact form positive test.
   Choose another country - Latvia for phone number

5. contactFormNoDataProcessing
   Сontact form, negative test.
   Do not agree for Data processing. Should return error message

----Integrational tests

6. testLinkHabr
   Check, that browser opens new tab with https://habr.com/ru/companies/skillfactory/articles/, when click on Habr button in footer

7. testLinkYoutube
   Check, that browser opens new tab with skillfactory youtube channel, when click on Youtube button in footer

8. testLinkTelegram
   Check, that browser opens new tab with skillfactory Telegram channel, when click on Telegram button in footer
   Flacky test, for unknown reason sometimes Telegram button in unclickable

----Sighup for newsletter

9. signUpForEmailPositive
   Positive test. Sign up for Skillfactory newsletter in footer. Provide valid email address
   Test may fail due to Captcha check

10. signUpForEmailNegative
    Negative test. Sign up for email in footer. Provide invalid email address
    Error message should appear

----Tests related to https://skillfactory.ru/java-qa-engineer-testirovshik-po

11. openPageQAcourseWithJava
    Positive Test. Check that we can navigate to QA with Java course page from main page

12. signUpQAcourseWithJava
    Positive Test. Sign up for QA with Java course

13. signUpQAcourseWithJavaWrongEmail
    Negative Test. Sign up for QA with Java course with wrong email

14. signUpQAcourseWithJavaWrongName
    Negative Test. Sign up for QA with Java course with wrong Name

15. signUpQAcourseCheckPromocodeField
    Positive Test. When sign up for the course, in contact form click on promocode. Field for promocode input should get visible

16. checkKursiMenuVseOnlineKursi

    Check, that you can move mouse coursor to "Курсы" and navigate to "Все онлайн курсы"

    Dear mentor, please check my question commented under @Test 

    Question:
    
    Why if I try to click on the element via Actions it returns error?
    Line of code to click on the element:
    new Actions(webDriver).click(coursesButton).perform();
    Error:
    org.openqa.selenium.interactions.MoveTargetOutOfBoundsException: move target out of bounds

    If I try to click on element via command:
    webDriver.findElement(coursesButtonLocator).click();

    It executes without any errors.

17. courseProgrammeTestirovanieWebSectionExpands
    Check that course programme section "Тестирование web" expands

18. courseProgrammeAvtomatizacijaWebSPomowjuSeleniumSectionExpands
    heck that course programme section "Автоматизация web c помощью Selenium" expands

----Tests related to https://skillfactory.ru/courses

19. courseITSpecialistSNuljaIsVisible
    Test if course IT Специалист с нуля is visible

20. course_TestirovshikNaJava_IsVisible
    Test if course Teстировщик на Java is visible

21. checkJulyPromo
    Check "Only in July" promo is visible

----Test online chat

22. openChatWindow
    Check if chat opens, when you click on chat button


Would like to perform these tests, but need additional improvement from Developers team:
1. mainPageAllCourses
   Check, that website shows all courses after click on "All courses" button.
Issue:
   Can not do this test, as there is no unique attribute for link with course name. And script fetches all links. Therefore we can not count how many links are on the page.
For example:

Current element:
<a href="https://skillfactory.ru/python-fullstack-web-developer" target="_blank" style="color: inherit" rel="noopener">Fullstack-разработчик на&nbsp;Python</a>

Element with unique attribute:
<a href="https://skillfactory.ru/python-fullstack-web-developer" target="_blank" style="color: inherit" rel="noopener" **coursename="1"**>Fullstack-разработчик на&nbsp;Python</a>



