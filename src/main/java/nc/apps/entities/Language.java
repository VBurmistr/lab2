package nc.apps.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Language {
    private Long id;

    public Language(String languageName) {
        this.languageName = languageName;
    }

    private String languageName;
}
