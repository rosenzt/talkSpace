package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebCalcHomePage extends BasePage {

    @FindBy(css = "#Btn1")
    WebElement oneButton;

    @FindBy(css = "#Btn2")
    WebElement twoButton;

    @FindBy(css = "#Btn3")
    WebElement threeButton;

    @FindBy(css = "#Btn4")
    WebElement fourButton;

    @FindBy(css = "#Btn5")
    WebElement fiveButton;

    @FindBy(css = "#Btn6")
    WebElement sixButton;

    @FindBy(css = "#Btn7")
    WebElement sevenButton;

    @FindBy(css = "#Btn8")
    WebElement eightButton;

    @FindBy(css = "#Btn9")
    WebElement nineButton;

    @FindBy(css = "#Btn0")
    WebElement zeroButton;

    @FindBy(css = "#BtnPlus")
    WebElement plusButton;

    @FindBy(css = "#BtnMinus")
    WebElement minusButton;

    @FindBy(css = "#BtnParanL")
    WebElement openPerentacies;

    @FindBy(css = "#BtnMult")
    WebElement multiplyButton;

    @FindBy(css = "#BtnSin")
    WebElement sinus;

    @FindBy(css = "#BtnParanR")
    WebElement closePerentacies;

    @FindBy(css = "#BtnDot")
    WebElement dotButton;

    @FindBy(css = "#BtnCalc")
    WebElement equalsButton;

    @FindBy(css = "#input")
    WebElement inputAndResult;

    @FindBy(css = "#BtnClear")
    WebElement clearButton;

    @FindBy(css = "#hist")
    WebElement historyButton;

    @FindBy(css = "#histframe [data-inp]")
    List<WebElement> historyListRecords;

    public WebCalcHomePage(WebDriver driver) {
        super(driver);
    }

    public double getResult() {
        return Double.parseDouble(getText(inputAndResult));
    }

    public void pressingButtons(List<String> keys) {
        Map<String, WebElement> elements = new HashMap<String, WebElement>(); //Placing relevant button elements into the HashMap, allows a cleaner code in the test method in a scenario of multiple clicks.

        elements.put("1", oneButton);
        elements.put("2", twoButton);
        elements.put("3", threeButton);
        elements.put("4", fourButton);
        elements.put("5", fiveButton);
        elements.put("6", sixButton);
        elements.put("7", sevenButton);
        elements.put("8", eightButton);
        elements.put("9", nineButton);
        elements.put("0", zeroButton);
        elements.put("+", plusButton);
        elements.put("-", minusButton);
        elements.put("*", multiplyButton);
        elements.put("=", equalsButton);
        elements.put("(", openPerentacies);
        elements.put(")", closePerentacies);
        elements.put(".", dotButton);
        elements.put("sin", sinus);

        for (String key : keys) {
            click(elements.get(key));
        }
    }

    public void clearScreen() {
        click(clearButton);
    }

    public void openHistory() {
        click(historyButton);
    }

    public void verify(String value) {
        Actions actions = new Actions(driver);
        actions.moveToElement(inputAndResult);
        actions.contextClick();


        Double.parseDouble(value);
    }

    public int countHistory() {
       return historyListRecords.size();
    }

}

