package nc.apps.services.interfaces;

import nc.apps.dto.tabledtos.LanguageDTO;
import nc.apps.entities.Language;
import nc.apps.services.exceptions.ServiceException;

import java.util.List;

public interface LanguageService {
    boolean save(LanguageDTO language) throws ServiceException;
    List<LanguageDTO> getAll() throws ServiceException;
}
