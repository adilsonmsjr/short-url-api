package projects.short_url_api.service;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import projects.short_url_api.dto.ResponseDto;
import projects.short_url_api.dto.ShortUrlRequestDto;
import projects.short_url_api.entity.ShortUrl;
import projects.short_url_api.exception.ResourceNotFoundException;
import projects.short_url_api.repository.ShortUrlRepository;
import projects.short_url_api.service.generator.CodeGenerator;

@ExtendWith(MockitoExtension.class)
public class ShortUrlServiceTest {

    @Mock
    private ShortUrlRepository shortUrlRepository;

    @Mock
    private CodeGenerator codeGenerator;

    @InjectMocks
    private ShortUrlService shortUrlService;

    @Test
    void receberUrl(){

        ShortUrlRequestDto request = new ShortUrlRequestDto("https://www.google.com");

        ShortUrl shortUrl = new ShortUrl();

        shortUrl.setId(1L);
        shortUrl.setRequestUrl("https://www.google.com");
        shortUrl.setCode("AAAAAA");
        shortUrl.setClickCount(0L);
        shortUrl.setExpiresAt(OffsetDateTime.now().plusDays(3));
        shortUrl.setCriadoEm(OffsetDateTime.now());

        when(shortUrlRepository.existsByCode(anyString()))
            .thenReturn(false);

        when(shortUrlRepository.save(any(ShortUrl.class)))
            .thenReturn(shortUrl);     

        when(codeGenerator.generate())
        .thenReturn("AAAAAA");
        
        ResponseDto response = shortUrlService.recebeUrl(request);

        assertNotNull(response);
        assertEquals("AAAAAA", response.code());
        assertEquals("https://www.google.com", response.requestUrl());
        assertEquals("http://localhost:8080/" + "AAAAAA", response.shortUrl());
        assertEquals(0L, response.clickCount());

         verify(shortUrlRepository)
            .save(any(ShortUrl.class));

        verify(shortUrlRepository)
            .existsByCode(anyString());
        
    }

    @Test
    void buscarShortUrl(){

        ShortUrl shortUrl = new ShortUrl();

        //criando entidade
        shortUrl.setId(1L);
        shortUrl.setRequestUrl("https://www.google.com");
        shortUrl.setCode("AAAAAA");
        shortUrl.setClickCount(0L);
        shortUrl.setExpiresAt(OffsetDateTime.now().plusDays(3));
        shortUrl.setCriadoEm(OffsetDateTime.now());

        // quando buscar o codigo, devolva o objeto
        when(shortUrlRepository.findByCode("AAAAAA"))
            .thenReturn(Optional.of(shortUrl));

        //chamar o metodo do services
        ShortUrl result = shortUrlService.buscarShortUrl("AAAAAA");

        //confirma que voltou o obj
        assertNotNull(result);
        //confirma que o obj buscado retornou
        assertEquals("AAAAAA", result.getCode());

        //verifica se o metodo do repositorio foi chamado
        verify(shortUrlRepository).findByCode("AAAAAA");
    }

    @Test
    void contarClick(){

        ShortUrl shortUrl = new ShortUrl();

        //criando entidade
        shortUrl.setClickCount(5L);
        
        when(shortUrlRepository.save(any(ShortUrl.class)))
            .thenReturn(shortUrl);

        shortUrlService.contarClick(shortUrl);

        assertEquals(6L, shortUrl.getClickCount());

        verify(shortUrlRepository).save(shortUrl);       

    }
    
    @Test
    void lancaResourceNotFoundException(){

       when(shortUrlRepository.findByCode("AAAAAA"))
            .thenReturn(Optional.empty());

        ResourceNotFoundException exception =
                    assertThrows(ResourceNotFoundException.class, () -> shortUrlService.buscarShortUrl("AAAAAA"));

        assertEquals(
            "URL não encontrada.", exception.getMessage());

         // Verificar se buscou o codigo
        verify(shortUrlRepository)
            .findByCode("AAAAAA");

        // Garantir que o save nunca foi chamado
        verify(shortUrlRepository, never())
                .save(any(ShortUrl.class));
        
        }

}
