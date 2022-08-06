package nc.apps.controllers;

import lombok.extern.slf4j.Slf4j;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Slf4j
@Controller
public class MainViewController {
   @Autowired
   BookService bookService;

    @GetMapping(path = "/searchbooks")
    public String searchBooks(){
        return "searchBooks";
    }

    @GetMapping(path = "/addnewbook")
    public String addNewBook(){
        return "addNewBook";
    }

    @GetMapping(path = "/editbook/{id}")
    public ModelAndView editBook(@PathVariable int id) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("book",bookService.getBookById(id));
        modelAndView.setViewName("editBook");
        return modelAndView;
    }

    @GetMapping(path = "/addauthor")
    public String addAuthor(){
        return "addAuthor";
    }

    @GetMapping(path = "/addcategory")
    public String addCategory(){
        return "addCategory";
    }

    @GetMapping(path = "/addlanguage")
    public String addLanguage(){
        return "addLanguage";
    }

    @GetMapping(path = "/addpublisher")
    public String addPublisher(){
        return "addPublisher";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal user) {
        ModelAndView model = new ModelAndView();
        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }
        model.setViewName("403");
        return model;
    }
}