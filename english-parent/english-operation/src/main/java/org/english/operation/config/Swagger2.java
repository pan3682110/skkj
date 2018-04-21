package org.english.operation.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
    	List<Parameter> operationParameters = new ArrayList<Parameter>();
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("authorization").description("Token 除了登录接口,注册接口外都需要").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        operationParameters.add(aParameterBuilder.build());
    	return new Docket(DocumentationType.SWAGGER_2)
        .globalOperationParameters(operationParameters)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.english.operation.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    
	private ApiInfo apiInfo() {
//    	Contact contact = new Contact("FISHLEE", "https://www.onemost.net", "260414677@qq.com");
    	return new ApiInfoBuilder()
        .title("晟堃科技接口文档")
        .description("Project:老杨疯狂英语")
        .termsOfServiceUrl("org.english.operation")
//        .contact(contact)
        .version("1.0")
        .build();
    }
}
