package nc.apps.dao.interfaces;

import nc.apps.dao.exception.DAOException;
import nc.apps.entities.Language;

import java.util.List;

public interface LanguageDAO {
    List<Language> getAll() throws DAOException;
    void save(Language language) throws DAOException ;
    void remove(int id) throws DAOException;

}
