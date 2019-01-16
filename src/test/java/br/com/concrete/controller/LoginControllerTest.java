package br.com.concrete.controller;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.concrete.dtos.LoginDto;
import br.com.concrete.dtos.UserRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test(expected = Exception.class)
	public void testValidacaoSenhaCodigoRetorno() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/login";
		URI uri = new URI(baseUrl);
		LoginDto loginDto = new LoginDto();
		HttpEntity<LoginDto> request = new HttpEntity<>(loginDto);
		this.restTemplate.postForEntity(uri, request, String.class);
	}

}
