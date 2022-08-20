package nc.apps.services.jpaservices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.AuthorDAO;
import nc.apps.dto.tabledtos.AuthorDTO;
import nc.apps.entities.Author;
import nc.apps.mappers.DTOToDomainMapper;
import nc.apps.mappers.DomainToDTOMapper;
import nc.apps.repositories.AuthorRepository;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthorServiceJPAImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceJPAImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public boolean save(AuthorDTO author) throws ServiceException {
        try {
            authorRepository.save(DTOToDomainMapper.mapAuthor(author));
            return true;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public List<AuthorDTO> getAll() throws ServiceException {
        try {
            return DomainToDTOMapper.mapAuthors(authorRepository.findAll());
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }
}
