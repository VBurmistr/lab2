package nc.apps.services.jpaservices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.PublisherDAO;
import nc.apps.dto.tabledtos.PublisherDTO;
import nc.apps.entities.Publisher;
import nc.apps.mappers.DTOToDomainMapper;
import nc.apps.mappers.DomainToDTOMapper;
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

    public void save(PublisherDTO publisher) throws ServiceException {
        try {
            publisherRepository.save(DTOToDomainMapper.mapPublisher(publisher));
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public List<PublisherDTO> getAll() throws ServiceException {
        try {
            return DomainToDTOMapper.mapPublishers(publisherRepository.findAll());
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void remove(int id) throws ServiceException {
        try {
            publisherRepository.deleteById(id);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }
}
