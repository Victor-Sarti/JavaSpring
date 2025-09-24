package br.com.sarti.JavaSpring.exeception.hadler;

import br.com.sarti.JavaSpring.exeception.ExeceptionResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {
// MENSAGEM DE ERRO NO SERVIDOR
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExeceptionResponse> handleAllExeceptions (Exception ex, WebRequest request){
    ExeceptionResponse response = new ExeceptionResponse(
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    public final ResponseEntity<ExeceptionResponse> handleNotFoundExeceptions (Exception ex, WebRequest request){
    ExeceptionResponse response = new ExeceptionResponse(
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
