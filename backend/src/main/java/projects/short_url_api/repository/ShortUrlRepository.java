package projects.short_url_api.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projects.short_url_api.entity.ShortUrl;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long>{

     Optional<ShortUrl> findByCode(String code);
     Boolean existsByCode(String code);
     long countByExpiresAtAfter(OffsetDateTime now);
     long countByExpiresAtBefore(OffsetDateTime now);
     
     @Query("SELECT COALESCE(SUM(s.clickCount),0) FROM ShortUrl s")
     long sumClickCount();
}
