package nc.apps.dbinitializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DBInitializer {
    private final Resource resourceFile;
    private final DataSource dataSource;
    public DBInitializer(@Value("classpath:tableCreator.sql") Resource resourceFile,
                         @Autowired DataSource dataSource) {
        this.resourceFile = resourceFile;
        this.dataSource = dataSource;
    }
    @PostConstruct
    public void initializeDB() {
        try (Connection con = dataSource.getConnection();
                BufferedReader reader =
                     new BufferedReader(new FileReader(resourceFile.getFile()));
             PreparedStatement statement =
                     con.prepareStatement(reader.lines().collect(Collectors.joining()))) {
            log.info("Started DB initialization.");
            statement.execute();
            log.info("Db initialized.");

        } catch (SQLException | IOException e) {
            log.error("Error while initializing db.", e);
        }
    }
}
