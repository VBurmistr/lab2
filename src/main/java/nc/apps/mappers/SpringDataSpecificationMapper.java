package nc.apps.mappers;

import nc.apps.dto.BookDBFilter;
import nc.apps.dto.Ordering;
import nc.apps.dto.OrderingBy;
import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.entities.Book;
import nc.apps.repositories.BookRepository;
import nc.apps.springdataspecifications.BookSpecifications;
import nc.apps.utils.StringOperations;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

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

        /*
        BookDBFilter filter = new BookDBFilter();
        filter.setCategory(parseStringValues(searchFiltersFromForm.getCategory()));
        filter.setLanguage(parseStringValues(searchFiltersFromForm.getLanguage()));
        filter.setAuthorName(parseStringValues(searchFiltersFromForm.getAuthorName()));
        filter.setTitle(parseStringValues(searchFiltersFromForm.getTitle()));
        filter.setPublisher(parseStringValues(searchFiltersFromForm.getPublisher()));
        if(!StringOperations.isNullOrEmpty(searchFiltersFromForm.getOrderBy())
                &&!searchFiltersFromForm.getOrderBy().equals("0")){
            try {
                int parsedInt = Integer.parseInt(searchFiltersFromForm.getOrderBy());
                Optional<OrderingBy> orderingBy =
                        OrderingBy.valueOf(parsedInt);
                orderingBy.ifPresent(filter::setOrderingBy);
            }catch (Exception e){
                log.info("Wrong orderingBy input");
            }
        }
        if(!StringOperations.isNullOrEmpty(searchFiltersFromForm.getOrdering())){
            try {
                int parsedInt = Integer.parseInt(searchFiltersFromForm.getOrdering());
                Optional<Ordering> ordering =
                        Ordering.valueOf(parsedInt);
                ordering.ifPresent(filter::setOrdering);
            }catch (Exception e){
                log.info("Wrong ordering input");
            }
        }
        filter.setLimit(rowLimit);
        filter.setOffset((searchFiltersFromForm.getPage()-1)*rowLimit);
        return filter;

         */
    }

    private static String parseStringValues(String value) {
        if (!StringOperations.isNullOrEmpty(value)) {
            return value;
        }
        return null;
    }
}
