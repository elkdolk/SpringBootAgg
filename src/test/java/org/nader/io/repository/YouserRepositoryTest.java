package org.nader.io.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nader.io.entities.Youser;
import org.nader.io.repositories.YouserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class YouserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private YouserRepository youserRepository;
	
	@Test
	public void testYouserRepository() throws Exception {
		Youser userOne = generateUser();
		Youser savedINDB = entityManager.persist(userOne);
		Optional<Youser> getFromDB = youserRepository.findById(savedINDB.getId());
		assertThat(getFromDB.get()).isEqualTo(savedINDB);
	}
	
	@Test
	public void testFindByName() throws Exception {
		Youser userOne = generateUser();
		Youser savedINDB = entityManager.persist(userOne);
		Youser foundByName = youserRepository.findByName("nader");
		assertThat(foundByName.getName()).isEqualTo(savedINDB.getName());
	}
			
	private Youser generateUser(){
		Youser userOne = new Youser();
		userOne.setName("nader");
		userOne.setPassword("12345");
		userOne.setEnabled(true);
		userOne.setEmail("nader.mirza@gmail.com");
		return userOne;
	}
}
