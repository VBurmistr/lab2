package nc.apps.controllers.mvccontrollers;

import lombok.extern.slf4j.Slf4j;
import nc.apps.propertyholder.SmartAdderPropertyHolder;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Context;
import java.security.Principal;

@Slf4j
@Controller
public class MainViewController {
    private final BookService bookService;
    private final SmartAdderPropertyHolder propertyHolder;

    @Autowired
    public MainViewController(BookService bookService,
                              SmartAdderPropertyHolder propertyHolder) {
        this.bookService = bookService;
        this.propertyHolder = propertyHolder;
    }

    @GetMapping(path = "/searchbooks")
    public String searchBooks() {
        return "searchBooks";
    }

    @GetMapping(path = "/addnewbook")
    public String addNewBook() {
        return "addNewBook";
    }

    @GetMapping(path = "/editbook/{id}")
    public ModelAndView editBook(@PathVariable int id) throws ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("book", bookService.getBookById(id));
        modelAndView.setViewName("editBook");
        return modelAndView;
    }

    @GetMapping(path = "/addauthor")
    public String addAuthor() {
        return "addAuthor";
    }

    @GetMapping(path = "/addcategory")
    public String addCategory() {
        return "addCategory";
    }

    @GetMapping(path = "/addlanguage")
    public String addLanguage() {
        return "addLanguage";
    }

    @GetMapping(path = "/addpublisher")
    public String addPublisher() {
        return "addPublisher";
    }

    @GetMapping(path = "/manageauthors")
    public String manageAuthors() {
        return "manageAuthors";
    }

    @GetMapping(path = "/managecategorys")
    public String manageCategorys() {
        return "manageCategorys";
    }

    @GetMapping(path = "/managelanguages")
    public String manageLanguages() {
        return "manageLanguages";
    }

    @GetMapping(path = "/managepublishers")
    public String managePublishers() {
        return "managePublishers";
    }


    @GetMapping(path = "/smartadder")
    public ModelAndView addBookSmart() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("smartAdderProperty", propertyHolder);
        modelAndView.setViewName("bookSmartAdder");
        return modelAndView;
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
