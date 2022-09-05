package nc.apps.services.daoServices;

import nc.apps.config.AppConfig;
import nc.apps.config.SecurityConfig;
import nc.apps.config.SpringDataConfig;
import nc.apps.config.SwaggerCustomConfig;
import nc.apps.dto.tabledtos.CategoryDTO;
import nc.apps.dto.tabledtos.PublisherDTO;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.CategoryService;
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
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, SpringDataConfig.class, SecurityConfig.class, SwaggerCustomConfig.class})
@Rollback
@Transactional
class PublisherServiceImlpTest {
    @Autowired
    @Qualifier("publisherServiceImpl")
    PublisherService publisherService;

    @Test
    void testAllFunctional() throws ServiceException {
        publisherService.save(PublisherDTO.builder()
                .publisherName("testName").build());
        PublisherDTO publisherDTO = publisherService.getAll()
                .stream()
                .filter(a->a.getPublisherName().equals("testName"))
                .findFirst().orElseThrow(()->new RuntimeException("Expected one publisher but get zero"));
        assertNotNull(publisherDTO.getId());
        publisherService.remove(publisherDTO.getId());
    }
}
