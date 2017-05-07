package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

// SpringSecurityの基本設定が行われる(/loginのPOSTはここで処理される)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// 特定のリクエストに対して「セキュリティ設定」を無視する設定など、全体に関わる設定ができる
    @Override
    public void configure(WebSecurity web) throws Exception {
    	// 静的リソールに対するアクセスには、セキュリティの設定は無視
        web.ignoring().antMatchers("/webjars/**", "/css/**");
    }

    // 許可の設定や、ログイン・ログアウトに関する設定ができる。
    // authorizeRequests()：許可に関する設定。ログインフォームを表示する。
    //　　　　　　　　　　　　　「/loginForm」には任意のユーザーがアクセスできるようにする。それ以外のパスには、認証なしでアクセスできないようにする。
    // formLogin()：「ログイン」に関する「設定」を行う。
    //             「フォーム認証」を有効にし、「認証処理のパス」「ログイン・フォーム表示のパス」「認証失敗時の遷移先」「認証成功時の遷移先」「ユーザ名・パスワードのパラメータ名」を設定する。
    // logout()：POSTでアクセスすることでログアウトできるようになる。
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                    .antMatchers("/loginForm").permitAll()
                    .anyRequest().authenticated()
            .and()
            .formLogin()
            	.loginProcessingUrl("/login") // 「認証処理のパス」
                    .loginPage("/loginForm") // ログイン・フォーム表示のパス」
                    .failureUrl("/loginForm?error") // 「認証失敗時の遷移先」
                    .defaultSuccessUrl("/Top", true) // 「認証成功時の遷移先」
                    .usernameParameter("UserNo").passwordParameter("password") // 「ユーザ名・パスワードのパラメータ名」
            .and()
            .logout()
                    .logoutSuccessUrl("/loginForm");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
    	// パスワードをハッシュ化するためのPasswordEncoderクラスの定義
        return new Pbkdf2PasswordEncoder();
    }
}