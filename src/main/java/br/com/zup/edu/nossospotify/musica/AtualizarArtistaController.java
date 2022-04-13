package br.com.zup.edu.nossospotify.musica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class AtualizarArtistaController {

    private final ArtistaRepository repository;

    public AtualizarArtistaController(ArtistaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @PatchMapping("/artistas/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaArtistaRequest request) {
        Artista artista = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        artista.setNome(request.getNome());

        repository.save(artista);

        return ResponseEntity.noContent().build();
    }
}
