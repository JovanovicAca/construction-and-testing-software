package com.example.KTS.controller.Ingredient;

import com.example.KTS.Model.DTO.IngredientDTO;
import com.example.KTS.Model.DTO.JwtAuthenticationRequest;
import com.example.KTS.Model.DTO.UserTokenState;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

import static com.example.KTS.TestUtil.json;
import static com.example.KTS.constants.ClientConstants.PASSWORD;
import static com.example.KTS.constants.ClientConstants.USERNAME;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public class IngredientControllerIntegrationTest {


    private final String urlBase = "http://localhost:";



    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    private String accessToken;

     public void login(String username, String password) {
        ResponseEntity<UserTokenState> responseEntity =
         restTemplate.postForEntity("/auth/login", new
                 JwtAuthenticationRequest(username,password), UserTokenState.class);
            accessToken = "Bearer " + responseEntity.getBody().getAccessToken(); }

    @BeforeAll
    public void setup(){
        login(USERNAME, PASSWORD);
    }

    @Test
    public void testValidUpdateIngredient() throws Exception {
        login(USERNAME, PASSWORD);
        IngredientDTO dto = new IngredientDTO("kikiriki",false,100.0);
        String json = json(dto);
        mockMvc.perform(post("/ingredient/update")
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .content(json).contentType(contentType))
                .andExpect(status().isOk());

    }
    @Test
    public void testInvalidUpdateIngredient() throws Exception {
        login(USERNAME, PASSWORD);
        IngredientDTO dto = new IngredientDTO("kikiriki",false,-100.0);
        String json = json(dto);
        mockMvc.perform(post("/ingredient/update")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                        .andExpect(status().isBadRequest());

    }
    @Test
    public void shouldThrowBad_RequestWhenAddingIngredientWithNameThatAlreadyExists() throws Exception {
        login(USERNAME, PASSWORD);
        IngredientDTO dto = new IngredientDTO("kikiriki",false,100.0);
        String json = json(dto);
        mockMvc.perform(post("/ingredient/add")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void shouldReturnOKWhenAddingIngredientWithNameThatDoesNotExist() throws Exception {
        login(USERNAME, PASSWORD);
        IngredientDTO dto = new IngredientDTO("orah",false,100.0);
        String json = json(dto);
        mockMvc.perform(post("/ingredient/add")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                .andExpect(status().isOk());

    }
}
