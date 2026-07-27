package projects.short_url_api.doc;

import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(
    name = "Cache"
    )
public interface CacheControllerDoc {


    @Operation(
        summary = "Retorna infos de Cache",
        security = @SecurityRequirement(name = "apiKey"),
        responses = {
                    @ApiResponse(
                        responseCode = "200"
                    )
        }
    )    
    Map<Object,Object> mostrarCache();


}
