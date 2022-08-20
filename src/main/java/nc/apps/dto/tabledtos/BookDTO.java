package nc.apps.dto.tabledtos;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nc.apps.entities.Publisher;

@Getter
@Setter
@Builder
public class BookDTO {
    private Integer id;
    private String title;
    private AuthorDTO author;
    private CategoryDTO category;
    private PublisherDTO publisher;
    private LanguageDTO language;
    private BookBaseModelDTO prequel;
}
