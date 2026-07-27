package projects.short_url_api.service;

import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;

@Service
public class CacheService {

    private final CacheManager cacheManager;

    public CacheService (CacheManager cacheManager){
        this.cacheManager = cacheManager;
    }

    public Map<Object, Object> getCache() {

        // pega o cache "shortUrl"
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache("shortUrl");
        
        if (caffeineCache == null) {
            return Map.of("mensagem", "Cache não encontrado ou não inicializado.");
        }

        // acessa o cache nativo do Caffeine e pegamos o mapa de dados completo
        Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
        
        // retorna o Map (Key -> Value)
        return nativeCache.asMap();
    }
    }
