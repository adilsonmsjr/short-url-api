package projects.short_url_api.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public record ShortUrlRequestDto(
                        @NotBlank(message = "A URL é obrigatória.")
                        @URL (message = "URL inválida.")
                        String requestUrl) {

}
