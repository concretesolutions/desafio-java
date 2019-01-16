package br.com.concrete.controller;

import static org.assertj.core.api.Assertions.assertThat;
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

import br.com.concrete.dtos.UserRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testaFiltro() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/user/100", String.class))
				.contains("{\"message\"");
	}
	
	@Test
	public void testValidacaoNome() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/register";
		URI uri = new URI(baseUrl);
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setEmail("teste@teste.com");
		userRequestDto.setName("j");
		userRequestDto.setPassword("rffr!2@A");
		HttpEntity<UserRequestDto> request = new HttpEntity<>(userRequestDto);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		assertEquals("{\"message\":\"O nome deve conter entre 5 e 200 caracteres\"}", result.getBody());
	}
	
	@Test
	public void testCadastro() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/register";
		URI uri = new URI(baseUrl);
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setEmail("winston@teste.com");
		userRequestDto.setName("Winston Churchill");
		userRequestDto.setPassword("rffrR25A");
		HttpEntity<UserRequestDto> request = new HttpEntity<>(userRequestDto);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		assertThat(result.getBody()).contains("Winston Churchill");
	}
	
	
	@Test
	public void testCadastroCodigoRetorno() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/register";
		URI uri = new URI(baseUrl);
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setEmail("franklin@teste.com");
		userRequestDto.setName("Franklin Roosevelt");
		userRequestDto.setPassword("rffrR25A");
		HttpEntity<UserRequestDto> request = new HttpEntity<>(userRequestDto);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}
	
	@Test
	public void testValidacaoNomeCodigoRetorno() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/register";
		URI uri = new URI(baseUrl);
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setEmail("teste7@teste.com");
		userRequestDto.setName("j");
		userRequestDto.setPassword("rffr!2@A");
		HttpEntity<UserRequestDto> request = new HttpEntity<>(userRequestDto);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
	
	@Test
	public void testCadastroConflito() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/register";
		URI uri = new URI(baseUrl);
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setEmail("winston2@teste.com");
		userRequestDto.setName("Winston Churchill");
		userRequestDto.setPassword("rffrR25A");
		HttpEntity<UserRequestDto> request = new HttpEntity<>(userRequestDto);
		this.restTemplate.postForEntity(uri, request, String.class);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
	}

	@Test
	public void testValidacaoSenha() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/register";
		URI uri = new URI(baseUrl);
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setEmail("teste_@teste.com");
		userRequestDto.setName("Teste teste");
		userRequestDto.setPassword("rffr!2@");
		HttpEntity<UserRequestDto> request = new HttpEntity<>(userRequestDto);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		assertEquals("{\"message\":\"A senha deve conter ao menos uma letra mínuscula, uma maiúscula,  um número e ter no mínimo 5 caracteres\"}", result.getBody());
	}
	
	@Test
	public void testValidacaoEmailCodigoRetorno() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/register";
		URI uri = new URI(baseUrl);
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setEmail("teste.com");
		userRequestDto.setName("Teste teste");
		userRequestDto.setPassword("rffr!2@");
		HttpEntity<UserRequestDto> request = new HttpEntity<>(userRequestDto);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
	
	@Test
	public void testValidacaoEmail() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/register";
		URI uri = new URI(baseUrl);
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setEmail("teste.com");
		userRequestDto.setName("Teste teste");
		userRequestDto.setPassword("rffr!W2@");
		HttpEntity<UserRequestDto> request = new HttpEntity<>(userRequestDto);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		assertEquals("{\"message\":\"O e-mail é invalido\"}", result.getBody());
	}
	
	@Test
	public void testValidacaoSenhaCodigoRetorno() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/register";
		URI uri = new URI(baseUrl);
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setEmail("testet@teste.com");
		userRequestDto.setName("Teste teste");
		userRequestDto.setPassword("rffr!2@");
		HttpEntity<UserRequestDto> request = new HttpEntity<>(userRequestDto);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
}
