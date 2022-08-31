package nc.apps.dto.tabledtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookTable {
    private List<BookDTO> books;
    private long totalPages;
    private long currentPage;
}
