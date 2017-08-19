package com.example.rest.rest_service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.rest.rest_bean.RestProfileBean;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RestProfileService_test {
	
	@Autowired
	RestProfileService RestProfileService;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
    @Test /* テストケース1 */
    public void testFindOne() throws Exception {
    	
    	// Setup
    	RestProfileBean RestProfileBean = createProfile("a1","", 0,"","","","");
    	
    	// Execute
    	RestProfileBean actual = RestProfileService.findOne(RestProfileBean);
    	String expected = "AAAA1111";
    	
    	// Verification
    	assertThat(actual, notNullValue());
    	assertThat(actual.getName(), is(expected));
    }
    
    // テストフィクスチャ
 	private RestProfileBean createProfile(String Userno, 
 									  String Name, 
 									  Integer Age, 
 									  String Department, 
 									  String Club, 
 									  String Dispatchlocation, 
 									  String Freetext) {
 		
 		RestProfileBean ProfileBean = new RestProfileBean();
     	ProfileBean.setUserno(Userno);
     	ProfileBean.setName(Name);
     	ProfileBean.setAge(Age);
     	ProfileBean.setDepartment(Department);
     	ProfileBean.setClub(Club);
     	ProfileBean.setDispatchlocation(Dispatchlocation);
     	ProfileBean.setFreetext(Freetext);
     	
 		return ProfileBean;
 	}
}
