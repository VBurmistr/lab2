package nc.apps.entities.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@Table(name = "lab3_language_table",
        uniqueConstraints = {
                @UniqueConstraint(name = "lab3_language_table_language_unique",
                        columnNames = "language")
        })
@Check(constraints = "language <> ''")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Book> books;

    public Language(String languageName) {
        this.languageName = languageName;
    }

    @Column(name = "language")
    private String languageName;
}
