package nc.apps.services.jpaservices;

import nc.apps.config.AppConfig;
import nc.apps.config.SecurityConfig;
import nc.apps.config.SpringDataConfig;
import nc.apps.config.SwaggerCustomConfig;
import nc.apps.dto.tabledtos.CategoryDTO;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.CategoryService;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, SpringDataConfig.class, SecurityConfig.class, SwaggerCustomConfig.class})
@Transactional
@Rollback
class CategoryServiceJPAImplTest {
    @Autowired
    @Qualifier("categoryServiceImpl")
    CategoryService categoryService;

    @Test
    void testRemove() throws ServiceException {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .categoryName("testName")
                .build();
        categoryService.save(categoryDTO);

        CategoryDTO categoryDTOActual = categoryService.getAll()
                .stream()
                .filter(a -> a.getCategoryName().equals("testName"))
                .findFirst().orElseThrow(() -> new RuntimeException("Expected one category but get zero"));
        assertNotNull(categoryDTOActual.getId());
        categoryService.remove(categoryDTOActual.getId());
        assertThrows(RuntimeException.class, () -> {
            categoryService.getAll()
                    .stream()
                    .filter(a -> a.getCategoryName().equals("testName"))
                    .findFirst().orElseThrow(() -> new RuntimeException("Expected one category but get zero"));
        });
    }
}