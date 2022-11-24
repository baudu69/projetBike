package fr.polytech.projetapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;

public class SortieNotFoundException extends RuntimeException{
    public SortieNotFoundException(Integer id) {
        super(MessageFormat.format("Sortie {0} not found", id));
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.getMessage());
    }
}
