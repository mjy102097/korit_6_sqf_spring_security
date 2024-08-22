package com.study.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 우리가 만든 SecurityConfig를 적용시키겠다.
@Configuration // Configuration 어노테이션이 달린곳에서 Bean 등록이 가능하다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { // Bean을 생성하면 메소드 이름으로 IoC에 등록이 됨
        return new BCryptPasswordEncoder();
    }

    // Override : 부모 클래스의 메소드를 상속받아 재정의 하는것
    // Overloading : 같은 이름의 메소드인데 매개변수가 다른것
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable(); // formLogin(폼 기반 로그인)을 사용하지 않겠다.
        http.httpBasic().disable(); // httpBasic(alert 처럼 뜨는거) 사용하지 않겠다.
        http.csrf().disable(); // csrf(위조 방지 스티커(토큰))을 쓰지 않겠다.
//      http.sessionManagement().disable();
            // 스프링 시큐리티가 세션을 생성하지도 않고 기존의 세션을 사용하지도 않겠다.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            // 스프링 시큐리티가 세션을 생성하지 않겠다. 기존의 세션을 완전히 사용하지 않겠다는 뜻은 아님.
            // JWT 등의 토큰 인증방식을 사용할 때 설정하는 것.

        http.cors(); // CrossOrigin(다른 출처에서의 요청을 허용하도록 CORS를 활성화)

        http.authorizeRequests()
                .antMatchers("/auth/**", "/h2-console/**", "/test/**") // antMatchers: 주소 여러개를 고를수 있음
                .permitAll() // 해당 경로는 인증 없이 접근 가능
                .anyRequest() // 다른 모든 요청들은
                .authenticated() // 인가(인증) 필요 하다는 뜻
                .and()
                .headers()
                .frameOptions()
                .disable(); // H2 콘솔과 같은 프레임 관련 헤더 비활성화

    }
}
