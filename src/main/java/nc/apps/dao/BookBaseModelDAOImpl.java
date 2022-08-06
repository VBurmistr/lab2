package nc.apps.dao;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.BookBaseModelDAO;
import nc.apps.entities.BookBaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
public class BookBaseModelDAOImpl implements BookBaseModelDAO {
    private final DataSource dataSource;

    @Autowired
    public BookBaseModelDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static final String SQL_GET_ALL = "SELECT id, title FROM LAB3_BOOK_TABLE";

    @Override
    public List<BookBaseModel> getAll() throws DAOException{
        List<BookBaseModel> bookBaseModels = new ArrayList<>();
        log.info("Get all bookBaseModels query:"+SQL_GET_ALL);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_GET_ALL)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                bookBaseModels.add(new BookBaseModel(resultSet.getLong("id"),
                        resultSet.getString("title")));
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting base-model's.",e);
        }
        return bookBaseModels;
    }
}
