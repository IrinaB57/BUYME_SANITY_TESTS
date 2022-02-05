package org.buyme.engine;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * A new webdriver instance should be initialised per test flow, and should be destroyed at the end of its lifecycle via the teardown method.
 */
public class BaseTest {

    public static final String DEFAULT_DRIVER = "DEFAULT_DRIVER";

    private Map<String, WebDriver> drivers = new HashMap<>();
    private Map<String, Object> testData = new HashMap<>();

    public WebDriver getDriver(String name) {
        WebDriver driver = drivers.get(name);
        if (driver == null) {
            driver = getDriverFromFactory();
            drivers.put(name, driver);
        }
        return driver;
    }

    protected WebDriver getDriverFromFactory() {
        return WebdriveSingleton.getInstance().getDriver();
    }

    public WebDriver getDriver() {
        return getDriver(DEFAULT_DRIVER);
    }

    public void tearDown() {
        if (drivers != null) {
            drivers.entrySet().stream().forEach(entry -> entry.getValue().quit());
            drivers.clear();
        }
        testData.clear();
    }
}
