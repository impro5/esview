package esform;

import esform.filter.LoggerFilter;
import esform.filter.OauthFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by
 *
 * @name:孙证杰
 * @email:200765821@qq.com on 2017/9/17.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@PropertySource(value = {"classpath:esform.properties"})
@MapperScan(basePackages = "esform.dao")
public class Application {

    @Autowired
    private LoggerFilter loggerFilter;
    @Autowired
    private OauthFilter oauthFilter;
    private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LOGGER.info("--启动了--");
    }

    @Bean
    public FilterRegistrationBean<LoggerFilter> logFilter() {
        FilterRegistrationBean<LoggerFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(loggerFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<OauthFilter> authFilter() {
        FilterRegistrationBean<OauthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(oauthFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
