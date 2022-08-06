package nc.apps.services;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.AuthorDAO;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.entities.Author;
import nc.apps.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {
    AuthorDAO authorDAO;
    @Autowired
    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    public boolean save(Author author) throws ServiceException {
        try {
            authorDAO.save(author);
            return true;
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public List<Author> getAll() throws ServiceException {
        try {
            return  authorDAO.getAll();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
