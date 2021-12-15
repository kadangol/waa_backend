package com.waa.AmazonMini.configuration;

import com.waa.AmazonMini.auth.model.ERole;
import com.waa.AmazonMini.auth.security.jwt.AuthEntryPointJwt;
import com.waa.AmazonMini.auth.security.jwt.AuthTokenFilter;
import com.waa.AmazonMini.auth.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
//               .antMatchers(HttpMethod.GET, "/category/get-all").permitAll()
//                .antMatchers(HttpMethod.GET, "/category/get/{id:[\\d]+}").permitAll()
               .antMatchers(HttpMethod.GET, "/seller").hasAuthority(ERole.ROLE_SELLER.toString())
                .antMatchers(HttpMethod.PUT, "/seller/{id}/approve").hasAuthority(ERole.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/seller/{id}/reject").hasAuthority(ERole.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/seller/{id}/profile").hasAnyAuthority(ERole.ROLE_ADMIN.toString(),ERole.ROLE_SELLER.toString())

                .antMatchers(HttpMethod.GET, "/buyer").hasAnyAuthority(ERole.ROLE_ADMIN.toString(),ERole.ROLE_BUYER.toString())
                .antMatchers(HttpMethod.GET, "/buyer/{buyerId}").hasAnyAuthority(ERole.ROLE_ADMIN.toString(),ERole.ROLE_BUYER.toString())
                .antMatchers(HttpMethod.PUT, "/buyer").hasAnyAuthority(ERole.ROLE_ADMIN.toString(),ERole.ROLE_BUYER.toString())
                .antMatchers(HttpMethod.DELETE, "/buyer/{buyerId}").hasAnyAuthority(ERole.ROLE_ADMIN.toString(),ERole.ROLE_BUYER.toString())



//                .antMatchers(HttpMethod.GET, "/sub-category/get-all").permitAll()
//                .antMatchers(HttpMethod.GET, "/sub-category/get/{id:[\\d]+}").permitAll()
//                .antMatchers(HttpMethod.POST, "/sub-category").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/signin").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/signup").permitAll()
//                .antMatchers(HttpMethod.POST, "/item/get-active-item").permitAll()
//                .antMatchers(HttpMethod.POST, "/item/get-all").permitAll()
//                .antMatchers(HttpMethod.POST, "/item-rent-line/get-by-item-id/{itemId:[\\d]+}").permitAll()
                .anyRequest()
                .authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS)
                .antMatchers("/public/**")
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**");
    }
}
