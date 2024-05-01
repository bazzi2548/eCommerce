//package com.example.goodsservice.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final AuthenticationConfiguration authenticationConfiguration;
//
//    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
//        this.authenticationConfiguration = authenticationConfiguration;
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//
//        return configuration.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable) //csrf disable
//                .cors(AbstractHttpConfigurer::disable) //cors disable
//                .formLogin(AbstractHttpConfigurer::disable) //Form 로그인 방식 disable
//                .httpBasic(AbstractHttpConfigurer::disable) //http basic 인증 방식 disable
//                .logout((logout) -> logout.deleteCookies("access")
//                        .logoutSuccessUrl("/api/goods"))
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/login", "/api/goods", "/api/goods/**", "/sign-up","localhost:8081/**").permitAll()
//                        .requestMatchers("/my-page","/wishlist", "/logout", "api/orders","/user").hasAuthority("customer")
//                        .anyRequest().authenticated()) //경로별 인가 작업
//                .sessionManagement((session) -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //세션 설정
//                .build();
//
//    }
//}