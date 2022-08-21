package nc.apps.services.daoServices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.AuthorDAO;
import nc.apps.dto.tabledtos.AuthorDTO;
import nc.apps.mappers.DTOToDomainMapper;
import nc.apps.mappers.DomainToDTOMapper;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.entities.Author;
import nc.apps.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDAO authorDAO;
    @Autowired
    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    public boolean save(AuthorDTO author) throws ServiceException {
        try {
            authorDAO.save(DTOToDomainMapper.mapAuthor(author));
            return true;
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public List<AuthorDTO> getAll() throws ServiceException {
        try {
            return DomainToDTOMapper.mapAuthors(authorDAO.getAll());
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
