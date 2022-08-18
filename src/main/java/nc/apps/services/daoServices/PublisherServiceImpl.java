package nc.apps.services.daoServices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.PublisherDAO;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.PublisherService;
import nc.apps.entities.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
public class PublisherServiceImpl implements PublisherService {
    PublisherDAO publisherDAO;

    @Autowired
    public PublisherServiceImpl(PublisherDAO publisherDAO) {
        this.publisherDAO = publisherDAO;
    }

    public boolean save(Publisher publisher) throws ServiceException {
        try {
            publisherDAO.save(publisher);
            return true;
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public List<Publisher> getAll() throws ServiceException {
        try {
            return  publisherDAO.getAll();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
