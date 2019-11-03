package com.phone.site;

import javax.sql.DataSource;

import com.aafanasev.fonoapi.retrofit.FonoApiFactory;
import com.aafanasev.fonoapi.retrofit.FonoApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SiteApplication extends SpringBootServletInitializer
{
	public static void main(String[] args) {
		SpringApplication.run(SiteApplication.class, args);
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(final DataSource dataSource)
	{
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

	@Bean
	public FonoApiService fonoApiService()
	{
		return new FonoApiFactory().create();
	}
}
