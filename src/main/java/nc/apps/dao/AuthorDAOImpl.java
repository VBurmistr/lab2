package nc.apps.dao;

import lombok.extern.slf4j.Slf4j;
import nc.apps.connectionmanager.interfaces.ConnectionManager;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.AuthorDAO;
import nc.apps.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class AuthorDAOImpl implements AuthorDAO {
    private final DataSource dataSource;
    public static final String SQL_GET_ALL = "SELECT * FROM LAB3_AUTHOR_TABLE";

    public static final String SQL_REMOVE = "DELETE FROM lab3_author_table where id = ?";

    public static final String SQL_ADD_NEW = "INSERT INTO LAB3_AUTHOR_TABLE (first_name,last_name) VALUES (?,?)";
    @Autowired
    public AuthorDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<Author> getAll() throws DAOException{
        List<Author> authors = new ArrayList<>();
        log.info("Get all authors query:"+SQL_GET_ALL);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_GET_ALL)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                authors.add(Author.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name")).build());
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting all authors.",e);
        }
        return authors;
    }

    @Override
    public void save(Author author) throws DAOException {
        log.info("Add new author query:"+SQL_ADD_NEW);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_ADD_NEW)){
            statement.setString(1,author.getFirstName());
            statement.setString(2,author.getLastName());
            int res = statement.executeUpdate();
            if (res == 0) {
                throw new DAOException("Cant add author for some reason, author:"+author);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while saving author:"+author,e);
        }
    }

    @Override
    public void remove(int id) throws DAOException {
        log.info("Remove author query:"+SQL_REMOVE);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_REMOVE)){
            statement.setInt(1,id);
            int res = statement.executeUpdate();
            if (res == 0) {
                throw new DAOException("Cant remove author for some reason, author with id:"+id);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while removing author with id:"+id,e);
        }
    }
}
