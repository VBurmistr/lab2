package nc.apps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookIDsDTO {

    private String title;

    @JsonProperty("author")
    private String authorId;

    @JsonProperty("category")
    private String categoryId;

    @JsonProperty("language")
    private String languageId;

    @JsonProperty("publisher")
    private String publisherId;

    @JsonProperty("prequel")
    private String prequelId;
}
