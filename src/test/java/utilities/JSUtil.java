package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtil {

    public static void pageLoadColplte() {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("return document.readyState").equals("complete");
    }
    public static void flashElement(WebElement element, WebDriver driver) {
        String bgColor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 8; i++) {
            changeColor("#000000", element, driver);
            changeColor(bgColor, element, driver);
        }
    }

    private static void changeColor(String color, WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
        }
    }

    public static void drawBorder(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public static String getTitleByJS() {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        String title = js.executeScript("return document.title;").toString();
        return title;
    }

    public static void clickElementByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public static void generateJSAlert(String message) {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("alert('" + message + "')");
    }

    public static void refreshPageByJS() {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("history.go(0)");
    }
}
