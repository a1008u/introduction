package com.example.rest.rest_controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.rest_bean.*;
import com.example.rest.rest_service.RestProfileService;

@RestController
@RequestMapping("/api/Top")
public class TopRestController {

	@Autowired
	RestProfileService RestProfileService;
	
	// Supplier<ProfileBean> su = () -> new ProfileBean();
	
	RestProfileBean RestProfileBean = new RestProfileBean();
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    RestProfileBean getCustomer(@PathVariable String id) {
    	
    	Consumer<ZonedDateTime> Utilization_time = 
   			 now -> { String nowString = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
   			 		  System.out.println("===ajaxを押下した時刻：" + nowString + "===");};
    	
		ZonedDateTime now = ZonedDateTime.now();
		Utilization_time.accept(now);
    	
    	RestProfileBean RestProfileBean = new RestProfileBean();
    	RestProfileBean.setUserno(id);
    	RestProfileBean  = RestProfileService.findOne(RestProfileBean);
    	
        return RestProfileBean;
    }
    
	
}
