package br.com.zup.edu.nossospotify.musica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
public class RemoverAlbumController {

    private final AlbumRepository repository;

    public RemoverAlbumController(AlbumRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @DeleteMapping("/albuns/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") Long id) {
        Album album = repository.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Álbum não encontrado");
        });

        repository.delete(album);

        return ResponseEntity.noContent().build();
    }
}
