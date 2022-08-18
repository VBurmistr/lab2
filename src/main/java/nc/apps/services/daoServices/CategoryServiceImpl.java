package nc.apps.services.daoServices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.CategoryDAO;
import nc.apps.entities.Category;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
public class CategoryServiceImpl implements CategoryService {
    CategoryDAO categoryDAO;
    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public boolean save(Category category) throws ServiceException {
        try {
            categoryDAO.save(category);
            return true;
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public List<Category> getAll() throws ServiceException {
        try {
            return categoryDAO.getAll();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
