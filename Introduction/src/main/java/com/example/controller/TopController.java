package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.bean.ProfileBean;
import com.example.service.LoginUserDetails;
import com.example.service.ProfileService;


//画面遷移用コントロaーラ
@Controller
//マッピングするURLの接頭辞
@RequestMapping(value = "/Top", method = RequestMethod.GET)
@SessionAttributes(value = {"MyProfileBean"})
@ComponentScan("bean")
public class TopController {
	
	@Autowired
	ProfileService ProfileService;
	
	/**
	 * メイン画面表示
	 * MyProfileを取得して、Topページへ
	 * @param Model
	 * @param LoginUserDetails @AuthenticationPrincipal
	 * 
	 */
	@GetMapping
    String list(Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {
		
		System.out.println("【ログイン情報の確認】-----------------------------------------");
		System.out.println(userDetails.getUser());
		System.out.println("-----------------------------------------【ログイン情報の確認】");
		
		ProfileBean MyProfileBean = createMyProfileBean(userDetails);
		model.addAttribute("MyProfileBean", MyProfileBean);
		
        return "contents/Top";
    }

	/**
	 * MyProfileを取得
	 * @param LoginUserDetails
	 * 
	 */
	private ProfileBean createMyProfileBean(LoginUserDetails userDetails) {
		ProfileBean MyProfileBean = new ProfileBean();
		MyProfileBean.setUserno(userDetails.getUser().getUserno());
		ProfileBean ProfileBean = ProfileService.findOne(MyProfileBean);
		BeanUtils.copyProperties(ProfileBean, MyProfileBean);
		return MyProfileBean;
	}

}
