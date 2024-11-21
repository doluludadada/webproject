package org.gla.springbackend.config;

import org.gla.springbackend.service.CustomUserDetailsService; // 自訂的使用者詳情服務
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 聲明這是一個組態類
@EnableWebSecurity // 啟用 Spring Security 的 Web 安全支援
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService; // 注入自訂的使用者詳情服務

    // 組態密碼編碼器，使用 BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 組態認證管理器
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // 組態安全過濾鏈
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 組態請求的授權
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/signUp", "/css/**", "/js/**", "/media/**").permitAll() // 允許所有人訪問的路徑
                .anyRequest().authenticated() // 其他請求需要認證
            )
            // 組態表單登錄
            .formLogin(form -> form
                .loginPage("/login") // 登錄頁面的路徑
                .loginProcessingUrl("/login") // 處理登錄請求的路徑
                .defaultSuccessUrl("/homepage", true) // 登錄成功後跳轉的路徑
                .permitAll() // 允許所有人訪問登錄相關的頁面
            )
            // 組態註銷
            .logout(logout -> logout
                .logoutUrl("/logout") // 註銷請求的路徑
                .logoutSuccessUrl("/login?logout") // 註銷成功後跳轉的路徑
                .permitAll() // 允許所有人訪問註銷相關的頁面
            );

        return http.build();
    }
}
