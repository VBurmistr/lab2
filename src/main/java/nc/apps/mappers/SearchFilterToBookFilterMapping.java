package nc.apps.mappers;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dto.BookDBFilter;
import nc.apps.dto.Ordering;
import nc.apps.dto.OrderingBy;
import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.utils.StringOperations;

import java.util.Optional;
@Slf4j
public class SearchFilterToBookFilterMapping {
    public static BookDBFilter map(SearchFiltersFromForm searchFiltersFromForm, int rowLimit){
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
    }

    private static String parseStringValues(String value){
        if(!StringOperations.isNullOrEmpty(value)){
            return value;
        }
        return null;
    }
}
