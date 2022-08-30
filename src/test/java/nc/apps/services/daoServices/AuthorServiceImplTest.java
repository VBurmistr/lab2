package nc.apps.services.daoServices;

import lombok.ToString;
import nc.apps.config.AppConfig;
import nc.apps.config.SecurityConfig;
import nc.apps.config.SpringDataConfig;
import nc.apps.config.SwaggerCustomConfig;
import nc.apps.dto.tabledtos.AuthorDTO;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.AuthorService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, SpringDataConfig.class, SecurityConfig.class, SwaggerCustomConfig.class})
@Rollback
@Transactional
class AuthorServiceImplTest {
    @Autowired
    @Qualifier("authorServiceImpl")
    AuthorService authorServiceDAO;

    @Test
    void testAllFunctional() throws ServiceException {
        authorServiceDAO.save(AuthorDTO.builder()
                .firstName("testFirstName12")
                .lastName("testLastName").build());
        AuthorDTO authorDTO = authorServiceDAO.getAll()
                .stream()
                .filter(a->a.getFirstName().equals("testFirstName12"))
                .findFirst().orElseThrow(()->new RuntimeException("Expected one author but get zero"));
        assertNotNull(authorDTO.getId());
        authorServiceDAO.remove(authorDTO.getId());
    }
}