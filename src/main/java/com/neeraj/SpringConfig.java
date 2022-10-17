package com.neeraj;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringConfig {

	@Bean
	public DataSource data() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/student", "root",
				"root");
		return dataSource;
	}

	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate() {
		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(data());
		return jdbc;
	}

	@Bean
	public EmployeeDao empDao() {
		NamedParamEmployeeDao nDao = new NamedParamEmployeeDao();
		nDao.setT(jdbcTemplate());
		return nDao;
	}

}
