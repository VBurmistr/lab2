package nc.apps.connectionmanager;

import nc.apps.connectionmanager.interfaces.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerDriver implements ConnectionManager {
    private final String username;
    private final String password;
    private final String url;

    public ConnectionManagerDriver(@Value("${datasource.driver}") String driver,
                                   @Value("${datasource.user}") String username,
                                   @Value("${datasource.password}") String password,
                                   @Value("${datasource.url}") String url) {
        try{
            Class.forName(driver);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        this.password = password;
        this.username = username;
        this.url = url;

    }
    public Connection getConnection(){
        try {
            return DriverManager.getConnection(url,
                    username,
                    password);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
