package nc.apps.controllers.restcontrollers;

import nc.apps.dto.ResponseObject;
import nc.apps.dto.tabledtos.CategoryDTO;
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
    public ResponseEntity addCategory(@RequestBody CategoryDTO category) throws ServiceException {
        categoryService.save(category);
        ResponseObject obj = new ResponseObject();
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping("/getall/")
    public ResponseEntity<ResponseObject<List<CategoryDTO>>> getAllCategories() throws ServiceException {
        List<CategoryDTO> categories = categoryService.getAll();
        ResponseObject<List<CategoryDTO>> obj = new ResponseObject<>();
        obj.setResponseBody(categories);
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @GetMapping(value ="/remove/{id}")
    public ResponseEntity<ResponseObject> removeCategory(@PathVariable Integer id) throws ServiceException {
        categoryService.remove(id);
        ResponseObject obj = new ResponseObject<>();
        obj.setSuccess(true);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }
}
