package nc.apps.services.daoServices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.BookBaseModelDAO;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookBaseModelService;
import nc.apps.entities.BookBaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
public class BookBaseModelServiceImpl implements BookBaseModelService {
    BookBaseModelDAO bookBaseModelDAO;
    @Autowired
    public BookBaseModelServiceImpl( BookBaseModelDAO bookBaseModelDAO) {
        this.bookBaseModelDAO = bookBaseModelDAO;
    }

    public List<BookBaseModel> getAll() throws ServiceException {
        try {
            return  bookBaseModelDAO.getAll();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
