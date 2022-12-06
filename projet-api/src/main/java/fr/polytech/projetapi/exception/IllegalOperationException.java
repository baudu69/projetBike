package fr.polytech.projetapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IllegalOperationException extends RuntimeException{

    public IllegalOperationException(String message) {
        super(message);
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, this.getMessage());
    }
}
