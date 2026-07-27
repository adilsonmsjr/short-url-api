package projects.short_url_api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponseDto (
                    LocalDateTime timestamp,
                    Integer status,
                    String error,
                    List<String> message
                    
) {

    

}
