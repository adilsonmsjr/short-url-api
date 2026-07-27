package projects.short_url_api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projects.short_url_api.doc.CacheControllerDoc;
import projects.short_url_api.service.CacheService;

@RestController
@RequestMapping("/admin/cache")
public class CacheController implements CacheControllerDoc{

    private final CacheService cacheService;

    public CacheController (CacheService cacheService){
        this.cacheService = cacheService;
    }

    @GetMapping
    public Map<Object,Object> mostrarCache(){

        return cacheService.getCache(); 

    }

}
