package nc.apps.services.interfaces;

import nc.apps.dto.tabledtos.AuthorDTO;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.entities.Author;

import java.util.List;

public interface AuthorService {
    boolean save(AuthorDTO author) throws ServiceException;
    List<AuthorDTO> getAll() throws ServiceException;
}
