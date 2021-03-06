package com.example.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.app.bean.ProfileBean;
import com.example.domain.Profile;
import com.example.app.repository.ProfileRepository;

@Service
@Transactional
@ComponentScan("bean")
public class ProfileService {
	
	@Autowired
	ProfileBean ProfileBean;
	
	@Autowired
	ProfileRepository ProfileRepository;
	

	// 【cRud】--------------------------------------------
    public List<ProfileBean> findAll() {
    	
    	List<Profile> List_Profile =  ProfileRepository.findAllOrderByName();
    	
    	List<ProfileBean> List_ProfileBean = new ArrayList<>();
    	
    	List_Profile.stream()
					.forEach(Profile -> {
						ProfileBean ProfileBean = new ProfileBean();
						BeanUtils.copyProperties(Profile, ProfileBean);
						List_ProfileBean.add(ProfileBean);
					});
    	
        return List_ProfileBean;
    }

    public ProfileBean findOne(ProfileBean ProfileBean) {
    	Profile Profile = ProfileRepository.findByUserno(ProfileBean.getUserno());
    	BeanUtils.copyProperties(Profile, ProfileBean);
        return ProfileBean;
    }

    // ページング
    public Page<Profile> findAll(Pageable pageable) {
        return ProfileRepository.findAllOrderByName(pageable);
    }
    
    // 【Crud】--------------------------------------------
    public Profile create(ProfileBean ProfileBean) {
    	Profile Profile = copyProfile(ProfileBean);
        return ProfileRepository.save(Profile);
    }
    
    // 【crUd】--------------------------------------------
    public Profile update(ProfileBean ProfileBean) {
    	Profile Profile = copyProfile(ProfileBean);
        return ProfileRepository.save(Profile);
    }
    
    public Profile findOneAndSave(ProfileBean ProfileBean) {
    	Profile Profile = ProfileRepository.findByUserno(ProfileBean.getUserno());
    	BeanUtils.copyProperties(ProfileBean, Profile);
        return ProfileRepository.save(Profile);
    }
    
    // 【cruD】--------------------------------------------
    // findOne→delete
    public void delete(ProfileBean ProfileBean) {
    	Profile Profile = ProfileRepository.findByUserno(ProfileBean.getUserno());
    	ProfileRepository.delete(Profile);
    }
    
    // Common Pattern
	private Profile copyProfile(ProfileBean ProfileBean) {
		Profile Profile = new Profile();
    	BeanUtils.copyProperties(ProfileBean, Profile);
		return Profile;
	}

}
