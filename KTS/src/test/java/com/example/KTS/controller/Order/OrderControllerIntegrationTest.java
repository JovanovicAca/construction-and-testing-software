package com.example.KTS.controller.Order;

import com.example.KTS.Model.DTO.*;
import com.example.KTS.Model.Enums.ORDER_STATUS;
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
public class OrderControllerIntegrationTest {

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
    public void testInvalidAddOrder() throws Exception {
        login(USERNAME, PASSWORD);
        OrderDTO dto = new OrderDTO(ORDER_STATUS.PENDING,100.0,"10","Nema");
        String json = json(dto);
        mockMvc.perform(post("/order/add")
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(json).contentType(contentType))
                        .andExpect(status().isBadRequest());

    }
    //FORBIDEN ???
//    @Test
//    public void shouldReturnBadRequestWhenNoTableByOrderId() throws Exception {
//        login(USERNAME, PASSWORD);
//        String dto = "999";
//        String json = json(dto);
//        mockMvc.perform(post("/order/findByTable")
//                        .header(HttpHeaders.AUTHORIZATION, accessToken)
//                        .content(json).contentType(contentType))
//                        .andExpect(status().isBadRequest());
//
//    }

//    @Test
//    public void shouldReturnOkWhenUpdatingNewOrder() throws Exception {
//        login(USERNAME, PASSWORD);
//        OrderDTO dto = new OrderDTO(ORDER_STATUS.PENDING,100.0,"10","Nema");
//        String json = json(dto);
//        mockMvc.perform(post("/order/update/orderItem")
//                        .header(HttpHeaders.AUTHORIZATION, accessToken)
//                        .content(json).contentType(contentType))
//                .andExpect(status().isOk());
//
//    }
}
