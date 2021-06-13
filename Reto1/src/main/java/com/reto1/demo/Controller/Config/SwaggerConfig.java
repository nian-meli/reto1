package com.reto1.demo.Controller.Config;

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
public class SwaggerConfig {
    // Docket es la documentacion de spring
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //Seleccionar solo el paquete que quiero que se muestre
                .apis(RequestHandlerSelectors.basePackage("com.reto1.demo.Controller.Controller"))
                .build().apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder().title("SocialMeli API")
                .description("This api allow next others user and his products")
                .version("1.1.0")
                .license("Created by Nicoll Angulo ☺️")
                .build();
    }
}
