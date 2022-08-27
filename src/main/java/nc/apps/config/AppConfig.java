package nc.apps.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import nc.apps.connectionmanager.ConnectionManagerJNDI;
import nc.apps.connectionmanager.interfaces.ConnectionManager;
import nc.apps.propertyholder.SmartAdderPropertyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
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
@Slf4j
public class AppConfig implements WebMvcConfigurer {


    @Bean
    @Profile({"dev","default"})
    @Primary
    SmartAdderPropertyHolder devSmartAdderPropertyHolder(@Value("${smartadder.dev.port}") String port,
                                                            @Value("${smartadder.domain}") String domain){
        log.info("Initialized devSmartAdderPropertyHolder bean.");
        return new SmartAdderPropertyHolder(domain,port);
    }

    @Bean
    @Profile("docker")
    SmartAdderPropertyHolder dockerSmartAdderPropertyHolder(@Value("${smartadder.docker.port}") String port,
                                                               @Value("${smartadder.domain}") String domain){
        log.info("Initialized dockerSmartAdderPropertyHolder bean.");
        return new SmartAdderPropertyHolder(domain,port);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/");

//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
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
    @Profile("dev")
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
}
