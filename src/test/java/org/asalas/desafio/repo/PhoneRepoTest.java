package org.asalas.desafio.repo;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@DataJpaTest
public class PhoneRepoTest {
	 @Autowired
	 private TestEntityManager entityManager;
	 
	 @Autowired
	 private PhoneRepo phoneRepo;
	 
	 @Test
	 public void prfindAllRetornaAlgo() {
		 Assert.assertNotNull(phoneRepo.findAll());
	 }
}
