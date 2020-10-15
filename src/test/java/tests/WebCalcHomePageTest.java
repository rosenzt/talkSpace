package tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_objects.WebCalcHomePage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebCalcHomePageTest {

    WebDriver driver;
    private WebCalcHomePage webCalcHomePage;
    static ExtentTest test;
    static ExtentReports report;

    @BeforeClass
    void lunchSuit() {
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        report = new ExtentReports(System.getProperty("user.dir") + "/src/main/resources/Reports/report.html", false);
        test = report.startTest("WebCalcHomePageTest");
        driver.manage().window().maximize();
        this.driver.get("https://web2.0calc.com/");

        webCalcHomePage = new WebCalcHomePage(driver);
    }

    /**
     * The following method verifies the result of 2+3 is 5
     */
    @Test(priority = 0)
    void testAddition() {
        try {
            webCalcHomePage.clearScreen();
            List<String> buttons = Arrays.asList("2", "+", "3", "=");
            webCalcHomePage.pressingButtons(buttons);

            boolean pass = (webCalcHomePage.getLatestResult() == 5);
            if (pass) {
                test.log(LogStatus.PASS, "Test passed");
            } else
                test.log(LogStatus.FAIL, "Test failed");
        } catch (Exception exception) {
            test.log(LogStatus.ERROR, "Following exception has been thrown " + exception);
        }
    }

    /**
     * The following method verifies the result of 10-2 is 8
     */
    @Test(priority = 1)
    void testSubtraction() {
        try {
            webCalcHomePage.clearScreen();
            List<String> buttons = Arrays.asList("1", "0", "-", "2", "=");
            webCalcHomePage.pressingButtons(buttons);

            boolean pass = (webCalcHomePage.getLatestResult() == 8);
            if (pass) {
                test.log(LogStatus.PASS, "Test passed");
            } else
                test.log(LogStatus.FAIL, "Test failed");
        } catch (Exception exception) {
            test.log(LogStatus.ERROR, "Following exception has been thrown " + exception);
        }
    }

    /**
     * The following method verifies the result of (10-2)*2 != 20
     */
    @Test(priority = 2)
    void testChainProblem() {
        try {
            webCalcHomePage.clearScreen();
            List<String> buttons = Arrays.asList("(", "1", "0", "-", "2", ")", "*", "2", "=");
            webCalcHomePage.pressingButtons(buttons);

            boolean pass = (webCalcHomePage.getLatestResult() != 20);
            if (pass) {
                test.log(LogStatus.PASS, "Test passed");
            } else
                test.log(LogStatus.FAIL, "Test failed");
        } catch (Exception exception) {
            test.log(LogStatus.ERROR, "Following exception has been thrown " + exception);
        }
    }

    /**
     * The following method verifies the result of sin(30) = 0.5
     */
    @Test(priority = 3)
    void testSinus() {
        try {
            webCalcHomePage.clearScreen();
            List<String> buttons = Arrays.asList("sin", "3", "0", "=");
            webCalcHomePage.pressingButtons(buttons);

            boolean pass = (webCalcHomePage.getLatestResult() == 0.5);
            if (pass) {
                test.log(LogStatus.PASS, "Test passed");
            } else
                test.log(LogStatus.FAIL, "Test failed");
        } catch (Exception exception) {
            test.log(LogStatus.ERROR, "Following exception has been thrown " + exception);
        }
    }

    /**
     * The following method verifies the number of actions listed in the history record (in this case 4)
     */
    @Test(priority = 4)
    void testHistory() {
        try {
            webCalcHomePage.openHistory();

            boolean pass = (webCalcHomePage.countHistory() == 4);
            if (pass) {
                test.log(LogStatus.PASS, "Test passed");
            } else
                test.log(LogStatus.FAIL, "Test failed");
        } catch (Exception exception) {
            test.log(LogStatus.ERROR, "Following exception has been thrown " + exception);
        }
    }

    @AfterClass
    void tearDown() {
        driver.quit();
    }
}
