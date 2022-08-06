package nc.apps.controllers;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dto.ErrorObject;
import nc.apps.services.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public String handleError(Model model, HttpServletRequest req, Exception ex) {
        model.addAttribute("exMsg", ex.getMessage());
        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("statusCode", 404);
        return "errorPage";
    }
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorObject> handleServiceException(HttpServletRequest req, Exception ex) {
        log.error("Service exception.",ex);
        return new ResponseEntity<ErrorObject>(
                new ErrorObject(500,ex.getMessage(),
                        System.currentTimeMillis()),
                HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

}
