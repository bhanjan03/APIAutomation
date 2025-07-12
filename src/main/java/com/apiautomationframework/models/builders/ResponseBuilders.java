package com.apiautomationframework.models.builders;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import static java.net.HttpURLConnection.HTTP_OK;


public final class ResponseBuilders {
	
	public static ResponseSpecification createResponseSpecification() {
	    return new ResponseSpecBuilder().
	      expectStatusCode(HTTP_OK).
	      expectContentType(ContentType.JSON).build();
	  }

}
