package nc.apps.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Long id;
    public Author(Long id) {
        this.id = id;
    }

    private String firstName;
    private String lastName;
}
