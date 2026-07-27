package projects.short_url_api.exception;

public class ExpiredLinkException extends RuntimeException{

    public ExpiredLinkException(String message){

        super(message);
        
    }

}
