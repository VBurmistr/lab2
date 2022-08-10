package nc.apps.restcontrollers;

import nc.apps.entities.Category;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/")
public class CategoryRestController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add/")
    public ResponseEntity addCategory(@RequestBody Category category) throws ServiceException {
        boolean result = categoryService.save(category);
        if(result){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getall/")
    public ResponseEntity<List<Category>> getAllCategories() throws ServiceException {
        List<Category> categories = categoryService.getAll();
        if(categories!=null){
            return new ResponseEntity<>(categories,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
