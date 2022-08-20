package nc.apps.services.daoServices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.PublisherDAO;
import nc.apps.dto.tabledtos.PublisherDTO;
import nc.apps.mappers.DTOToDomainMapper;
import nc.apps.mappers.DomainToDTOMapper;
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

    public boolean save(PublisherDTO publisher) throws ServiceException {
        try {
            publisherDAO.save(DTOToDomainMapper.mapPublisher(publisher));
            return true;
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public List<PublisherDTO> getAll() throws ServiceException {
        try {
            return DomainToDTOMapper.mapPublishers(publisherDAO.getAll());
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
