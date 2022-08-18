package nc.apps.services.jpaservices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.AuthorDAO;
import nc.apps.entities.Author;
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

    public boolean save(Author author) throws ServiceException {
        try {
            authorRepository.save(author);
            return true;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public List<Author> getAll() throws ServiceException {
        try {
            return authorRepository.findAll();
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }
}
