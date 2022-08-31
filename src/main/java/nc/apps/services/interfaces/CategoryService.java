package nc.apps.services.interfaces;

import nc.apps.dto.tabledtos.CategoryDTO;
import nc.apps.services.exceptions.ServiceException;

import java.util.List;

public interface CategoryService {
    void save(CategoryDTO category) throws ServiceException;
    List<CategoryDTO> getAll() throws ServiceException;
    void remove(int id) throws ServiceException;
}
