package nc.apps.services.interfaces;

import nc.apps.dto.tabledtos.LanguageDTO;
import nc.apps.services.exceptions.ServiceException;

import java.util.List;

public interface LanguageService {
    void save(LanguageDTO language) throws ServiceException;
    List<LanguageDTO> getAll() throws ServiceException;
    void remove(int id) throws ServiceException;

}
