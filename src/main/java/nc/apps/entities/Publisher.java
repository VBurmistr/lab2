package nc.apps.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {
    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }

    private Long id;
    private String publisherName;
}
