package nc.apps.entities.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true)
@Table(name = "lab3_book_table",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "author_id"},
                name = "lab3_book_table_title_author_id_key")})
@Entity
@Check(constraints = "id <> prequel_id AND title <> ''")
public class Book extends BookBaseModel {
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false, foreignKey = @ForeignKey(name = "lab3_book_table_author_id_fkey"))
    private Author author;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "lab3_book_table_category_id_fkey"))
    private Category category;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id", nullable = false, foreignKey = @ForeignKey(name = "lab3_book_table_language_id_fkey"))
    private Language language;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id", nullable = false, foreignKey = @ForeignKey(name = "lab3_book_table_publisher_id_fkey"))
    private Publisher publisher;
    @ToString.Exclude
    @JoinColumn(name = "prequel_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Book prequel;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "prequel", fetch = FetchType.LAZY)
    private Set<Book> childs = new HashSet<>();
    @Builder
    public Book(Integer id, String title, Author author, Category category, Language language, Publisher publisher, Book prequel) {
        super(id, title);
        this.author = author;
        this.category = category;
        this.language = language;
        this.publisher = publisher;
        this.prequel = prequel;
    }

}
