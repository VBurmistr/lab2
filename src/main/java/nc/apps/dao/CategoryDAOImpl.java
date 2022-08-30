package nc.apps.dao;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.CategoryDAO;
import nc.apps.entities.Category;
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
public class CategoryDAOImpl implements CategoryDAO {
    private final DataSource dataSource;
    public static final String SQL_GET_ALL = "SELECT * FROM LAB3_CATEGORY_TABLE";
    public static final String SQL_ADD_NEW = "INSERT INTO LAB3_CATEGORY_TABLE (category_name) VALUES (?)";
    public static final String SQL_REMOVE = "DELETE FROM lab3_category_table where id = ?";
    @Autowired
    public CategoryDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<Category> getAll() throws DAOException {
        List<Category> categories = new ArrayList<>();
        log.info("Get all category's query:" + SQL_GET_ALL);
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_GET_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categories.add(Category.builder()
                                .id(resultSet.getInt("id"))
                                .categoryName(resultSet.getString("category_name"))
                                .build());
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting category's.", e);
        }
        return categories;
    }

    @Override
    public void save(Category category) throws DAOException {
        log.info("Add new category query:" + SQL_GET_ALL);
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_ADD_NEW)) {
            statement.setString(1, category.getCategoryName());
            int res = statement.executeUpdate();
            if (res == 0) {
                throw new DAOException("Cant add category for some reason, category:" + category);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while saving category:" + category, e);
        }
    }

    @Override
    public void remove(int id) throws DAOException {
        log.info("Remove category query:"+SQL_REMOVE);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_REMOVE)){
            statement.setInt(1,id);
            int res = statement.executeUpdate();
            if (res == 0) {
                throw new DAOException("Cant remove category for some reason, category with id:"+id);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while removing category with id:"+id,e);
        }
    }
}
