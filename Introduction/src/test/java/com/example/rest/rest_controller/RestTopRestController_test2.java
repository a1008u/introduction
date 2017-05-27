package com.example.rest.rest_controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;

import com.example.rest.rest_bean.*;
import com.example.rest.rest_service.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc  // @WebMvcTest(TopRestController.class)としてコントローラクラスを指定することもできる
public class RestTopRestController_test2 {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
    private RestProfileService RestProfileService;
	
	@Autowired
	private ObjectMapper mapper;
	
	/* テストケース1(Stringでの結果評価) 
	 * 
	 * テスト方針：SpringBoot(ver1.4xxx以上)で利用する場合のやり方を紹介
	 * 1.SpringSecurityを利用しているため、テスト用ログインするUserを作成する
	 * 　WithSecurityContextFactoryを実装し、アノテーションとして利用する
	 * 2.@AutoConfigureMockMvc(@WebMvcTest)と@MockBean(利用するService)を指定する
	 * 3.テストはMockMvcを利用する(JSONで取得する場合はObjectMapperを利用する)
	 */
	@Test
	@WithMockCustomUser(username = "a1", password= "demo")
	public void test_findOne() throws Exception { 
		// Set UP
		String result_typeString = "{" + "\"userno\":\"a1\",\"name\":\"AAAA1111\",\"age\":30,\"department\":\"NE1\",\"club\":\"\",\"dispatchlocation\":\"新橋\",\"freetext\":\"Springの修行中\"" + "}";
		RestProfileBean RestProfileBean = makeRestprofilebean("a1");
    	RestProfileBean expected = createProfile("a1","AAAA1111", 30,"NE1","","新橋","Springの修行中");
		
    	// Serviceを実行した際の動作を明記する
		given(this.RestProfileService.findOne(RestProfileBean)).willReturn(expected);
		
		// Execute + Verification
        mvc.perform(get("/api/Top/a1").accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().string(result_typeString));
	}
	
	@Test /* テストケース2(jsonでの結果評価) */
	@WithMockCustomUser(username = "a1", password= "demo")
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
