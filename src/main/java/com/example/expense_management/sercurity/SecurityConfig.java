package com.example.expense_management.sercurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /*
     * Bộ lọc bảo mật. Được sử dụng để xác thực, phân quyền, chống tấn công độc hại  cho các request
     * */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName("mySession");
        HttpSecurity logout = http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .shouldFilterAllDispatcherTypes(true)
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login").loginPage("/") // URL of the login page
                        .loginProcessingUrl("/login")  // URL for processing automatic login
                        .defaultSuccessUrl("/user/") // Default URL after successful login
                        .permitAll()
                ).requestCache((cache) -> cache
                        .requestCache(requestCache)
                )
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }
}
