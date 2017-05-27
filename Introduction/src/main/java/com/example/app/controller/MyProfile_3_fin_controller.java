package com.example.app.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.app.bean.ProfileBean;
import com.example.app.controller.form.ProfileForm;
import com.example.app.service.ProfileService;


/*【コントローラ：登録確認→完了用】
 * 処理順序
 * 1：セッションの初期化 メソッド名： clearSession
 * 2：項目の登録 メソッド名： updateForm
 * 
 */

@Controller
@RequestMapping("MyProfile_fin")
@SessionAttributes(value = "MyProfileForm")
@ComponentScan("bean")
public class MyProfile_3_fin_controller {

	@Autowired
	ProfileService ProfileService;
	
	@Autowired
	ProfileBean ProfileBean;
	
	/**
	 * 1.セッションを削除
	 * @param SessionStatus
	 * 
	 */
	@GetMapping
	public String clearSession(SessionStatus sessionStatus) {
		
	    sessionStatus.setComplete(); 
	    return "myprofile/MyProfile_fin";
	}
	
	/**
	 * 2.編集情報を保存する
	 * @param Model
	 * @param ProfileForm @ModelAttribute("MyProfileForm")
	 * 
	 */
	@PostMapping
    String updateForm(Model Mav, 
    				  @ModelAttribute("MyProfileForm")ProfileForm MyProfileForm) {
		
		BeanUtils.copyProperties(MyProfileForm, ProfileBean);
		ProfileService.findOneAndSave(ProfileBean);
        
        return "redirect:/MyProfile_fin";
    }
}
