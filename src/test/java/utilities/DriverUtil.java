package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;

public class DriverUtil {

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();


    private DriverUtil() {
    }

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browserParamFromEnv = System.getProperty("browser");
            String browser = browserParamFromEnv == null ? ConfigReader.getProperty("browser") : browserParamFromEnv;
            switch (browser) {
                case "chrome":
                    String downloadPath = System.getProperty("user.dir");
                    WebDriverManager.chromedriver().setup();
                    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                    chromePrefs.put("profile.default_content_settings.popups", 0);
                    chromePrefs.put("download.default_directory", downloadPath);


                    driverPool.set(new ChromeDriver(new ChromeOptions().setExperimentalOption("prefs", chromePrefs).setExperimentalOption("useAutomationExtension", false).addArguments("--disable-notifications")));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "chrome-headless":
                    String downloadPathHeadless = System.getProperty("user.dir");
                    WebDriverManager.chromedriver().setup();
                    HashMap<String, Object> chromePrefHeadless = new HashMap<String, Object>();
                    chromePrefHeadless.put("profile.default_content_settings.popups", 0);
                    chromePrefHeadless.put("download.default_directory", downloadPathHeadless);
                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true).setExperimentalOption("prefs", chromePrefHeadless)));
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                case "chrome-remote":
                    try {
                        URL url = new URL("http://localhost:4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "firefox-remote":
                    try {
                        URL url = new URL("http://localhost:4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("firefox");
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    }
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Edge");
                    }
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your OS doesn't support Safari");
                    }
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;
                default:
                    throw new RuntimeException("Invalid browser name!");
            }
        }
        return driverPool.get();
    }

    public static void closeDriver() {
        driverPool.get().quit();
        driverPool.remove();
    }
}
