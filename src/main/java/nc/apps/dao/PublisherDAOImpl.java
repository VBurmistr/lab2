package nc.apps.dao;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.PublisherDAO;
import nc.apps.entities.domain.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class PublisherDAOImpl implements PublisherDAO {
    private final DataSource dataSource;
    public static final String SQL_GET_ALL = "SELECT * FROM LAB3_PUBLISHER_TABLE";
    public static final String SQL_ADD_NEW = "INSERT INTO LAB3_PUBLISHER_TABLE (publisher_name) VALUES (?)";
    public static final String SQL_REMOVE = "DELETE FROM lab3_publisher_table where id = ?";

    @Autowired
    public PublisherDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<Publisher> getAll() throws DAOException {
        List<Publisher> publishers = new ArrayList<>();
        log.info("Get all publishers query:" + SQL_GET_ALL);
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_GET_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                publishers.add(Publisher.builder()
                        .id(resultSet.getInt("id"))
                        .publisherName(resultSet.getString("publisher_name"))
                        .build());
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting publisher's.", e);
        }
        return publishers;
    }

    @Override
    public void save(Publisher publisher) throws DAOException {
        log.info("Add new publisher query:" + SQL_GET_ALL);
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_ADD_NEW)) {
            statement.setString(1, publisher.getPublisherName());
            int res = statement.executeUpdate();
            if (res == 0) {
                throw new DAOException("Cant add publisher for some reason, publisher:" + publisher);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while saving publisher:" + publisher, e);
        }
    }

    @Override
    public void remove(int id) throws DAOException {
        log.info("Remove publisher query:"+SQL_REMOVE);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_REMOVE)){
            statement.setInt(1,id);
            int res = statement.executeUpdate();
            if (res == 0) {
                throw new DAOException("Cant remove publisher for some reason, publisher with id:"+id);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while removing publisher with id:"+id,e);
        }
    }
}
