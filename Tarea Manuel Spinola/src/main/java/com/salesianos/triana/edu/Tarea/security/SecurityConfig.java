package com.salesianos.triana.edu.Tarea.security;


import com.salesianos.triana.edu.Tarea.security.jwt.JwtAccessDeniedHandler;
import com.salesianos.triana.edu.Tarea.security.jwt.JwtAuthenticationEntryPoint;
import com.salesianos.triana.edu.Tarea.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsService userDetailsService;
    private final JwtAccessDeniedHandler accessDeniedHandler;
    private final JwtAuthorizationFilter filter;
    private final JwtAuthenticationEntryPoint entryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(PasswordEncoderFactories
                        .createDelegatingPasswordEncoder());
    }



    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET).authenticated()
                .antMatchers(HttpMethod.POST, "/auth/register/").anonymous()
                .antMatchers(HttpMethod.POST, "/auth/login/").anonymous()
                .antMatchers(HttpMethod.GET, "/tarea/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/tarea/mine").authenticated()
                .antMatchers(HttpMethod.GET, "/tarea/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "/tarea/").authenticated()
                .antMatchers(HttpMethod.PUT, "/tarea/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/tarea/{id}").authenticated()
                .antMatchers(HttpMethod.GET, "/tarea/{id}/comentario/").authenticated()
                .antMatchers(HttpMethod.POST, "/tarea/{id}/comentario/").authenticated()
                .antMatchers(HttpMethod.PUT, "/tarea/{id}/comentario/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/tarea/{id}/comentario/{id}").authenticated()
                .anyRequest().authenticated();

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);


    }

}