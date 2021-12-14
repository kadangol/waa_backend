package com.waa.AmazonMini.configuration;

import com.waa.AmazonMini.utils.service.PageSerializer;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.CacheControl;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableSpringDataWebSupport
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                        .allowCredentials(false);
            }
        };
    }

    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String imagePath = "file:" + System.getProperty("user.dir") + File.separator + FolderPath.ROOT_PATH.getFolderName() + File.separator;
//        registry.addResourceHandler("/" + File.separator + FolderPath.ROOT_PATH.getFolderName() + "/**")
//                .addResourceLocations(imagePath)
//                .setCacheControl(CacheControl.maxAge(2, TimeUnit.DAYS).cachePublic());
//    }

    //  -------------Configuring JsonView In Page-----------
    @Bean
    public Module jacksonPageWithJsonViewModule() {
        SimpleModule module = new SimpleModule("jackson-page-with-jsonview", Version.unknownVersion());
        module.addSerializer(PageImpl.class, new PageSerializer());
        return module;
    }

    /*
     * This allows to start pagination from 1
     * ie; /{url}?page=1
     * */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setOneIndexedParameters(true);
        argumentResolvers.add(resolver);

        super.addArgumentResolvers(argumentResolvers);
    }
}
