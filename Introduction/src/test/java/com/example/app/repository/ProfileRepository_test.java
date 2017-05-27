package com.example.app.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.bean.ProfileBean;
import com.example.app.service.ProfileService;
import com.example.domain.Profile;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileRepository_test {
	
	private static final String ID = "a1";
	
	@Autowired
	ProfileRepository ProfileRepository;
	
	@Test /* テストケース1 */
	public void test_findAll() throws Exception { 
		
		// Execute
		List<Profile> List_Profile = ProfileRepository.findAll();
		
		// Verification
		int expected_Size = 25;
		assertThat(List_Profile.size(), is(expected_Size));
		
	}
	
    @Test /* テストケース2 */
    public void test_FindOne() throws Exception {
    	
    	// Execute
    	Profile Profile = ProfileRepository.findByUserno(ID);
    	
    	// Verification
    	assertThat(Profile, notNullValue());
    	assertThat(Profile.getUserno(), is(ID));
    }
    
    @Transactional
    @Test /* テストケース3 */
    public void test_delete() throws Exception {
    	
    	// Setup
    	
    	// Execute
    	Profile Profile = ProfileRepository.findByUserno(ID);
    	ProfileRepository.delete(Profile);
    	List<Profile> List_Profile = ProfileRepository.findAll();
    	
    	// Verification
		int expected_Size = 25;
		assertThat(List_Profile.size(), is(not(expected_Size)));
    }   
}
