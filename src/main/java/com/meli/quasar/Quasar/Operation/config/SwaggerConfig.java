package com.meli.quasar.Quasar.Operation.config;

import com.meli.quasar.Quasar.Operation.util.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    private static final Contact CONTACT = new Contact("Hector Suarez", null, "hector.suarez89@hotmail.com");
    private static final String DESCRIPCION = "Service to decode confidential information intercepted from the space ship imperial.";
    private static final String PATH_CONTROLLERS = "com.meli.quasar.Quasar.Operation";
    private String appVersion;

    private String appName;

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title(Constants.SWAGGER_TITLE)
                .version(appVersion)
                .description(String.format(DESCRIPCION, appName))
                .contact(CONTACT)
                .build();
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(getApiInfo())
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage(PATH_CONTROLLERS))
                .paths(regex("/.*"))
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl(null).build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
