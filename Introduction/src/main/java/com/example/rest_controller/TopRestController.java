package com.example.rest_controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.ProfileBean;
import com.example.service.ProfileService;

@RestController
@RequestMapping("/api/Top")
public class TopRestController {

	@Autowired
	ProfileService ProfileService;
	
	// Supplier<ProfileBean> su = () -> new ProfileBean();
	
	ProfileBean ProfileBean = new ProfileBean();
	
	// Restのテスト
	@RequestMapping(value = "/getZoneDateTime", method = RequestMethod.GET)
	@ResponseBody
	public String[] getTestData() {
		
		ZonedDateTime now = ZonedDateTime.now();
		String nowString = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
	 
	    String[] datas = {"===ajax_btnを押下した時刻：", nowString+"==="};
	    
	    return datas;
	}
	
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ProfileBean getCustomer(@PathVariable String id) {
    	ProfileBean ProfileBean = new ProfileBean();
    	ProfileBean.setUserno(id);
    	ProfileBean  = ProfileService.findOne(ProfileBean);
        return ProfileBean;
    }
    
	
}
