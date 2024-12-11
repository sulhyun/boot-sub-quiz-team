package kr.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.spring.boot.handler.CustomLogoutHandler;
import kr.spring.boot.handler.LoginFailHandler;
import kr.spring.boot.model.util.UserRole;
import kr.spring.boot.service.CustomOAuth2UserService;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	
	private final CustomOAuth2UserService customOAuth2UserService; // OAuth2 사용자 정보 서비스
	private final CustomLogoutHandler customLogoutHandler;
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf ->csrf.disable())
            .authorizeHttpRequests((requests) -> requests
            	.requestMatchers("/info/*").authenticated()								// 로그인만 되어있으면 '유저', '관리자' 모두 이용 가능
                .requestMatchers("/post/insert/*").hasAuthority(UserRole.USER.name()) 	// '로그인 된 유저'만 이용 가능한 페이지
                .requestMatchers("/admin/**").hasAnyAuthority(UserRole.ADMIN.name())	// '관리자'만 이용 가능한 페이지
                .anyRequest().permitAll()  												// 그 외 요청은 인증 필요
            )
            .formLogin((form) -> form
                .loginPage("/member/login")  				// 커스텀 로그인 페이지 설정
                .permitAll()          						// 로그인은 모두 접근 가능
                .loginProcessingUrl("/member/login")		// 로그인 요청을 처리할 URL을 설정
                .failureHandler(new LoginFailHandler())		// 로그인 실패시 실행될 핸들러
                .defaultSuccessUrl("/")						// 로그인 성공 후 리다이렉트할 기본 URL
            )
            .logout((logout) -> logout
            		.logoutUrl("/member/logout")	// 로그아웃 요청을 처리할 URL을 설정
            		.addLogoutHandler(customLogoutHandler)
            		.logoutSuccessUrl("/")			// 로그아웃 성공 후 리다이렉트할 기본 URL
            		.clearAuthentication(true)		// 로그아웃 시 인증 정보 제거
            		.invalidateHttpSession(true)	// 로그아웃 시 세션 무효화
            		.permitAll()					// 로그아웃은 모두 접근 가능
           )
           .oauth2Login(oauth2 -> oauth2
                    .loginPage("/member/login")                   // 소셜 로그인도 동일한 로그인 페이지 사용
                    .defaultSuccessUrl("/")                       // 소셜 로그인 성공 후 리다이렉트할 기본 URL
                    .userInfoEndpoint(userInfo -> userInfo
                        .userService(customOAuth2UserService)     // 사용자 정보를 처리할 서비스
           )
        );    
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}