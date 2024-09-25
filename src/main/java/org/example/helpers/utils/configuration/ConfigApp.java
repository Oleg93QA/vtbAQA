package org.example.helpers.utils.configuration;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:test.properties")
public interface ConfigApp extends Config {

    @Key("browser")
    String browser();

    @Key("browser.size")
    String browserSize();

    @Key("base.ui.url")
    String baseUiUrl();

    @Key("base.api.url")
    String baseApiUrl();

    @Key("base.api.path")
    String baseApiPath();
}
