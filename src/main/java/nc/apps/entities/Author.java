package nc.apps.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "lab3_author_table",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name", "last_name"},
                name = "lab3_author_table_first_name_last_name_key")})
@Check(constraints = "first_name <> '' AND last_name <> ''")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;
}
