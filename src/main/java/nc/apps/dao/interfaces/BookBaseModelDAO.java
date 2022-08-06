package nc.apps.dao.interfaces;

import nc.apps.dao.exception.DAOException;
import nc.apps.entities.BookBaseModel;

import java.util.List;

public interface BookBaseModelDAO {
    List<BookBaseModel> getAll() throws DAOException;
}
