package br.com.zup.edu.nossospotify.musica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DetalharMusicaController {

    private final MusicaRepository repository;

    public DetalharMusicaController(MusicaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/musicas/{id}")
    public ResponseEntity<?> detalhar(@PathVariable("id") Long id) {
        Musica musica = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(new MusicaResponse(musica));
    }
}
