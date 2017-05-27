package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.User;


/**
 * Data JPA tests for {@link User}.
 *
 * @author au
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class User_test {
	
	private static final String ID = "a1";
	private static final String PASS = "demo";
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test /* テストケース1 */
	public void createWhenUsernoIsNullShouldThrowException() throws Exception {
		/*
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("ID must not be empty");
		*/
		new User(null, PASS);
	}
	
	@Test /* テストケース2 */
	public void createWhenUsernoIsEmptyShouldThrowException() throws Exception {
		/*
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("ID must not be empty");
		*/
		new User("", PASS);
	}

	@Test /* テストケース3 */
	public void createWhenPassIsNullShouldThrowException() throws Exception {
		/*
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("PASS must not be null");
		*/
		new User(ID, null);
	}

	@Test /* テストケース4 */
	public void createWhenPassIsEmptyShouldThrowException() throws Exception {
		/*
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("PASS must not be null");
		*/
		new User(ID, "");
	}
}
