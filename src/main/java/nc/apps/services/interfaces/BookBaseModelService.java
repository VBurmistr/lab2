package nc.apps.services.interfaces;

import nc.apps.dto.tabledtos.BookBaseModelDTO;
import nc.apps.services.exceptions.ServiceException;

import java.util.List;

public interface BookBaseModelService {
    List<BookBaseModelDTO> getAll() throws ServiceException;
}
