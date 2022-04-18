package br.com.zup.edu.nossospotify.musica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DetalharArtistaController {

    private final ArtistaRepository repository;

    public DetalharArtistaController(ArtistaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/artistas/{id}")
    public ResponseEntity<?> detalhar(@PathVariable("id") Long id) {
        Artista artista = repository.consultaArtistaPorIdComAlbunsEMusicas(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(new ArtistaResponse(artista));
    }
}
