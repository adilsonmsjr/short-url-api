package projects.short_url_api.doc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import projects.short_url_api.dto.ErrorResponseDto;
import projects.short_url_api.dto.EstatsGlobaisDto;
import projects.short_url_api.dto.ResponseDto;

@Tag(
    name = "Requisições"
    )

public interface RedirectUrlControllerDoc {


     @Operation(
        summary = "Retorna URL original",
        description = "Recebe um código e retorna a URL original",
        responses = {
                    @ApiResponse(
                        responseCode = "200"
                    ),
                    @ApiResponse(
                        responseCode = "400",
                        description = "URL inválida",
                        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    )
        }
    )    
    ResponseEntity<Void> acessarUrl(@PathVariable String code);

    @Operation(
        summary = "Retorna informações de uma requisição",
        description = "Recebe um código e retorna informações sobre a URL cadastrada",
        security = @SecurityRequirement(name = "apiKey"),
        responses = {
                    @ApiResponse(
                        responseCode = "200"
                    ),
                    @ApiResponse(
                        responseCode = "400",
                        description = "URL inválida",
                        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    )
        }
    )    
    ResponseEntity<ResponseDto> responserUrl(@PathVariable String code);    

    @Operation(
        summary = "Retorna estatístas globais da aplicação",
        security = @SecurityRequirement(name = "apiKey"),
        responses = {
                    @ApiResponse(
                        responseCode = "200"
                    )
        }
    )    
    ResponseEntity<EstatsGlobaisDto> getEstatsGlobais();

}
