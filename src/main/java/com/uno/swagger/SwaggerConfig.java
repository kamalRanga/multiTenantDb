package com.uno.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("USER")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.uno.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaInfo());
    }
   

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "UNO Project API",
                "API Server Documentation",
                "1.0",
                "Terms of Service",
                new Contact("UNO", "",
                        ""),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html"
        );

        return apiInfo;
    }
}
