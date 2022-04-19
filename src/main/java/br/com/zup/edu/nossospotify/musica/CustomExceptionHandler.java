package br.com.zup.edu.nossospotify.musica;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<MensagemDeErro> mensagens = ex.getBindingResult().getFieldErrors().stream()
                .map(MensagemDeErro::new)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(mensagens);
    }
}
