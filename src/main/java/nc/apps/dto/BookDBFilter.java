package nc.apps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDBFilter {
    private String title;
    private String authorName;
    private String category;
    private String language;
    private String publisher;
    private int limit;
    private int offset;
    private OrderingBy orderingBy;
    private Ordering ordering;

}
