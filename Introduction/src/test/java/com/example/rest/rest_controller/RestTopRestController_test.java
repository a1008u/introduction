package com.example.rest.rest_controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.app.bean.ProfileBean;
import com.example.rest.rest_bean.*;
import com.example.rest.rest_service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.rest.rest_controller.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JacksonAutoConfiguration.class)
@AutoConfigureMockMvc
public class RestTopRestController_test {
	
	// private static final String ID = "a1";
	
    private MockMvc mvc;
    
    @Rule
    public final MockitoRule rule = MockitoJUnit.rule();
    
    @InjectMocks
    private TopRestController TopRestController;
    
	@MockBean
    private RestProfileService RestProfileService;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Before
    public void before() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(TopRestController).build();
    }
	
	/* テストケース1(Stringでの結果評価) 
	 * 
	 * テスト方針：SpringBootTest上でMockkitoを利用してRestControllerのテスト実施
	 */
	@Test 
	public void test_findOne() throws Exception { 
		// Set UP
		String result_typeString = "{" + "\"userno\":\"a1\",\"name\":\"AAAA1111\",\"age\":30,\"department\":\"NE1\",\"club\":\"\",\"dispatchlocation\":\"新橋\",\"freetext\":\"Springの修行中\"" + "}";
		RestProfileBean RestProfileBean = makeRestprofilebean("a1");
    	RestProfileBean expected = createProfile("a1","AAAA1111", 30,"NE1","","新橋","Springの修行中");
		
    	// Serviceを実行した際の動作を明記する
		when(this.RestProfileService.findOne(RestProfileBean)).thenReturn(expected);
		
		// Execute + Verification
        mvc.perform(get("/api/Top/a1"))
           .andExpect(status().isOk())
           .andExpect(content().string(result_typeString));
	}
	
	@Test /* テストケース1(jsonでの結果評価) */
	public void test_findOne2() throws Exception { 
		// Set UP
		RestProfileBean RestProfileBean = makeRestprofilebean("a1");
    	RestProfileBean result = new RestProfileBean("a1","AAAA1111", 30,"NE1","","新橋","Springの修行中");
		
    	// Serviceを実行した際の動作を明記する
		when(this.RestProfileService.findOne(RestProfileBean)).thenReturn(result);
		
		//  Execute + Verification
		RestProfileBean expected = createProfile("a1","AAAA1111", 30,"NE1","","新橋","Springの修行中");
        mvc.perform(get("/api/Top/a1"))
           .andExpect(status().isOk())
           .andExpect(content().json(mapper.writeValueAsString(expected)));
	}
	
	// テストフィクスチャ------------------------------------------------------------	
	private RestProfileBean makeRestprofilebean(String Userno) {
		RestProfileBean RestProfileBean= new RestProfileBean();
    	RestProfileBean.setUserno(Userno);
		return RestProfileBean;
	}
	
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
