package nc.apps.services.daoServices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.CategoryDAO;
import nc.apps.dto.tabledtos.CategoryDTO;
import nc.apps.entities.Category;
import nc.apps.mappers.DTOToDomainMapper;
import nc.apps.mappers.DomainToDTOMapper;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO;
    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public void save(CategoryDTO category) throws ServiceException {
        try {
            categoryDAO.save(DTOToDomainMapper.mapCategory(category));
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public List<CategoryDTO> getAll() throws ServiceException {
        try {
            return DomainToDTOMapper.mapCategories(categoryDAO.getAll());
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void remove(int id) throws ServiceException {
        throw new ServiceException("Unimplemented operation");
    }
}
