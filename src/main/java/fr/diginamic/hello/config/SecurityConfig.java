package fr.diginamic.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.authorizeHttpRequests(request -> request
              .requestMatchers("/","/login").permitAll()
              .requestMatchers("/logout").authenticated()
              .requestMatchers("/city").authenticated()
              .requestMatchers("/h2-console/**").permitAll()
              .anyRequest().denyAll())
              .httpBasic(Customizer.withDefaults())
              .formLogin(Customizer.withDefaults());
        return http.build();
    }

}
