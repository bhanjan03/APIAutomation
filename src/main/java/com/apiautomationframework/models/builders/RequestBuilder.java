package com.apiautomationframework.models.builders;

import static com.apiautomationframework.config.ConfigFactory.getConfig;
import static io.restassured.filter.log.LogDetail.ALL;

import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.output.WriterOutputStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {
	
	
	
	/*public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("BASE_URI")).
     //           setBaseUri("https://api.spotify.com").
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }*/
	
	public static RequestSpecification getRequestSpec(){
		PrintStream printStream = new PrintStream(new WriterOutputStream(new StringWriter(), StandardCharsets.UTF_8), true);
		
        return new RequestSpecBuilder()
        		.setBaseUri(getConfig().url())
                .setBasePath(Route.BASE_PATH)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter(printStream))
                .log(ALL)
                .build();
    }

}
