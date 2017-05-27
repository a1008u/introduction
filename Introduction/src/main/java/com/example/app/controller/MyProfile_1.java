package com.example.app.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.app.service.LoginUserDetails;
import com.example.app.service.ProfileService;
import com.example.app.bean.ProfileBean;
import com.example.app.controller.form.ProfileForm;

/*【コントローラ：Top画面遷移用】
 * 処理順序
 * 1：ProfileFormの初期化 メソッド名：setUpMyProfileForm
 * 2-1：個人情報の編集　メソッド名：MyEditForm
 * 2-2：他のList情報の編集　メソッド名：ListEditForm
 * */

@Controller
//マッピングするURLの接頭辞
@RequestMapping("MyProfile")
@SessionAttributes(value = {"MyProfileForm","MyProfileBean"})
@ComponentScan("bean")
public class MyProfile_1 {
	
	@Autowired
	ProfileService ProfileService;
	
	@Autowired
	ProfileBean ProfileBean;

	/**
	 * 1.リクエスト時に必ず呼び出す(Formの初期化)-------------------
	 * ＊セッションにMyProfileFormを格納する
	 */
    @ModelAttribute(value ="MyProfileForm")
    ProfileForm setUpMyProfileForm() {
        System.out.println("create MyProfileForm");
        return new ProfileForm();
    }
	
    /**
	 * 2-1----------------------------------------------------
	 * ログインユーザ(自分)の情報をFormに格納し、MyProfile編集ページへ
	 * 
	 * @param Model
	 * @param ProfieForm ＊セッションに格納しているFormを取得
	 * @param LoginUserDetails　@AuthenticationPrincipal 
	 * @param ProfileBean @ModelAttribute("MyProfileBean")
	 * 
	 */
	@GetMapping
    String MyEditForm(Model model, 
    		    	  ProfileForm MyProfileForm,
    		    	  @AuthenticationPrincipal LoginUserDetails userDetails,
    		    	  @ModelAttribute("MyProfileBean")ProfileBean MyProfileBean) {
		
		BeanUtils.copyProperties(MyProfileBean, MyProfileForm);
		model.addAttribute("MyProfileForm", MyProfileForm);
		
        return "myprofile/MyProfile";
    }
	
    /**
	 * 2-2----------------------------------------------------
	 * ProfileListからUserNoを取得し、Profile編集ページへ
	 * (Profile編集ページは2-1と共通)
	 * 
	 * @param String @RequestParam
	 * @param Model 
	 * @param ProfileForm ＊セッションに格納しているFormを取得
	 * 
	 */
    @GetMapping(path = "edit")
    String ListEditForm(@RequestParam("userno") String userno,
    					Model model, 
    					ProfileForm MyProfileForm) {
    	
    	ProfileBean.setUserno(userno);
    	ProfileBean = ProfileService.findOne(ProfileBean);
    	BeanUtils.copyProperties(ProfileBean, MyProfileForm);
    	model.addAttribute("MyProfileForm", MyProfileForm);
    	
    	return "myprofile/MyProfile";
    }

}
