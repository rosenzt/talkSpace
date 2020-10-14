package page_objects;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;

abstract class BasePage {
    WebDriver driver;

    @FindBy(css = "#cookieconsentmodal")
    WebElement cookieModal;

    @FindBy(css = "[name='cookies']")
    WebElement acceptCookies;

    @FindBy(css = "#btns")
    WebElement buttons;

    BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    void click(WebElement element) {
        try {
            element.click();
            sleep(1000);
        } catch (ElementClickInterceptedException elementClickInterceptedException) {
            if (cookieModal.isDisplayed()) {
                click(acceptCookies);
                sleep(1000);
                click(buttons);
                click(element);
            }
        } catch (NoSuchElementException missingElement) {
            System.out.println("Exception thrown");
        }
    }

    String getText(WebElement element) {
        return element.getText();
    }

    void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException interruptedException) {
            System.out.println("Exception thrown");
        }
    }
}
