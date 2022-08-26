package nc.apps.connectionmanager;

import lombok.extern.slf4j.Slf4j;
import nc.apps.connectionmanager.interfaces.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
@Slf4j
public final class ConnectionManagerJNDI implements ConnectionManager {
    private final String weblogicDatasourceJndi;

    public ConnectionManagerJNDI(@Value("${weblogic.datasource.jndi}") String weblogicDatasourceJndi) {
        this.weblogicDatasourceJndi = weblogicDatasourceJndi;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        DataSource dataSource = null;
        Context context = null;
        Hashtable ht = new Hashtable();
        ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
        ht.put(Context.PROVIDER_URL,"t3://localhost:7001");
        try{
            context = new InitialContext(ht);
            dataSource = (DataSource) context.lookup(weblogicDatasourceJndi);
            connection = dataSource.getConnection();
        } catch (NamingException e) {
            log.error("Cant initialize connection via JNDI",e);
        }
        return connection;
    }
}
