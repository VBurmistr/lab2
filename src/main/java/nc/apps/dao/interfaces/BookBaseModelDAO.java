package nc.apps.dao.interfaces;

import nc.apps.dao.exception.DAOException;
import nc.apps.entities.domain.BookBaseModel;

import java.util.List;

public interface BookBaseModelDAO {
    List<BookBaseModel> getAll() throws DAOException;
}
