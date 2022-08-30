package nc.apps.services.jpaservices;

import nc.apps.config.AppConfig;
import nc.apps.config.SecurityConfig;
import nc.apps.config.SpringDataConfig;
import nc.apps.config.SwaggerCustomConfig;
import nc.apps.dto.tabledtos.AuthorDTO;
import nc.apps.dto.tabledtos.LanguageDTO;
import nc.apps.dto.tabledtos.PublisherDTO;
import nc.apps.entities.Author;
import nc.apps.repositories.AuthorRepository;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.AuthorService;
import nc.apps.services.interfaces.PublisherService;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith({SpringExtension.class})
@ActiveProfiles("dev")
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, SpringDataConfig.class, SecurityConfig.class, SwaggerCustomConfig.class})
@Transactional
@Rollback
class PublisherServiceJPAImplTest {
    @Autowired
    @Qualifier("publisherServiceJPAImpl")
    PublisherService publisherService;

    @Test
    void testRemove() throws ServiceException {
        PublisherDTO publisherDTO = PublisherDTO.builder()
                .publisherName("testName")
                .build();
        publisherService.save(publisherDTO);

        PublisherDTO publisherDTOActual = publisherService.getAll()
                .stream()
                .filter(a -> a.getPublisherName().equals("testName"))
                .findFirst().orElseThrow(() -> new RuntimeException("Expected one publisher but get zero"));
        assertNotNull(publisherDTOActual.getId());
        publisherService.remove(publisherDTOActual.getId());
        assertThrows(RuntimeException.class, () -> {
            publisherService.getAll()
                    .stream()
                    .filter(a -> a.getPublisherName().equals("testName"))
                    .findFirst().orElseThrow(() -> new RuntimeException("Expected one publisher but get zero"));
        });
    }
}