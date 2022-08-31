package nc.apps.services.jpaservices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dto.tabledtos.CategoryDTO;
import nc.apps.mappers.DTOToDomainMapper;
import nc.apps.mappers.DomainToDTOMapper;
import nc.apps.repositories.CategoryRepository;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.CategoryService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
public class CategoryServiceJPAImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryServiceJPAImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public void save(CategoryDTO category) throws ServiceException {
        try {
            categoryRepository.save(DTOToDomainMapper.mapCategory(category));
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }
    @Override
    public List<CategoryDTO> getAll() throws ServiceException {
        try {
            return DomainToDTOMapper.mapCategories(categoryRepository.findAll());
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }
    @Override
    public void remove(int id) throws ServiceException {
        try {
            categoryRepository.deleteById(id);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }
}
