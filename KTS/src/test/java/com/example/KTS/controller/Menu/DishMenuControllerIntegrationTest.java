package com.example.KTS.controller.Menu;

import com.example.KTS.Model.DTO.DishMenuDTO;
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
public class DishMenuControllerIntegrationTest {

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
    public void testGetAllDishMenu(){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<Object> request = new HttpEntity<Object>(headers);
        URI uri = UriComponentsBuilder.fromHttpUrl(urlBase + this.port + "/dish/menu/all").build().toUri();

        ResponseEntity<DishMenuDTO[]> responseEntity = restTemplate
                .exchange(uri, HttpMethod.GET, request, DishMenuDTO[].class);
        DishMenuDTO[] dishDTOS = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testInvalidUpdateDishMenu() throws Exception {
        login(USERNAME, PASSWORD);
        DishMenuDTO dto = new DishMenuDTO("Nema imena");
        String json = json(dto);
        mockMvc.perform(post("/dish/menu/update")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                        .andExpect(status().isBadRequest());

    }
    @Test
    public void testValidUpdateDishMenu() throws Exception {
        login(USERNAME, PASSWORD);
        DishMenuDTO dto = new DishMenuDTO("Dorucak");
        String json = json(dto);
        mockMvc.perform(post("/dish/menu/update")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                        .andExpect(status().isBadRequest());

    }
    @Test
    public void testInvalidAddDishMenu() throws Exception {
        login(USERNAME, PASSWORD);
        DishMenuDTO dto = new DishMenuDTO("Dorucak");
        String json = json(dto);
        mockMvc.perform(post("/dish/menu/add")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                        .andExpect(status().isBadRequest());

    }
    @Test
    public void shouldReturnOKWhenAddingDishMenuWithNameThatDoesNotExist() throws Exception {
        login(USERNAME, PASSWORD);
        DishMenuDTO dto = new DishMenuDTO("Dorucakcic");
        String json = json(dto);
        mockMvc.perform(post("/dish/addDish")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                .andExpect(status().isOk());

    }
    @Test
    public void shouldReturnBadRequestWhenDeletingDishWithNameThatDoesNotExist() throws Exception {
        login(USERNAME, PASSWORD);
        String name = "Sarmica";
        String json = json(name);
        mockMvc.perform(MockMvcRequestBuilders.delete("/dish/deleteDish",json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest());
    }

}
