package projects.short_url_api.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.benmanes.caffeine.cache.Caffeine;



@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cachaManager(){

        CaffeineCacheManager cacheManager = new CaffeineCacheManager("shortUrl");

        cacheManager.setCaffeine(Caffeine.newBuilder()
                                    .initialCapacity(100)
                                    .maximumSize(1000)
                                    .expireAfterAccess(1, TimeUnit.HOURS)
                                    .recordStats()
    );                  

        return cacheManager;
        
    }

}

/* @CachePut(value = "shortUrls", key = "#code")
public ShortUrl atualizar(String code, UpdateRequest request){
    ...
}

@CacheEvict(value = "shortUrls", key = "#code")
public void deletar(String code){
    ...
}

@CacheEvict(value = "shortUrls", allEntries = true)
public void limparCache(){
}

cacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfter(new Expiry<Object, Object>() {
                    @Override
                    public long expireAfterCreate(Object key, Object value, long currentTime) {
                        // 1. Verificamos se o valor guardado é a sua classe ShortUrl
                        if (value instanceof ShortUrl) {
                            ShortUrl url = (ShortUrl) value;
                            
                            // 2. Calculamos o tempo restante entre AGORA e o momento da expiração
                            Duration restante = Duration.between(OffsetDateTime.now(), url.getExpiresAt());
                            
                            // 3. Se já passou do tempo, expira imediatamente (0), senão retorna o tempo em nanossegundos
                            return restante.isNegative() ? 0 : restante.toNanos();
                        }
                        // Tempo padrão caso não seja ShortUrl (ex: 5 minutos)
                        return Duration.ofMinutes(5).toNanos();
                    }

                    @Override
                    public long expireAfterUpdate(Object key, Object value, long currentTime, long currentDuration) {
                        return currentDuration; // Mantém o tempo original em caso de update
                    }

                    @Override
                    public long expireAfterRead(Object key, Object value, long currentTime, long currentDuration) {
                        return currentDuration; // Não muda o tempo só porque alguém leu o cache
                    }
                })); */