package org.example.helpers.utils.configuration;

import com.codeborne.selenide.WebDriverRunner;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class BrowserUtils {
    public static WebDriver getDriver(){
        return WebDriverRunner.getWebDriver();
    }

    public static void switchToNewWindow(WebDriver driver) {
        String currentWindowHandle = driver.getWindowHandle();
        for(String windowHandle: driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @SneakyThrows
    public static boolean isOpenMoreOnePage(WebDriver driver, int countSec) {
        boolean isNewPageOpen = false;
        while (!isNewPageOpen && countSec > 0) {
            sleep(1000);
            isNewPageOpen = driver.getWindowHandles().size() > 1;
            countSec --;
        }
        return isNewPageOpen;
    }
}


