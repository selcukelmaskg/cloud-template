package com.poc.customerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfiguration {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Customer Service API in Spring-Boot 2")
                .description(
                        "Customer Service")
                .termsOfServiceUrl("").version("0.0.1-SNAPSHOT").build();
    }

    @Bean
    public Docket configureControllerPackageAndConvertors() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.poc.customerservice")).build()
                .apiInfo(apiInfo());
    }

}
