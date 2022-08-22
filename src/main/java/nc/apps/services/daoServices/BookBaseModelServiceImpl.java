package nc.apps.services.daoServices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.BookBaseModelDAO;
import nc.apps.dto.tabledtos.BookBaseModelDTO;
import nc.apps.mappers.DomainToDTOMapper;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookBaseModelService;
import nc.apps.entities.BookBaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookBaseModelServiceImpl implements BookBaseModelService {
    BookBaseModelDAO bookBaseModelDAO;
    @Autowired
    public BookBaseModelServiceImpl( BookBaseModelDAO bookBaseModelDAO) {
        this.bookBaseModelDAO = bookBaseModelDAO;
    }

    public List<BookBaseModelDTO> getAll() throws ServiceException {
        try {
            return DomainToDTOMapper.mapBaseModels(bookBaseModelDAO.getAll());
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
