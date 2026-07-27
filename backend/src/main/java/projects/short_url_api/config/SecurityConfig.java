package projects.short_url_api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig { 

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter){

        this.securityFilter = securityFilter;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                    .csrf(csrf -> csrf.disable())
                    .cors(cors -> {})
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(authorize -> authorize
                                            .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                                            .anyRequest().permitAll())
                                            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                                            .build();
    }
}


