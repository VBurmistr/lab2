package nc.apps.mappers;

import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.entities.domain.Book;
import nc.apps.springdataspecifications.BookSpecifications;
import nc.apps.utils.StringOperations;
import org.springframework.data.jpa.domain.Specification;

public class SpringDataSpecificationMapper {
    public static Specification<Book> mapSpecs(SearchFiltersFromForm filtersFromForm) {
        Specification<Book> specs = null;
        if (filtersFromForm.getTitle() != null &&
                !filtersFromForm.getTitle().isEmpty()) {
            Specification<Book> title = BookSpecifications
                    .hasTitle(parseStringValues("%" + filtersFromForm.getTitle() + "%"));
            specs = title;
        }
        if (filtersFromForm.getAuthorName() != null &&
                !filtersFromForm.getAuthorName().isEmpty()) {
            Specification<Book> authorName = BookSpecifications
                    .hasAuthorFirstName(parseStringValues("%" + filtersFromForm.getAuthorName() + "%"));
            Specification<Book> authorLastName = BookSpecifications
                    .hasAuthorLastName(parseStringValues("%" + filtersFromForm.getAuthorName() + "%"));
            if (specs != null) {
                specs.or(authorName);
            } else {
                specs = authorName.or(authorLastName);
            }
        }
        if (filtersFromForm.getCategory() != null &&
                !filtersFromForm.getCategory().isEmpty()) {
            Specification<Book> category = BookSpecifications
                    .hasCategory(parseStringValues("%" + filtersFromForm.getCategory() + "%"));
            if (specs != null) {
                specs.or(category);
            } else {
                specs = category;
            }
        }

        if (filtersFromForm.getPublisher() != null &&
                !filtersFromForm.getPublisher().isEmpty()) {
            Specification<Book> publisher = BookSpecifications
                    .hasPublisher(parseStringValues("%" + filtersFromForm.getPublisher() + "%"));
            if (specs != null) {
                specs.or(publisher);
            } else {
                specs = publisher;
            }
        }
        if (filtersFromForm.getLanguage() != null &&
                !filtersFromForm.getLanguage().isEmpty()) {
            Specification<Book> language = BookSpecifications
                    .hasLanguage(parseStringValues("%" + filtersFromForm.getLanguage() + "%"));
            if (specs != null) {
                specs.or(language);
            } else {
                specs = language;
            }
        }

        return specs;
    }

    private static String parseStringValues(String value) {
        if (!StringOperations.isNullOrEmpty(value)) {
            return value;
        }
        return null;
    }
}
