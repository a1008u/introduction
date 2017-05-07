package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.example.form.ProfileForm;


//画面遷移用コントロaーラ
@Controller
//マッピングするURLの接頭辞
@RequestMapping("MyProfile_confirm")
@SessionAttributes(value = "MyProfileForm")
public class MyProfile_2_confirm_controller {


	/**
	 * 2-1----------------------------------------------------
	 * 編集情報を取得し、入力→確認画面へ
	 * 
	 * @param Model
	 * @param ProfieForm　@Validated @ModelAttribute("MyProfileForm") ＊セッションに格納しているFormを取得
	 * @param BindingResult
	 * 
	 */
	@PostMapping
    String editForm(Model Mav, 
    				@Validated @ModelAttribute("MyProfileForm")ProfileForm MyProfileForm, 
    		        BindingResult result) {
		
		// 単項目チェック
		if (result.hasErrors()) return "myprofile/MyProfile";
		
		// 相関チェック
		
		// Usernoの確認
		Check_Userno(MyProfileForm);
		
		return "myprofile/MyProfile_confirm";
    }
	
	/**
	 * 2-1----------------------------------------------------
	 * 確認画面→入力画面へ
	 * 
	 * @param Model
	 * @param ProfieForm　@Validated @ModelAttribute("MyProfileForm") ＊セッションに格納しているFormを取得
	 * @param BindingResult
	 * 
	 */
	@GetMapping(path = "return")
    String retrunForm(Model Mav, 
    				@Validated @ModelAttribute("MyProfileForm")ProfileForm MyProfileForm, 
    		        BindingResult result) {
		
		// 単項目チェック
		if (result.hasErrors()) return "myprofile/MyProfile";
		
		// 相関チェック
		
		// Usernoの確認
		Check_Userno(MyProfileForm);
        return "myprofile/MyProfile";
    }
	
	// 
	/**
	 * 共通メソッド----------------------------------------------------------
	 * Usernoの確認
	 * 
	 * @param ProfieForm　
	 * 
	 */
	private void Check_Userno(ProfileForm MyProfileForm) {
		System.out.println("========================");
		System.out.println(MyProfileForm.getUserno());
		System.out.println("========================");
	}
	
}
