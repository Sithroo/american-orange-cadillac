package io.sithroo.aoc.transactions.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfo(
                "Transaction Service API",
                "The API service allows searching for transaction",
                "v1",
                "Terms of service",
                new Contact("Pradeep Peiris", "aoc.sithroo.io", "wpnpeiris@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.sithroo.aoc.transactions.resource"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo);
    }
}

