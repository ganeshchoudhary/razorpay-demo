package com.demo.demo.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@ConfigurationProperties
public class BasicConfiguration {
	
	private String DATABASE_URL;
	
	private String DATABASE_USERNAME;
	
	private String DATABASE_PASSWORD;
	
	private  String SECRET_ID ;
	
	private  String SECRET_KEY;
	
	private List<String> ALLOWED_ORIGINS;
}
