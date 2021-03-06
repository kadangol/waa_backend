package com.waa.AmazonMini.configuration;

import com.waa.AmazonMini.auth.model.ERole;
import com.waa.AmazonMini.auth.security.jwt.AuthEntryPointJwt;
import com.waa.AmazonMini.auth.security.jwt.AuthTokenFilter;
import com.waa.AmazonMini.auth.security.services.UserDetailsServiceImpl;
import com.waa.AmazonMini.domain.Review;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import com.waa.AmazonMini.utils.enums.Status;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

                .antMatchers(HttpMethod.GET, "/email").permitAll()

                .antMatchers(HttpMethod.POST, "/seller/sign-up").permitAll()
                .antMatchers(HttpMethod.GET, "/seller").hasAuthority(ERole.ROLE_SELLER.toString())
                .antMatchers(HttpMethod.PUT, "/seller/{id}/approve").hasAuthority(ERole.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/seller/{id}/reject").hasAuthority(ERole.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/seller/{id}/profile").hasAnyAuthority(ERole.ROLE_ADMIN.toString(), ERole.ROLE_SELLER.toString(), ERole.ROLE_BUYER.toString())



                .antMatchers(HttpMethod.POST, "/buyer/sign-up").permitAll()
                .antMatchers(HttpMethod.GET, "/buyer").hasAnyAuthority(ERole.ROLE_ADMIN.toString(), ERole.ROLE_BUYER.toString())
                .antMatchers(HttpMethod.GET, "/buyer/{buyerId}").hasAnyAuthority(ERole.ROLE_ADMIN.toString(), ERole.ROLE_BUYER.toString())
                .antMatchers(HttpMethod.PUT, "/buyer").hasAnyAuthority(ERole.ROLE_ADMIN.toString(), ERole.ROLE_BUYER.toString())
                .antMatchers(HttpMethod.DELETE, "/buyer/{buyerId}").hasAnyAuthority(ERole.ROLE_ADMIN.toString(), ERole.ROLE_BUYER.toString())
                .antMatchers(HttpMethod.DELETE, "/buyer/{buyerId}").hasAnyAuthority(ERole.ROLE_ADMIN.toString(), ERole.ROLE_BUYER.toString())
                .antMatchers(HttpMethod.PUT, "/buyer/follow/{sellerId}").hasAnyAuthority(ERole.ROLE_ADMIN.toString(), ERole.ROLE_BUYER.toString())
                .antMatchers(HttpMethod.PUT, "/buyer/unfollow/{sellerId}").hasAnyAuthority(ERole.ROLE_ADMIN.toString(), ERole.ROLE_BUYER.toString())


                .antMatchers(HttpMethod.GET, "/product").permitAll()
                .antMatchers(HttpMethod.GET, "/product/{productId}").permitAll()
                .antMatchers(HttpMethod.POST, "/product").hasAuthority(ERole.ROLE_SELLER.toString())
                .antMatchers(HttpMethod.PUT, "/product").hasAnyAuthority(ERole.ROLE_ADMIN.toString(), ERole.ROLE_SELLER.toString())
                .antMatchers(HttpMethod.DELETE, "/product").hasAnyAuthority(ERole.ROLE_ADMIN.toString(), ERole.ROLE_SELLER.toString())
                .antMatchers(HttpMethod.GET, "/product/findProductBySeller/{sellerId}").hasAnyAuthority(ERole.ROLE_SELLER.toString())

                .antMatchers(HttpMethod.POST, "/orderline/addToCart").hasAuthority(ERole.ROLE_BUYER.toString())
                .antMatchers(HttpMethod.POST, "/orderline/*").hasAuthority(ERole.ROLE_BUYER.toString())
                .antMatchers(HttpMethod.POST, "/orderline/purchaseOrder/{orderLineId}").hasAuthority(ERole.ROLE_BUYER.toString())
                .antMatchers(HttpMethod.POST, "/orderline/cancelOrder/{orderLineId}").hasAnyAuthority(ERole.ROLE_BUYER.toString(), ERole.ROLE_SELLER.toString())
                .antMatchers(HttpMethod.POST, "/orderline/updateOrderShippingStatus").hasAnyAuthority(ERole.ROLE_BUYER.toString(), ERole.ROLE_SELLER.toString())
                .antMatchers(HttpMethod.POST, "/orderline/updateOrderStatus").hasAnyAuthority(ERole.ROLE_BUYER.toString(), ERole.ROLE_SELLER.toString())
                .antMatchers(HttpMethod.POST, "/orderline/{orderLineId}").hasAnyAuthority(ERole.ROLE_BUYER.toString(), ERole.ROLE_SELLER.toString())

                .antMatchers(HttpMethod.POST, "/review").hasAuthority(ERole.ROLE_BUYER.toString())
                .antMatchers(HttpMethod.PUT, "/review/approve/{reviewId}").hasAuthority(ERole.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/review/reject/{reviewId}").hasAuthority(ERole.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/review/notapprovedyet").hasAuthority(ERole.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/review/approved").hasAuthority(ERole.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/review/rejected").hasAuthority(ERole.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/review/product/{productId}").permitAll()



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
