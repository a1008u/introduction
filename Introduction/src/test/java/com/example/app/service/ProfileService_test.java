package com.example.app.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.app.bean.ProfileBean;
import com.example.app.service.ProfileService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileService_test {
	
	@Autowired
	ProfileService ProfileService;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test /* テストケース1 */
	public void test_findAll() throws Exception { 
		
		// Execute
		List<ProfileBean> List_Profile = ProfileService.findAll();
		
		// Verification
		int expected = 25;
		assertThat(List_Profile.size(), is(expected));
		
	}
	
	
    @Test /* テストケース2 */
    public void testFindOne() throws Exception {
    	
    	// Setup
    	ProfileBean ProfileBean = createProfile("a1","", 0,"","","","");
    	
    	// Execute
    	ProfileBean actual =ProfileService.findOne(ProfileBean);
    	String expected = "AAAA1111";
    	
    	// Verification
    	assertThat(actual, notNullValue());
    	assertThat(actual.getName(), is(expected));
    }

    
    @Test /* テストケース3 */
    public void testCreate_FindOne() throws Exception {
    	
    	// Setup
    	ProfileBean expected = createProfile("xx1","test",21,"SE1","Tennnis","新橋","テストテストテスト");
    	
    	// Execute
    	ProfileService.create(expected);
    	ProfileBean actual =ProfileService.findOne(expected);
    	
    	// Verification
    	assertThat(actual, notNullValue());
    	assertThat(actual.getUserno(), is(expected.getUserno()));
    	assertThat(actual.getName(), is(expected.getName()));
    }

    @Test /* テストケース3 */
    public void testUpdate_findOne() throws Exception {
    	
    	// Setup
    	ProfileBean expected = createProfile("test1","test",21,"SE1","Tennnis","新橋","テストテストテスト");
    	
    	// Execute
    	ProfileService.update(expected);
    	
    	// Execute + Verification
    	ProfileBean actual = ProfileService.findOne(expected);
    	assertThat(actual, notNullValue());
        assertThat(actual.getName(),is("test"));
    }
    
    
    @Test /* テストケース4 */
    public void test_findOneAndSave() throws Exception {
    	
    	// Setup
    	ProfileBean expected = createProfile("a1","testS", 999,"aaaa","dddd","sss","tttts");
    	
    	// Execute
    	ProfileService.findOneAndSave(expected);
    	
    	// Execute + Verification
    	ProfileBean actual = ProfileService.findOne(expected);
    	assertThat(actual, notNullValue());
        assertThat(actual.getName(),is(expected.getName()));
        assertThat(actual.getUserno(),is(expected.getUserno()));
    }
    
    
    @Test /* テストケース5 */
    public void test_delete() throws Exception {
    	
    	this.thrown.expect(IllegalArgumentException.class);
    	
    	// Setup
    	ProfileBean expected = createProfile("b1","", 0,"","","","");
    	
    	// Execute
    	ProfileService.delete(expected);
    	
    	// Execute (実行時にエラーとなる)
    	ProfileBean actual = ProfileService.findOne(expected);
    	assertThat(actual, is(nullValue()));
    }   
    

    // テストフィクスチャ
	private ProfileBean createProfile(String Userno, 
									  String Name, 
									  Integer Age, 
									  String Department, 
									  String Club, 
									  String Dispatchlocation, 
									  String Freetext) {
		
		ProfileBean ProfileBean = new ProfileBean();
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
