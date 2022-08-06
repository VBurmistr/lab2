package nc.apps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFiltersFromForm {
    private int page;
    private String title;
    private String authorName;
    private String category;
    private String language;
    private String publisher;
    private String orderBy;
    private String ordering;
}
