package com.apiautomationframework.models.builders;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.filter.log.LogDetail.ALL;

public class ResponseBuilder {
	
	public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                log(ALL).
                build();
    }

}
