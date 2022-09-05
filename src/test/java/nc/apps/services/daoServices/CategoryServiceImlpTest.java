package nc.apps.services.daoServices;

import lombok.ToString;
import nc.apps.config.AppConfig;
import nc.apps.config.SecurityConfig;
import nc.apps.config.SpringDataConfig;
import nc.apps.config.SwaggerCustomConfig;
import nc.apps.dto.tabledtos.AuthorDTO;
import nc.apps.dto.tabledtos.CategoryDTO;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.AuthorService;
import nc.apps.services.interfaces.CategoryService;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, SpringDataConfig.class, SecurityConfig.class, SwaggerCustomConfig.class})
@Rollback
@Transactional
class CategoryServiceImlpTest {
    @Autowired
    @Qualifier("categoryServiceImpl")
    CategoryService categoryService;

    @Autowired
    DataSource dataSource;
    @Test
    void testAllFunctional() throws ServiceException {
        categoryService.save(CategoryDTO.builder()
                .categoryName("testName").build());
        CategoryDTO categoryDTO = categoryService.getAll()
                .stream()
                .filter(a->a.getCategoryName().equals("testName"))
                .findFirst().orElseThrow(()->new RuntimeException("Expected one category but get zero"));
        assertNotNull(categoryDTO.getId());
        categoryService.remove(categoryDTO.getId());
    }
}
