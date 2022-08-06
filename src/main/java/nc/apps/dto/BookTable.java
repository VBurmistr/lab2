package nc.apps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import nc.apps.entities.Book;

import java.util.List;

@Data
@AllArgsConstructor
public class BookTable {
    private List<Book> books;
    private long totalPages;
    private long currentPage;
}
