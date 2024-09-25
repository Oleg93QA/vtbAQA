package org.example.listeners;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.nio.charset.StandardCharsets;

public class AllureAttachment {
    //приложить код страницы
    @Attachment(value = "PageSource", type ="test/plain")
    public static byte[] pageSource(){
        return WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }
    //приложи скрин
    @Attachment(value = "PageScreen", type ="image/png")
    public static byte[] PageScreen(){
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
    // логи браузера
    @Attachment(value = "BrowserLogs", type ="test/plain")
    public static String getLogs(){
        String browser = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getCapabilities().getBrowserName();
        if(browser.equals("chrome")){
            return String.join("\n", Selenide.getWebDriverLogs(LogType.BROWSER));
        }
        return null;
    }
    //
    @Attachment(value = "{0}", type = "text/html")
    public String log(String name, String value){
        return value;
    }


}
