package nc.apps.services.jpaservices;

import nc.apps.config.AppConfig;
import nc.apps.config.SecurityConfig;
import nc.apps.config.SpringDataConfig;
import nc.apps.dto.tabledtos.AuthorDTO;
import nc.apps.entities.Author;
import nc.apps.repositories.AuthorRepository;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.AuthorService;
import nc.apps.services.jpaservices.AuthorServiceJPAImpl;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@ActiveProfiles("dev")
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, SpringDataConfig.class, SecurityConfig.class})
@Transactional
@Rollback
class AuthorServiceJPAImplTest {
    @Autowired
    @Qualifier("authorServiceJPAImpl")
    AuthorService authorService;
    @Autowired
    AuthorRepository authorRepository;
    @Test
    void getBookById() throws ServiceException {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setFirstName("testName");
        authorDTO.setLastName("testLastName");
        authorService.save(authorDTO);
        Optional<Author> author = authorRepository.findByFirstNameAndLastName("testName","testLastName");
        assertTrue(author.isPresent());
        authorService.remove(author.get().getId());
        Optional<Author> author1 = authorRepository.findByFirstNameAndLastName("testName","testLastName");
        assertFalse(author1.isPresent());
    }
}