package com.apiautomationframework.models.builders;

import io.restassured.response.Response;
import static com.apiautomationframework.models.builders.RequestBuilder.*;
import static com.apiautomationframework.models.builders.ResponseBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {
	
	
	
	/*public static Response post(String path, String payload){
        return given(getRequestSpec()).
                body(payload).
        when().post(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }*/
	
	public static Response post(String path, Object requestBody){
        return given(getRequestSpec()).
                body(requestBody).
        when().post(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }
	
	public static Response post(String path, String token, Object requestBody){
        return given(getRequestSpec()).header("Authorization",token).
                body(requestBody).
        when().post(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    

    public static Response get(String path){
        return given(getRequestSpec()).
        when().get(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }
    
    public static Response get(String path, String token){
        return given(getRequestSpec()).header("Authorization",token).
        when().get(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response update(String path, String token, Object requestBody){
        return given(getRequestSpec()).
                body(requestBody).
        when().put(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

}
