package nc.apps.services.jpaservices;

import nc.apps.config.AppConfig;
import nc.apps.config.SecurityConfig;
import nc.apps.config.SpringDataConfig;
import nc.apps.config.SwaggerCustomConfig;
import nc.apps.dto.tabledtos.AuthorDTO;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, SpringDataConfig.class, SecurityConfig.class, SwaggerCustomConfig.class})
@Transactional
@Rollback
class AuthorServiceJPAImplTest {
    @Autowired
    @Qualifier("authorServiceJPAImpl")
    AuthorService authorService;

    @Test
    void testRemove() throws ServiceException {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setFirstName("testName");
        authorDTO.setLastName("testLastName");
        authorService.save(authorDTO);

        AuthorDTO authorDTOActual = authorService.getAll()
                .stream()
                .filter(a -> a.getFirstName().equals("testName"))
                .findFirst().orElseThrow(() -> new RuntimeException("Expected one author but get zero"));
        assertNotNull(authorDTOActual.getId());
        authorService.remove(authorDTOActual.getId());

        assertThrows(RuntimeException.class, () -> {
            authorService.getAll()
                    .stream()
                    .filter(a -> a.getFirstName().equals("testName"))
                    .findFirst().orElseThrow(() -> new RuntimeException("Expected one author but get zero"));
        });
    }
}