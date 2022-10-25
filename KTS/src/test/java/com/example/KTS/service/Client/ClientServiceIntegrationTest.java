package com.example.KTS.service.Client;

import com.example.KTS.Model.Staff.Client;
import com.example.KTS.Repo.ClientRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.KTS.constants.ClientConstants.USERNAME;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ClientServiceIntegrationTest {

	@Autowired
	private ClientRepo clientRepo;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findByUsername(){
		Client found = clientRepo.findByUsername(USERNAME);
		assertEquals(USERNAME, found.getUsername());
	}

	@Test // Daj fensi imena metoda
	public void findByUsernameExpectedNull(){
		Client found = clientRepo.findByUsername("USERNAME");
		assertEquals(null, found);
	}

}
