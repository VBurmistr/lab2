package nc.apps.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import nc.apps.connectionmanager.ConnectionManagerJNDI;
import nc.apps.connectionmanager.interfaces.ConnectionManager;
import nc.apps.propertyholder.SmartAdderPropertyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(value = "nc.apps")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@Slf4j
public class AppConfig implements WebMvcConfigurer {
    @Bean
    @Profile({"dev","default","test"})
    @Primary
    SmartAdderPropertyHolder devSmartAdderPropertyHolder(@Value("${smartadder.port.dev}") String port,
                                                            @Value("${smartadder.domain.dev}") String domain,
                                                         @Value("${smartadder.context}") String context){
        log.info("Initialized devSmartAdderPropertyHolder bean.");
        return new SmartAdderPropertyHolder(domain,port,context );
    }

    @Bean
    @Profile("docker")
    SmartAdderPropertyHolder dockerSmartAdderPropertyHolder(@Value("${smartadder.port.docker}") String port,
                                                               @Value("${smartadder.domain.docker}") String domain,
                                                            @Value("${smartadder.context}") String context){
        log.info("Initialized dockerSmartAdderPropertyHolder bean.");
        return new SmartAdderPropertyHolder(domain,port,context);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/");
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean
                = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/view/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Bean
    @Profile("docker")
    public DataSource dataSourceDocker(@Value("${datasource.driver}") String driver,
                                    @Value("${datasource.url_docker}") String url,
                                    @Value("${datasource.user}") String username,
                                    @Value("${datasource.password}") String password) {
        log.info("Initialized dataSourceDocker bean.");
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(20);
        ds.setDriverClassName(driver);
        ds.setJdbcUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    @Profile({"dev","default"})
    public DataSource dataSourceDevelop(@Value("${datasource.driver}") String driver,
                                        @Value("${datasource.url}") String url,
                                        @Value("${datasource.user}") String username,
                                        @Value("${datasource.password}") String password) {
        log.info("Initialized dataSourceDevelop bean.");
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(20);
        ds.setDriverClassName(driver);
        ds.setJdbcUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

//    @Bean
//    @Profile("test")
//    public DataSource dataSourceTests(@Value("${datasource.driver}") String driver,
//                                    @Value("${datasource.url_tests}") String url,
//                                    @Value("${datasource.user}") String username,
//                                    @Value("${datasource.password}") String password) {
//        log.info("Initialized dataSourceTests bean.");
//        HikariDataSource ds = new HikariDataSource();
//        ds.setMaximumPoolSize(20);
//        ds.setDriverClassName(driver);
//        ds.setJdbcUrl(url);
//        ds.setUsername(username);
//        ds.setPassword(password);
//        return ds;
//    }
}
