package nc.apps.services.jpaservices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.LanguageDAO;
import nc.apps.dto.tabledtos.LanguageDTO;
import nc.apps.entities.Language;
import nc.apps.mappers.DTOToDomainMapper;
import nc.apps.mappers.DomainToDTOMapper;
import nc.apps.repositories.LanguageRepository;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
public class LanguageServiceJPAImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageServiceJPAImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void save(LanguageDTO language) throws ServiceException {
        try {
            languageRepository.save(DTOToDomainMapper.mapLanguage(language));
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<LanguageDTO> getAll() throws ServiceException {
        try {
            return DomainToDTOMapper.mapLanguages(languageRepository.findAll());
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void remove(int id) throws ServiceException {
        try {
            languageRepository.deleteById(id);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }
}
