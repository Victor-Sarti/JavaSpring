package br.com.sarti.JavaSpring.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.NOT_FOUND)
public class RequireObjectIsNullException extends RuntimeException {
    public RequireObjectIsNullException() {
        super("It is not allowed to persist a null object!!");
    }public RequireObjectIsNullException(String message) {
        super(message);
    }
}
