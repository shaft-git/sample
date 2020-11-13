package jp.co.sample.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class PoolingDataSourceConfig {

	@Bean
	public DataSource dataSource(
			@Value("${database.driverClassName}") String driverClassName
			, @Value("${database.url}")String url
			, @Value("${database.username}")String username
			, @Value("${database.password}")String password
			, @Value("${cp.maxTotal}")int maxTotal
			, @Value("${cp.maxIdle}")int maxIdle
			, @Value("${cp.minIdle}")int minIdle
			, @Value("${cp.maxWaitMillis}")long maxWaitMillis
			) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDefaultAutoCommit(true);
		dataSource.setMaxTotal(maxTotal);
		dataSource.setMaxIdle(maxIdle);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxWaitMillis(maxWaitMillis);
		return dataSource;
	}
}
