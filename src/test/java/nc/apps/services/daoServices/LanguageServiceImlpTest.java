package nc.apps.services.daoServices;

import nc.apps.config.AppConfig;
import nc.apps.config.SecurityConfig;
import nc.apps.config.SpringDataConfig;
import nc.apps.config.SwaggerCustomConfig;
import nc.apps.dto.tabledtos.CategoryDTO;
import nc.apps.dto.tabledtos.LanguageDTO;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.CategoryService;
import nc.apps.services.interfaces.LanguageService;
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
@ActiveProfiles("dev")
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, SpringDataConfig.class, SecurityConfig.class, SwaggerCustomConfig.class})
@Rollback
@Transactional

class LanguageServiceImlpTest {
    @Autowired
    @Qualifier("languageServiceImpl")
    LanguageService languageService;

    @Test
    void testAllFunctional() throws ServiceException {
        languageService.save(LanguageDTO.builder()
                .languageName("testName").build());
        LanguageDTO languageDTO = languageService.getAll()
                .stream()
                .filter(a->a.getLanguageName().equals("testName"))
                .findFirst().orElseThrow(()->new RuntimeException("Expected one language but get zero"));
        assertNotNull(languageDTO.getId());
        languageService.remove(languageDTO.getId());
    }
}
