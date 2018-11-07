package org.asalas.desafio;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DesafioJavaApplicationTests {

	@BeforeClass
	public void setLogin () {

	  //  RestAssured.basic("asalas@test.org", "asalas@test.org");
	}

	@Test
	public void loginTest() {
		/* given().
	        contentType(JSON).
	        body("{\"email\" : \"asalas@test.org\"," 
	        +"\"password\": \"clave1234\"}").
	        put("/api/login").then().statusCode(201);*/
	}

}

