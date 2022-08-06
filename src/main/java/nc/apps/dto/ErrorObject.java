package nc.apps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorObject {
    private int statusCode;
    private String msg;
    private long timestamp;
}
