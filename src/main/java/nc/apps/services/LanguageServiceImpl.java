package nc.apps.services;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.LanguageDAO;
import nc.apps.entities.Language;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LanguageServiceImpl implements LanguageService {
    LanguageDAO languageDAO;

    @Autowired
    public LanguageServiceImpl(LanguageDAO languageDAO) {
        this.languageDAO = languageDAO;
    }

    public boolean save(Language language) throws ServiceException {
        try {
            languageDAO.save(language);
            return true;
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public List<Language> getAll() throws ServiceException {
        try {
            return  languageDAO.getAll();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
