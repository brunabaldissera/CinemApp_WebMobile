package br.upf.cinemApp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfigurations (val securityFilter: SecurityFilter){
    @Bean
    fun securityFilterChain(
            httpSecurity: HttpSecurity
    ): SecurityFilterChain {
        return httpSecurity
                .csrf { it.disable() }
                .sessionManagement {
                    it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                }
                .authorizeHttpRequests {
                    it.requestMatchers(HttpMethod.POST, "/sessions").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/sessions", "/sessions/*").permitAll()
                            .requestMatchers("/sessions/*").hasRole("ADMIN")
                            .requestMatchers("/auth/*").permitAll()
                            .requestMatchers("/usuarios/*").hasRole("ADMIN")
                            .anyRequest().authenticated()
                }
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter::class.java)
                .build()
    }
    @Bean
    fun authenticateManager(
            authenticationConfiguration: AuthenticationConfiguration
    ): AuthenticationManager{
        return authenticationConfiguration.authenticationManager
    }
    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder()
    }
}