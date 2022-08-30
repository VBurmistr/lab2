package nc.apps.dao.interfaces;

import nc.apps.dao.exception.DAOException;
import nc.apps.entities.Author;

import java.util.List;

public interface AuthorDAO {
    List<Author> getAll() throws DAOException;
    void save(Author author) throws DAOException;
    void remove(int id) throws DAOException;
}
