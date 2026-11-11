
package tn.esprit.autoloc_api.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tn.esprit.autoloc_api.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 : ressource introuvable
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(
            ResourceNotFoundException ex) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("statut", HttpStatus.NOT_FOUND.value());
        body.put("message", ex.getMessage());
        body.put("horodatage", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // 400 : argument incorrect
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(
            IllegalArgumentException ex) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("statut", HttpStatus.BAD_REQUEST.value());
        body.put("message", ex.getMessage());
        body.put("horodatage", LocalDateTime.now());

        return ResponseEntity.badRequest().body(body);
    }

    // 400 : validation des champs DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> erreurs = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                erreurs.put(
                        error.getField(),
                        error.getDefaultMessage()
                )
        );

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("statut", HttpStatus.BAD_REQUEST.value());
        body.put("message", "Erreur de validation des données");
        body.put("erreurs", erreurs);
        body.put("horodatage", LocalDateTime.now());

        return ResponseEntity.badRequest().body(body);
    }
}
