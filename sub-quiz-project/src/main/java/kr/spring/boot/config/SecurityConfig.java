package kr.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.spring.boot.model.util.UserRole;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf ->csrf.disable())
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/post/insert/*").hasAuthority(UserRole.USER.name()) 	// '로그인 된 유저'만 이용 가능한 페이지
                .requestMatchers("/admin/**").hasAnyAuthority(UserRole.ADMIN.name())	// '관리자'만 이용 가능한 페이지
                .anyRequest().permitAll()  												// 그 외 요청은 인증 필요
            )
            .formLogin((form) -> form
                //.loginPage("/login")  		// 커스텀 로그인 페이지 설정
                .permitAll()          			// 로그인은 모두 접근 가능
                .loginProcessingUrl("/login")	// 로그인 요청을 처리할 URL을 설정
                .defaultSuccessUrl("/")			// 로그인 성공 후 리다이렉트할 기본 URL
            )
            .logout((logout) -> logout
            		.logoutUrl("/logout")			// 로그아웃 요청을 처리할 URL을 설정
            		.logoutSuccessUrl("/")			// 로그아웃 성공 후 리다이렉트할 기본 URL
            		.clearAuthentication(true)		// 로그아웃 시 인증 정보 제거
            		.invalidateHttpSession(true)	// 로그아웃 시 세션 무효화
            		.permitAll());  				// 로그아웃은 모두 접근 가능
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
