package projects.short_url_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import projects.short_url_api.doc.ShortUrlControllerDoc;
import projects.short_url_api.dto.ResponseDto;
import projects.short_url_api.dto.ShortUrlRequestDto;
import projects.short_url_api.service.ShortUrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/create-url")
public class ShortUrlController implements ShortUrlControllerDoc{

    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService){
        this.shortUrlService = shortUrlService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> gerarCodigo(@Valid @RequestBody ShortUrlRequestDto request) {
        
        return ResponseEntity.ok(shortUrlService.recebeUrl(request));
    }

    
    

}
