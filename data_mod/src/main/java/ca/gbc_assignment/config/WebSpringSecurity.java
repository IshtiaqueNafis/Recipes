package ca.gbc_assignment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): < Nafis Ishtiaque>
 * Student Number: < 101206872>
 * Date: October 23, 2022
 * Description: "set basic secuirty routes"
 */
//endregion
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class WebSpringSecurity {

    private UserDetailsService userDetailsService;

    public WebSpringSecurity(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.authorizeRequests().
                antMatchers("/resources/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/registered/**").hasAuthority("REGISTERED").anyRequest().permitAll()
                .and().formLogin(form -> form.loginPage("/auth/loginForm").defaultSuccessUrl("/registered/recipes").loginProcessingUrl("/login").permitAll()).logout(
                        logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()

                ).headers().frameOptions().disable().and().
                csrf().
                disable();


        return http.build();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());


    }

}
