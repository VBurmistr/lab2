package nc.apps.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class Book extends BookBaseModel {
    private Author author;
    private Category category;
    private Language language;
    private Publisher publisher;
    private BookBaseModel prequel;

    public Book(Long id, String title, Author author, Category category, Language language, Publisher publisher, BookBaseModel prequel) {
        super(id, title);
        this.author = author;
        this.category = category;
        this.language = language;
        this.publisher = publisher;
        this.prequel = prequel;
    }

}
