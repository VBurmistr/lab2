package nc.apps.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "lab3_publisher_table",
        uniqueConstraints = {
                @UniqueConstraint(name = "lab3_publisher_table_publisher_name",
                        columnNames = "publisher_name")
        })
@Check(constraints = "publisher_name <> ''")
public class Publisher {
    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private List<Book> books;

    @Column(name="publisher_name")
    private String publisherName;
}
