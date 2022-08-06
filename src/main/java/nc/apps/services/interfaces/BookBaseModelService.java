package nc.apps.services.interfaces;

import nc.apps.services.exceptions.ServiceException;
import nc.apps.entities.BookBaseModel;

import java.util.List;

public interface BookBaseModelService {
    List<BookBaseModel> getAll() throws ServiceException;
}
