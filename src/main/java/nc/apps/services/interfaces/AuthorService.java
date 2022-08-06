package nc.apps.services.interfaces;

import nc.apps.services.exceptions.ServiceException;
import nc.apps.entities.Author;

import java.util.List;

public interface AuthorService {
    boolean save(Author author) throws ServiceException;
    List<Author> getAll() throws ServiceException;
}
