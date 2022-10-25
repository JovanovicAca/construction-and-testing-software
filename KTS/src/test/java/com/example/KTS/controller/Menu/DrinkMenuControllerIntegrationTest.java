package com.example.KTS.controller.Menu;

import com.example.KTS.Model.DTO.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
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
public class DrinkMenuControllerIntegrationTest {

    private final String urlBase = "http://localhost:";



    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private AuthenticationManager authenticationManager;



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
        DrinkMenuDTO[] drinkMenuDTOS = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Gazirani Sokovi Menu", drinkMenuDTOS[0].getMenuName());
    }
    @Test
    public void testValidAddDrinkMenu() throws Exception{
        //login(USERNAME, PASSWORD);
        DrinkMenuDTO dto = new DrinkMenuDTO("Novo Ime Menija");
        String json = json(dto);
        mockMvc.perform(post("/drink/menu/add")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                .andExpect(status().isOk());
    }
    //    @Test
//    public void testValidUpdateDrinkMenu() throws Exception {
//        //login(USERNAME, PASSWORD);
//        DrinkMenuDTO dto = new DrinkMenuDTO("Novo Ime Menija");
//        String json = json(dto);
//        mockMvc.perform(post("/drink/menu/update")
//                        .header(HttpHeaders.AUTHORIZATION, accessToken)
//                        .content(json).contentType(contentType))
//                .andExpect(status().isOk());
//
//    }
    @Test
    public void shouldThrowBad_RequestWhenDeletingDrinkMenuWithNameThatDoesNotExist() throws Exception {
        //login(USERNAME, PASSWORD);
        DrinkMenuDTO dto = new DrinkMenuDTO("Nepostojece Ime");
        String json = json(dto);
        mockMvc.perform(post("/drink/menu/delete")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void shouldReturnOKWhenDeletingDrinkMenuWithNameThatDoesExist() throws Exception {
        login(USERNAME, PASSWORD);
        DrinkMenuDTO dto = new DrinkMenuDTO("Cedjeni Sokovi Menu");
        String json = json(dto);
        mockMvc.perform(post("/dish/menu/delete")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                .andExpect(status().isOk());

    }
}
