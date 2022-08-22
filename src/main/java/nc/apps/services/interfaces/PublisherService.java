package nc.apps.services.interfaces;

import nc.apps.dto.tabledtos.LanguageDTO;
import nc.apps.dto.tabledtos.PublisherDTO;
import nc.apps.entities.Publisher;
import nc.apps.services.exceptions.ServiceException;

import java.util.List;

public interface PublisherService {
    void save(PublisherDTO publisher) throws ServiceException;
    List<PublisherDTO> getAll() throws ServiceException;
    void remove(int id) throws ServiceException;
}
