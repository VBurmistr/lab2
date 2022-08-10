package nc.apps.config;

import com.zaxxer.hikari.HikariDataSource;
import nc.apps.connectionmanager.ConnectionManagerJNDI;
import nc.apps.connectionmanager.interfaces.ConnectionManager;
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
public class AppConfig implements WebMvcConfigurer {

    @Configuration
    @Profile("dev")
    @PropertySource("classpath:smart-adder-dev.properties")
    static class devProps
    {
    }

    @Configuration
    @Profile("docker")
    @PropertySource("classpath:smart-adder-docker.properties")
    static class dockerProps
    {
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
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
    public DataSource getDataSourceDocker(@Value("${datasource.driver}") String driver,
                                    @Value("${datasource.url_docker}") String url,
                                    @Value("${datasource.user}") String username,
                                    @Value("${datasource.password}") String password) {
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
    public DataSource getDataSourceDevelop(@Value("${datasource.driver}") String driver,
                                    @Value("${datasource.url}") String url,
                                    @Value("${datasource.user}") String username,
                                    @Value("${datasource.password}") String password) {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(20);
        ds.setDriverClassName(driver);
        ds.setJdbcUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
