package net.developer.space.chargingstationsservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Lazaro Noel Guerra Medina
 * @since 17/04/2024
 * @version 1.0.0
 * @implNote Swagger configuration
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("net.developer.space"))
        .paths(PathSelectors.any())
        .build().apiInfo(apiInfoMetadata());
    }

    private ApiInfo apiInfoMetadata(){
        return new ApiInfoBuilder()
        .title("API Documentation")
        .description("API for check disponibility of charging stations for EV")
        .contact(new Contact("Lazaro Noel Guerra Medina", null, "lazaronoelg@gmail.com"))
        .version("1.0.0")
        .build();
    }
}
