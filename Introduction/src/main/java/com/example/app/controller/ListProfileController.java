package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.bean.ProfileBean;
import com.example.app.service.ProfileService;

//画面遷移用コントローラ
@Controller
//マッピングするURLの接頭辞
@RequestMapping("ListProfile")
public class ListProfileController {
	
	@Autowired
	ProfileService ProfileService;
	
	/**
	 * Profile一覧を表示する。
	 * @param Model
	 * 
	 */
	@GetMapping
    String list(Model model) {
		
        List<ProfileBean> ListProfile = ProfileService.findAll();
        model.addAttribute("ListProfile", ListProfile);
        
        return "contents/ListProfile";
    }

}
