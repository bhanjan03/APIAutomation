package com.apiautomationframework.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Key;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({"system:properties",
    "system:env",
    "file:./src/test/resources/config/config.properties",
    "file:./src/test/resources/config/data.properties"})
public interface FrameworkConfig extends Config {

	@Key("target")
    String target();

    @Key("browser")
    String browser();

    @Key("headless")
    Boolean headless();

    @Key("url")
    String url();

    @Key("timeout")
    int timeout();

    @Key("grid.url")
    String gridUrl();

    @Key("grid.port")
    String gridPort();

    @Key("faker.locale")
    String faker();
    
    String override_reports();

    boolean retry_failed_tests();

    int retry_count();

    String base_uri();
    
    @Key("username")
    String username();
    
    @Key("password")
    String password();
	}