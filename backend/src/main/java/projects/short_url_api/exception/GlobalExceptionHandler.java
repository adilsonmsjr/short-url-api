package projects.short_url_api.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import projects.short_url_api.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResouceNotFoundException(ResourceNotFoundException ex){

        ErrorResponseDto error = new ErrorResponseDto(
                                    LocalDateTime.now(),
                                    HttpStatus.NOT_FOUND.value(),
                                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                                    List.of(ex.getMessage())
                                    
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        
    }

    @ExceptionHandler(ExpiredLinkException.class)
    public ResponseEntity<ErrorResponseDto> handleExpiredLinkException(ExpiredLinkException ex){

        ErrorResponseDto error = new ErrorResponseDto(
                                    LocalDateTime.now(),
                                    HttpStatus.GONE.value(),
                                    HttpStatus.GONE.getReasonPhrase(),
                                    List.of(ex.getMessage())
                                    
        );

        return ResponseEntity.status(HttpStatus.GONE).body(error);
        
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationError(MethodArgumentNotValidException ex){

        List <String> mensagens = ex.getBindingResult()
                                    .getFieldErrors()
                                    .stream()
                                    .map(erro -> erro.getDefaultMessage())
                                    .toList();

                                    /* List<ValidationErrorDto> errors = new ArrayList<>();

                                     for(FieldError error : ex.getBindingResult().getFieldErrors()){

                                        errors.add(
                                            new ValidationErrorDto(
                                                error.getField(),
                                                error.getDefaultMessage()
                                            )
                                        );
                                    }  */


        ErrorResponseDto error = new ErrorResponseDto(
                                    LocalDateTime.now(),
                                    HttpStatus.BAD_REQUEST.value(),
                                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                    mensagens
                                );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        
    }


}
