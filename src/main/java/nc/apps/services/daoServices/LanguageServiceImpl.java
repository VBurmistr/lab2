package nc.apps.services.daoServices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.LanguageDAO;
import nc.apps.dto.tabledtos.LanguageDTO;
import nc.apps.entities.Language;
import nc.apps.mappers.DTOToDomainMapper;
import nc.apps.mappers.DomainToDTOMapper;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageDAO languageDAO;

    @Autowired
    public LanguageServiceImpl(LanguageDAO languageDAO) {
        this.languageDAO = languageDAO;
    }

    public void save(LanguageDTO language) throws ServiceException {
        try {
            languageDAO.save(DTOToDomainMapper.mapLanguage(language));
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public List<LanguageDTO> getAll() throws ServiceException {
        try {
            return DomainToDTOMapper.mapLanguages(languageDAO.getAll());
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void remove(int id) throws ServiceException {
        try {
            languageDAO.remove(id);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
