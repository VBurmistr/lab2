package nc.apps.services.jpaservices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.PublisherDAO;
import nc.apps.entities.Publisher;
import nc.apps.repositories.PublisherRepository;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
public class PublisherServiceJPAImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceJPAImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public boolean save(Publisher publisher) throws ServiceException {
        try {
            publisherRepository.save(publisher);
            return true;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public List<Publisher> getAll() throws ServiceException {
        try {
            return publisherRepository.findAll();
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }
}
