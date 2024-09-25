package org.example.helpers.utils.configuration;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;

public class DriverSettings {
    public static void configurationDriver (){
        ConfigApp configApp = ConfigFactory.create(ConfigApp.class);
        Configuration.baseUrl = configApp.baseUiUrl();
        Configuration.browser = configApp.browser();
        Configuration.browserSize = configApp.browserSize();

        Configuration.pageLoadTimeout = 90_000;
        Configuration.timeout = 20_000;
        Configuration.screenshots=true;
    }
}
