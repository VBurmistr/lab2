package nc.apps.services.jpaservices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.CategoryDAO;
import nc.apps.dto.tabledtos.CategoryDTO;
import nc.apps.entities.Category;
import nc.apps.mappers.DTOToDomainMapper;
import nc.apps.mappers.DomainToDTOMapper;
import nc.apps.repositories.CategoryRepository;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryServiceJPAImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryServiceJPAImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public boolean save(CategoryDTO category) throws ServiceException {
        try {
            categoryRepository.save(DTOToDomainMapper.mapCategory(category));
            return true;
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public List<CategoryDTO> getAll() throws ServiceException {
        try {
            return DomainToDTOMapper.mapCategories(categoryRepository.findAll());
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }
}
