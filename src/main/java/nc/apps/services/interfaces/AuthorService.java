package nc.apps.services.interfaces;

import nc.apps.dto.tabledtos.AuthorDTO;
import nc.apps.services.exceptions.ServiceException;

import java.util.List;

public interface AuthorService {
    void save(AuthorDTO author) throws ServiceException;
    List<AuthorDTO> getAll() throws ServiceException;
    void remove(int id) throws ServiceException;

}
