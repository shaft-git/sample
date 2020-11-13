package jp.co.sample.config;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("jp.co.sample.dao")
@EnableJpaAuditing
@PropertySource("classpath:jdbc.properties")
public class JpaConfig {

	@Bean
	public JpaVendorAdapter jpaVendorAdaptor() {
		HibernateJpaVendorAdapter vendorAdaptor = new HibernateJpaVendorAdapter();
		vendorAdaptor.setDatabase(Database.MYSQL);
		vendorAdaptor.setShowSql(true);
		return vendorAdaptor;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory =
				new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setPackagesToScan("jp.co.sample");
		factory.setJpaVendorAdapter(jpaVendorAdaptor());
		return factory;
	}
	
	@Bean
	@Qualifier
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}
	
	@Bean
	public AuditorAware<Integer> auditorAware() {
		return new AuditorAware<Integer>() {
			@Override
			public Optional<Integer> getCurrentAuditor() {
				Optional<Integer> ret = Optional.ofNullable(99999);
				return ret;
			}
		};
	}
}
