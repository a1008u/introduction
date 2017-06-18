package com.example.rest.rest_service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.rest.rest_bean.*;
import com.example.domain.Profile;
import com.example.rest.rest_repository.*;
@Service
@Transactional
@ComponentScan("bean")
public class RestProfileService {
	
	@Autowired
	RestProfileBean RestProfileBean;
	
	@Autowired
	RestProfileRepository RestProfileRepository;
	

	// 【cRud】--------------------------------------------
    public List<RestProfileBean> findAll() {
    	
    	List<Profile> List_Profile =  RestProfileRepository.findAllOrderByName();
    	
    	List<RestProfileBean> List_RestProfileBean = new ArrayList<>();
    	
    	List_Profile.stream()
					.forEach(Profile -> {
						RestProfileBean RestProfileBean = new RestProfileBean();
						BeanUtils.copyProperties(Profile, RestProfileBean);
						List_RestProfileBean.add(RestProfileBean);
					});
    	
        return List_RestProfileBean;
    }

    public RestProfileBean findOne(RestProfileBean RestProfileBean) {
    	Profile Profile = RestProfileRepository.findByUserno(RestProfileBean.getUserno());
    	BeanUtils.copyProperties(Profile, RestProfileBean);
        return RestProfileBean;
    }

    // ページング
    public Page<Profile> findAll(Pageable pageable) {
        return RestProfileRepository.findAllOrderByName(pageable);
    }
    
    // 【Crud】--------------------------------------------
    public Profile create(RestProfileBean RestProfileBean) {
    	Profile Profile = copyProfile(RestProfileBean);
        return RestProfileRepository.save(Profile);
    }
    
    // 【crUd】--------------------------------------------
    public Profile update(RestProfileBean RestProfileBean) {
    	Profile Profile = copyProfile(RestProfileBean);
        return RestProfileRepository.save(Profile);
    }
    
    public Profile findOneAndSave(RestProfileBean RestProfileBean) {
    	String Userno = RestProfileBean.getUserno();
    	Profile Profile = RestProfileRepository.findByUserno(Userno);
    	BeanUtils.copyProperties(RestProfileBean, Profile);
        return RestProfileRepository.save(Profile);
    }
    
    // 【cruD】--------------------------------------------
    // findOne→delete
    public void delete(RestProfileBean RestProfileBean) {
    	String Userno = RestProfileBean.getUserno();
    	RestProfileRepository.deleteByUserno(Userno);
    }
    
    // Common Pattern
	private Profile copyProfile(RestProfileBean RestProfileBean) {
		Profile Profile = new Profile();
    	BeanUtils.copyProperties(RestProfileBean, Profile);
		return Profile;
	}

}
