package xyz.krstic.restservices.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final Contact DEFAULT_CONTACT = new Contact(
			"Ivan Krstic",
			null,
			"krstic.d.ivan@gmail.com");
	
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			"REST Services API",
			null,
			"1.0",
			"Terms of service URL",
			DEFAULT_CONTACT,
			"License of API",
			"API license URL",
			Collections.emptyList());

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error")))
				.paths(Predicates.not(PathSelectors.regex("/")))
				.build()
				.apiInfo(DEFAULT_API_INFO);
	}

}