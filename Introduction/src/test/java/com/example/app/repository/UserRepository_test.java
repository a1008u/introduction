package com.example.app.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.User;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepository_test {
	
	private static final String ID = "a1";
	private static final String PASS = "demo";
	
	@Autowired
	UserRepository UserRepository;
	
	@Test /* テストケース1 */
	public void test_findAll() throws Exception { 
		
		// Setup
		User sampleUser = new User(ID, PASS);
		
		// Execute
		User User = UserRepository.findOne(sampleUser.getUserno());
		
		// Verification
		assertThat(User.getUserno(), is(notNullValue()));
		assertThat(User.getUserno(), is(notNullValue()));
		assertThat(User.getUserno(), is(sampleUser.getUserno()));
		assertThat(User.getUserno(), is(not(sampleUser.getEncodedPassword())));
		
	}
}
