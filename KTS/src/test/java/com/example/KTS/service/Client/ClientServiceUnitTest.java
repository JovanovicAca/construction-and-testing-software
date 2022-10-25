package com.example.KTS.service.Client;

import com.example.KTS.Model.Staff.Client;
import com.example.KTS.Repo.ClientRepo;
import com.example.KTS.Service.ClientService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.example.KTS.constants.ClientConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ClientServiceUnitTest {

	@Autowired
	private ClientService clientService;

	@MockBean
	private ClientRepo clientRepo;

	@BeforeAll
	public void setup() {
		List<Client> clients = new ArrayList<Client>();
		clients.add(new Client(NAME, SURNAME, PAYCHECK, USERNAME, PASSWORD));
		Client c = new Client(NAME, SURNAME, PAYCHECK, USERNAME, PASSWORD);

	}

	@Test
	public void findByUsername(){
		Client c = new Client(NAME, SURNAME, PAYCHECK, USERNAME, PASSWORD);
		when(clientRepo.findByUsername(USERNAME)).thenReturn(c);
		Client found = clientRepo.findByUsername(USERNAME);
		//verify(clientRepo, times(1)).findByUsername(USERNAME);
		assertEquals(USERNAME, found.getUsername());
	}

	@Test
	public void findByUsernameExpectedNull(){
		//Client c = new Client(NAME, SURNAME, PAYCHECK, USERNAME, PASSWORD);
		when(clientRepo.findByUsername(USERNAME)).thenReturn(null);
		Client found = clientRepo.findByUsername(USERNAME);
		//verify(clientRepo, times(1)).findByUsername(USERNAME);
		assertEquals(null, found);
	}

	@Test
	public void findOneByEmail(){
		Client found = clientRepo.findByUsername(USERNAME);
		verify(clientRepo, times(1)).findByUsername(USERNAME);
		assertEquals(USERNAME, found.getUsername());
	}

	@Test
	public void add(){
		Client c = new Client(NAME, SURNAME, PAYCHECK, USERNAME, PASSWORD);
		//Client created = clientService.add

	}


}
