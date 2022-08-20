package nc.apps.dto.tabledtos;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookBaseModelDTO {
    private Integer id;
    private String title;
}
