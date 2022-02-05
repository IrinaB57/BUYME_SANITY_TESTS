package org.buyme.engine;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.buyme.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

/**
 *  Webdriver setup based on the browser mentioned in the properties file
 */

public class WebdriveSingleton {
    private static WebdriveSingleton instance = new WebdriveSingleton();
    private Configuration configuration = Configuration.getInstance();
    private final String browserName = configuration.getProperty("browser");

    public static WebdriveSingleton getInstance() {
        return instance;
    }

    public WebDriver getDriver() {
        return getDriver(getBrowserType(), null);
    }

    public WebDriver getDriver(DriverType browserType, String testName) {
        return createDriver(browserType, testName);
    }

    public DriverType getBrowserType() {
        switch (browserName) {
            case "local-chrome":
            case "chrome":
                return DriverType.CHROME;
            case "local-firefox":
            case "firefox":
                return DriverType.FIREFOX;
            case "local-ie":
            case "iexplorer":
            case "ie":
                return DriverType.INTERNETEXPLORER;
            default:
                throw new IllegalArgumentException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
        }
    }

    private WebDriver createDriver(DriverType browserType, String testName) {
        RemoteWebDriver driver = null;
        switch (browserType) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setCapability("acceptInsecureCerts", true);
                options.setCapability("acceptSslCerts", true);
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                break;
            case INTERNETEXPLORER:
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                driver.manage().window().maximize();
                break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(configuration.getProperty("webdriver.implicit_wait", "20"))));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(configuration.getProperty("webdriver.timeout", "30"))));
        driver.manage().deleteAllCookies();

        return driver;
    }

    public enum DriverType {
        CHROME,
        FIREFOX,
        INTERNETEXPLORER,
    }
}
