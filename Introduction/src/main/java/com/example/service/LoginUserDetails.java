package com.example.service;

import com.example.domain.User;

import lombok.Data;

import org.springframework.security.core.authority.AuthorityUtils;

// Spring Securityで認証する場合、下記２点を利用する
// ・org.springframework.security.core.userdetails
// ・org.springframework.security.core.userdetails.UserDetailsService;

@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
	
	private static final long serialVersionUID = 1L;
	// Spring Securityの認証ユーザから、実際のUserオブジェクトを取得できるように、フィールドを追加。
	private final User user;

	// ユーザー名、パスワード、許可用のロールを設定（ロールの作成には、AuthorityUtilsを利用）
    public LoginUserDetails(User user) {
    	super(user.getUserno(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }
}