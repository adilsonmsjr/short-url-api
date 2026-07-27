package projects.short_url_api.dto;

public record ResponseDto(String code, String requestUrl, String shortUrl, Long clickCount) {

}
