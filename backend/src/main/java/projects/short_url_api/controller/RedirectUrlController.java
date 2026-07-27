package projects.short_url_api.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import projects.short_url_api.doc.RedirectUrlControllerDoc;
import projects.short_url_api.dto.EstatsGlobaisDto;
import projects.short_url_api.dto.ResponseDto;
import projects.short_url_api.entity.ShortUrl;
import projects.short_url_api.service.ShortUrlService;

@RestController
public class RedirectUrlController implements RedirectUrlControllerDoc{

    private final ShortUrlService shortUrlService;

    public RedirectUrlController (ShortUrlService shortUrlService){
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> acessarUrl(@PathVariable String code){

        ShortUrl url = shortUrlService.buscarShortUrl(code);

        shortUrlService.validaUrlAtiva(url);

        shortUrlService.contarClick(url);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(url.getRequestUrl()))
                .build();

    }

    @GetMapping("/admin/{code}")
    public ResponseEntity<ResponseDto> responserUrl(@PathVariable String code){

        ResponseDto dto = shortUrlService.responseShortUrl(code);

        return ResponseEntity.ok(dto);              

    }

    @GetMapping("/admin/estats")
    public ResponseEntity<EstatsGlobaisDto> getEstatsGlobais(){

        return ResponseEntity.ok(shortUrlService.estatsGlobais());            

    }
    
}
