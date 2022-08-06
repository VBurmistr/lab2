package nc.apps.services.interfaces;

import nc.apps.services.exceptions.ServiceException;
import nc.apps.entities.Category;

import java.util.List;

public interface CategoryService {
    boolean save(Category category) throws ServiceException;
    List<Category> getAll() throws ServiceException;
}
