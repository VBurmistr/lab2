package nc.apps.services.interfaces;

import nc.apps.entities.Publisher;
import nc.apps.services.exceptions.ServiceException;

import java.util.List;

public interface PublisherService {
    boolean save(Publisher publisher) throws ServiceException;
    List<Publisher> getAll() throws ServiceException;
}
