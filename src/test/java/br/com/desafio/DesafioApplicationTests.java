package br.com.desafio;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DesafioApplicationTests {
	
	
	/*@BeforeClass
	  public void setLogin () {
	  final String uri = "https://desafioconcreteadriel.herokuapp.com/api/";
	  RequestSpecification basicAuth = RestAssured.given().auth().preemptive().basic("joao@silva.org", "hunter2");
	  Response response = basicAuth.accept(ContentType.JSON).get(uri);
	  
	  }
	
	
	@Test
	public void isHerokuOnline() {
		given().when().get("https://desafioconcreteadriel.herokuapp.com/api/");
	}

	@Test
	public void loginTest() {
		 given().
	        contentType(JSON).
	        body("{\"email\" : \"joao@silva.org\"," 
	        +"\"password\": \"hunter2\"}").
	        put("/api/login").then().statusCode(201);
	}*/
	
	

}
