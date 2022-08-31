package nc.apps.mappers;

import lombok.extern.slf4j.Slf4j;
import nc.apps.entities.Ordering;
import nc.apps.entities.OrderingBy;
import nc.apps.dto.SearchFiltersFromForm;
import nc.apps.utils.StringOperations;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@Slf4j
public class SpringDataSortMapping {
    public static Sort map(SearchFiltersFromForm filtersFromForm) {
        Sort sort = Sort.unsorted();
        if (!StringOperations.isNullOrEmpty(filtersFromForm.getOrderBy())
                && !filtersFromForm.getOrderBy().equals("0")) {
            try {
                int parsedInt = Integer.parseInt(filtersFromForm.getOrderBy());
                Optional<OrderingBy> orderingBy =
                        OrderingBy.valueOf(parsedInt);
                sort = orderingBy.map(by -> Sort.by(by.getJpaOrderingField()))
                        .orElseGet(() -> Sort.by(OrderingBy.TITLE.getJpaOrderingField()));
            } catch (Exception e) {
                log.info("Wrong orderingBy input");
            }
        } else {
            sort = Sort.by(OrderingBy.TITLE.getJpaOrderingField());
        }

        if (!StringOperations.isNullOrEmpty(filtersFromForm.getOrdering())) {
            try {
                int parsedInt = Integer.parseInt(filtersFromForm.getOrdering());
                Optional<Ordering> ordering =
                        Ordering.valueOf(parsedInt);
                if (ordering.isPresent()) {
                    if (ordering.get() == Ordering.ASC) {
                        sort = sort.ascending();
                    } else {
                        sort = sort.descending();
                    }
                }
            } catch (Exception e) {
                log.info("Wrong ordering input");
            }
        } else {
            sort = sort.descending();
        }
        return sort;
    }

}
