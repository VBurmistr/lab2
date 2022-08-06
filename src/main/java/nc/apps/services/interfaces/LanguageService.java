package nc.apps.services.interfaces;

import nc.apps.entities.Language;
import nc.apps.services.exceptions.ServiceException;

import java.util.List;

public interface LanguageService {
    boolean save(Language language) throws ServiceException;
    List<Language> getAll() throws ServiceException;
}
