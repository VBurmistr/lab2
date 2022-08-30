package nc.apps.dao.interfaces;

import nc.apps.dao.exception.DAOException;
import nc.apps.entities.Publisher;

import java.util.List;

public interface PublisherDAO {
    List<Publisher> getAll() throws DAOException;
    void save(Publisher publisher) throws DAOException;
    void remove(int id) throws DAOException;

}
