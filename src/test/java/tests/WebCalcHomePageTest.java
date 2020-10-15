package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_objects.WebCalcHomePage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebCalcHomePageTest {

    WebDriver driver;
    private WebCalcHomePage webCalcHomePage;

    @BeforeClass
    void lunchSuit() {
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        this.driver.get("https://web2.0calc.com/");

        webCalcHomePage = new WebCalcHomePage(driver);
    }

    /**
     * The following method verifies the result of 2+3 is 5
     */
    @Test(priority = 0)
    void testAddition() {
        webCalcHomePage.clearScreen();
        List<String> buttons = Arrays.asList("2", "+", "3", "=");
        webCalcHomePage.pressingButtons(buttons);
        Assert.assertEquals(5, webCalcHomePage.getResult());
    }

    /**
     * The following method verifies the result of 10-2 is 8
     */
    @Test(priority = 1)
    void testSubtraction() {
        webCalcHomePage.clearScreen();
        List<String> buttons = Arrays.asList("1", "0", "-", "2", "=");
        webCalcHomePage.pressingButtons(buttons);
        Assert.assertEquals(8, webCalcHomePage.getResult());
    }

    /**
     * The following method verifies the result of (10-2)*2 != 20
     */
    @Test(priority = 2)
    void testChainProblem() {
        webCalcHomePage.clearScreen();
        List<String> buttons = Arrays.asList("(", "1", "0", "-", "2", ")", "*", "2", "=");
        webCalcHomePage.pressingButtons(buttons);
        Assert.assertNotEquals(20, webCalcHomePage.getResult());
    }

    /**
     * The following method verifies the result of sin(30) + 0.5
     */
    @Test(priority = 3)
    void testSinus() {
        webCalcHomePage.clearScreen();
        List<String> buttons = Arrays.asList("sin", "3", "0", "=");
        webCalcHomePage.pressingButtons(buttons);
        Assert.assertEquals(0.5, webCalcHomePage.getResult());
    }

    /**
     * The following method verifies the number of actions listed in the history record (in this case 4)
     */
    @Test(priority = 4)
    void testHistory() {
        webCalcHomePage.openHistory();
        Assert.assertEquals(4,webCalcHomePage.countHistory());

    }

    //@AfterClass
    void tearDown() {
        driver.quit();
    }
}
