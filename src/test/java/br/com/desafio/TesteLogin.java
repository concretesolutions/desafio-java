package br.com.desafio;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TesteLogin {
	
	
	@BeforeClass
	  public void setBaseUri () {

	    RestAssured.baseURI = "https://desafioconcreteadriel.herokuapp.com";
	  }

	@Test
	public void loginTest() {
		 given().
	        contentType(JSON).
	        body("{\"email\" : \"joao@silva.org\"," 
	        +"\"password\": \"hunter2\"}").
	        put("/api/login").then().statusCode(201);
	}
	
	@Test
	public void loginTestRetorno() {
		get("/api/login").then().body("data", notNullValue()).and().body("data", not(empty()));
		
	}

}
