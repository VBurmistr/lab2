package nc.apps.controllers;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dto.ErrorObject;
import nc.apps.dto.ResponseObject;
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
        ex.printStackTrace();
        model.addAttribute("exMsg", ex.getMessage());
        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("statusCode", 404);
        return "errorPage";
    }

    @ExceptionHandler({ServiceException.class, DAOException.class})
    public ResponseEntity<ResponseObject<ErrorObject>> handleServiceException(HttpServletRequest req, Exception ex) {
        log.error("Service/DAO exception.", ex);
        ResponseObject<ErrorObject> responseObject = new ResponseObject<>();
        responseObject.setSuccess(false);
        responseObject.setResponseBody(new ErrorObject(500,
                getRootCause(ex).getMessage(),
                System.currentTimeMillis()));
        return new ResponseEntity<>(responseObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static Throwable getRootCause(Throwable e) {
        if (e.getCause() == null) return e;
        return getRootCause(e.getCause());
    }
}
