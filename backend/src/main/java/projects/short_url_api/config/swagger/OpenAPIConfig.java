package projects.short_url_api.config.swagger;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@SecurityScheme(
        name = "apiKey",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER,
        paramName = "X-API-KEY"
)
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(){

        return new OpenAPI()
                .info(new Info()
                    .title("Short URL API")
                    .description("""
                        API de encurtamento de URLs.                        
                        Funcionalidades:
                        - Criar URLs curtas
                        - Redirecionamento automático
                        - Estatísticas de acesso
                        - Controle de endpoints com API KEY
                    """)
                    .version("v1.0.0")
                    .contact(new Contact()
                            .name("Adilson Jr.")
                            .email("dilsjr@gmail.com")
                            .url("https://github.com/adilsonmsjr"))
                    .license(new License()
                            .name("Apache 2.0")
                            .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
            )
            .servers(List.of(
                    new Server()
                            .url("http://localhost:8080")
                            .description("Local Development")
                    
            ))
            .externalDocs(new ExternalDocumentation()
                    .description("GitHub Repository")
                    .url("https://github.com/adilsonmsjr/short-url-api"));                        
                
    }

}
