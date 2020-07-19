package com.service.resource.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("htmltopdf-Service")
            .description("Swagger api documentation of exposed rest apis of service")
            .version("1.0-SNAPSHOT")
            .termsOfServiceUrl("http://terms-of-services.url(to be updated)")
            .license("LICENSE(to be updated)")
            .licenseUrl("https://github.com/utsabc/htmltopdfservice/blob/master/LICENSE")
            .build();
    }

}