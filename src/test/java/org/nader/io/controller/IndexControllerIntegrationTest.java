package org.nader.io.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nader.io.SpringBootRssReaderVApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRssReaderVApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerIntegrationTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void testIndex() throws Exception {
	
		ResponseEntity<String> response = testRestTemplate.getForEntity("/", String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}
}
