package com.waa.AmazonMini.configuration;


import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Date;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Docket api() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiEndPointsInfo()).pathMapping("/")
                .forCodeGeneration(true).genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Pageable.class).ignoredParameterTypes(java.sql.Date.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .securitySchemes(Arrays.asList(apiKey()))
                .useDefaultResponseMessages(false);

        docket = docket.select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*")).build();

        return docket;
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("MiniAmazon REST API").description("REST API Documentation")
                .contact(new Contact("Komiljon, Kapil, Juliet", "", ""))
                .license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0")
                .build();
    }
    private ApiKey apiKey() {
        return new ApiKey("jwtToken", "Authorization", "header");
    }

}
