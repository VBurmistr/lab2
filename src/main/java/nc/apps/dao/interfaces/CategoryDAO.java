package nc.apps.dao.interfaces;

import nc.apps.dao.exception.DAOException;
import nc.apps.entities.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getAll() throws DAOException;
    void save(Category category) throws DAOException;
}
