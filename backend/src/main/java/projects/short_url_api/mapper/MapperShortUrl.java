package projects.short_url_api.mapper;

import projects.short_url_api.dto.ShortUrlRequestDto;

import java.time.OffsetDateTime;

import projects.short_url_api.dto.ResponseDto;
import projects.short_url_api.entity.ShortUrl;

public class MapperShortUrl {

    public static ShortUrl toEntity(ShortUrlRequestDto data, String code, OffsetDateTime expiresAt){

        ShortUrl shortUrl = new ShortUrl();

        shortUrl.setRequestUrl(data.requestUrl());
        shortUrl.setCode(code);
        shortUrl.setExpiresAt(expiresAt);

        return shortUrl;

    }

    /* public static ShortUrl toCodeEntity(DtoCodeRequest code){

        ShortUrl shortUrl = new ShortUrl();

        shortUrl.setCode(code.code());

        return shortUrl;

    } */

    public static ResponseDto fromEntity(ShortUrl shortUrl){

        ResponseDto dto = new ResponseDto(
                                    shortUrl.getCode(),
                                    shortUrl.getRequestUrl(),
                                    "http://localhost:8080/" + shortUrl.getCode(),
                                    shortUrl.getClickCount()                                
                                );

        return dto;

    }

}
