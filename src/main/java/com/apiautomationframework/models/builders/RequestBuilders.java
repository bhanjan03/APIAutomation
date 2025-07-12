package com.apiautomationframework.models.builders;

import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.output.WriterOutputStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;

import static com.apiautomationframework.config.ConfigFactory.getConfig;
import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;

public final class RequestBuilders {
	 /**
	   * RequestSpecification is an Interface and RequestSpecBuilder is a class
	   *
	   * @return
	   */
	  public static RequestSpecification createRequestSpecification() {
	    PrintStream printStream = new PrintStream(new WriterOutputStream(new StringWriter(), StandardCharsets.UTF_8), true);

	    return new RequestSpecBuilder()
	      .setBaseUri(getConfig().base_uri())
	      .addFilter(new RequestLoggingFilter(printStream))
	      .log(ALL)
	      .build();
	  }

}
