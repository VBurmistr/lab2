package nc.apps.datasources;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Slf4j
@Profile("test")
@Primary
public class TestDataSource extends HikariDataSource {
    public TestDataSource(@Value("${datasource.driver}") String driver,
                          @Value("${datasource.url_tests}") String url,
                          @Value("${datasource.user}") String username,
                          @Value("${datasource.password}") String password) {
        System.out.println("Test hikari");
        this.setMaximumPoolSize(20);
        this.setDriverClassName(driver);
        this.setJdbcUrl(url);
        this.setUsername(username);
        this.setPassword(password);
    }
}
