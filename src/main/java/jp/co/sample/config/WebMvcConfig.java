package jp.co.sample.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScans(value = {
		@ComponentScan("jp.co.sample")
	})
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// ViewResolverRegistryクラスのjspメソッドを呼び出し JSP 用のViewResolverをセットアップします。
		// これにより、/WEB-INFディレクトリ配下に格納されているJSPファイルがViewとして扱われます。
		registry.jsp().prefix("/WEB-INF/views/");
	}
	/**
	 * Validationメッセージをpropertiesファイル定義にすると文字化けしてしまうことへの対応
	 */
	public Validator getValidator() {
		return validator();
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource());
		return localValidatorFactoryBean;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
		bean.setBasename("classpath:ValidationMessages");
		bean.setDefaultEncoding("UTF-8");
		return bean;
	}
}
