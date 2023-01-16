package fr.polytech.projetapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;

public class SortieNotFoundException extends ResponseStatusException {

    public SortieNotFoundException(Integer id) {
        super(HttpStatus.NOT_FOUND, MessageFormat.format("Sortie {0} not found", id));
    }

}
