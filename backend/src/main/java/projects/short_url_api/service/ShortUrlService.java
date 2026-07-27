package projects.short_url_api.service;


import java.time.OffsetDateTime;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import projects.short_url_api.dto.EstatsGlobaisDto;
import projects.short_url_api.dto.ResponseDto;
import projects.short_url_api.dto.ShortUrlRequestDto;
import projects.short_url_api.entity.ShortUrl;
import projects.short_url_api.exception.ExpiredLinkException;
import projects.short_url_api.exception.ResourceNotFoundException;
import projects.short_url_api.mapper.MapperShortUrl;
import projects.short_url_api.repository.ShortUrlRepository;
import projects.short_url_api.service.generator.CodeGenerator;

@Service
public class ShortUrlService {

    
    private final ShortUrlRepository shortUrlRepository;

    private final CodeGenerator codeGenerator;

    public ShortUrlService(ShortUrlRepository shortUrlRepository, CodeGenerator codeGenerator){
        this.shortUrlRepository = shortUrlRepository;
        this.codeGenerator = codeGenerator;
    }
 

    public ResponseDto recebeUrl(ShortUrlRequestDto request){

        ShortUrl shortUrl = new ShortUrl(); 
        String codeRequest;

        do {
            codeRequest = codeGenerator.generate();
        } while(shortUrlRepository.existsByCode(codeRequest));

        OffsetDateTime expiresAt = OffsetDateTime.now().plusDays(3);

        shortUrl = MapperShortUrl.toEntity(request, codeRequest, expiresAt);

        shortUrl = shortUrlRepository.save(shortUrl);      
       
        return MapperShortUrl.fromEntity(shortUrl);     
        
    }

        @Cacheable(value = "shortUrl", key = "#code")
        public ShortUrl buscarShortUrl(String code){

            ShortUrl url = new ShortUrl();        

            url = shortUrlRepository.findByCode(code)
                        .orElseThrow(() -> new ResourceNotFoundException("URL não encontrada."));            

            return url;        

        }

    public void contarClick(ShortUrl url){

        url.setClickCount(url.getClickCount() + 1);
        shortUrlRepository.save(url); 

    }

    public ShortUrl validaUrlAtiva(ShortUrl url){

        if (url.getExpiresAt().isBefore(OffsetDateTime.now())){
                throw new ExpiredLinkException("Link não encontrado."); 
            }              

        return url;

    }

    public ResponseDto responseShortUrl(String code){

        ShortUrl url = new ShortUrl();

        url = shortUrlRepository.findByCode(code)
                    .orElseThrow(() -> new ResourceNotFoundException("URL não encontrada.")); 

        ResponseDto dto = MapperShortUrl.fromEntity(url);     

        return dto;

    }

    public EstatsGlobaisDto estatsGlobais(){
       
        long totalLinks = shortUrlRepository.count();

        long activeLinks = shortUrlRepository.countByExpiresAtAfter(OffsetDateTime.now());

        long expiredLinks = shortUrlRepository.countByExpiresAtBefore(OffsetDateTime.now());

        long totalClicks = shortUrlRepository.sumClickCount();

        EstatsGlobaisDto estats = new EstatsGlobaisDto(totalLinks, activeLinks, expiredLinks, totalClicks);

        return estats;

    }
    }
