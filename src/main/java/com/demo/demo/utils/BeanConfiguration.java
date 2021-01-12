package com.demo.demo.utils;

import com.google.common.collect.Lists;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@Configuration
@EnableSwagger2
public class BeanConfiguration {
	
	private final BasicConfiguration basicConfiguration;
	
	@Autowired
	public BeanConfiguration(BasicConfiguration basicConfiguration) {
		this.basicConfiguration = basicConfiguration;
	}
	
	@Bean
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.url(basicConfiguration.getDATABASE_URL())
				.username(basicConfiguration.getDATABASE_USERNAME())
				.password(basicConfiguration.getDATABASE_PASSWORD())
				.build();
	}
	
	private ApiKey apiKey() {
		return new ApiKey("AUTHORIZATION", "token", "header");
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.enable(true)
				.securitySchemes(Lists.newArrayList(apiKey()));
	}
	
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
		corsConfiguration.addAllowedMethod(HttpMethod.PUT);
		corsConfiguration.addAllowedMethod(HttpMethod.GET);
		corsConfiguration.addAllowedMethod(HttpMethod.POST);
		corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
		corsConfiguration.setAllowedOrigins(basicConfiguration.getALLOWED_ORIGINS());
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
	
	@Bean
	public  RazorpayClient razorpayClient() throws RazorpayException {
		return new RazorpayClient(basicConfiguration.getSECRET_ID(), basicConfiguration.getSECRET_KEY());
	}
	
}
