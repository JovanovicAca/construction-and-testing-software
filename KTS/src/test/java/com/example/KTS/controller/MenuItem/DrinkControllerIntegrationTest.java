package com.example.KTS.controller.MenuItem;

import com.example.KTS.Model.DTO.*;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
public class DrinkControllerIntegrationTest {

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
    public void testGetAllDrinkMenu(){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> request = new HttpEntity<Object>(headers);
        URI uri = UriComponentsBuilder.fromHttpUrl(urlBase + this.port + "/drink/menu/all").build().toUri();

        ResponseEntity<DrinkMenuDTO[]> responseEntity = restTemplate
                .exchange(uri, HttpMethod.GET, request, DrinkMenuDTO[].class);
        DrinkMenuDTO[] dishDTOS = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testInvalidUpdateDrinkMenu() throws Exception {
        login(USERNAME, PASSWORD);
        DishMenuDTO dto = new DishMenuDTO("Nema imena");
        String json = json(dto);
        mockMvc.perform(post("/drink/menu/update")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void testValidUpdateDrinkMenu() throws Exception {
        login(USERNAME, PASSWORD);
        DishMenuDTO dto = new DishMenuDTO("Gazirani Sokovi");
        String json = json(dto);
        mockMvc.perform(post("/drink/menu/update")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void testInvalidAddDrinkMenu() throws Exception {
        login(USERNAME, PASSWORD);
        DishMenuDTO dto = new DishMenuDTO("Gazirani Sokovi");
        String json = json(dto);
        mockMvc.perform(post("/drink/menu/add")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void shouldReturnOkWhenAddingDrinkMenuWithNameThatDoesNotExist() throws Exception {
        login(USERNAME, PASSWORD);
        DishMenuDTO dto = new DishMenuDTO("Cedjeni Sokovi");
        String json = json(dto);
        mockMvc.perform(post("/drink/menu/add")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                        .andExpect(status().isBadRequest());

    }
    @Test
    public void shouldReturnMethodNotAllowedWhenDeletingDrinkWithNameThatDoesExist() throws Exception {
        login(USERNAME, PASSWORD);
        String name = "Koka Kola";
        String json = json(name);
        mockMvc.perform(MockMvcRequestBuilders.delete("/drink/deleteDrink")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }
}
