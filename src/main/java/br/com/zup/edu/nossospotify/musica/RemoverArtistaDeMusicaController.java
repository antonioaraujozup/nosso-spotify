package br.com.zup.edu.nossospotify.musica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
public class RemoverArtistaDeMusicaController {

    private final MusicaRepository musicaRepository;
    private final ArtistaRepository artistaRepository;

    public RemoverArtistaDeMusicaController(MusicaRepository musicaRepository, ArtistaRepository artistaRepository) {
        this.musicaRepository = musicaRepository;
        this.artistaRepository = artistaRepository;
    }

    @Transactional
    @DeleteMapping("/musicas/{musicaId}/artistas/{artistaId}")
    public ResponseEntity<?> remover(@PathVariable("musicaId") Long musicaId,
                                     @PathVariable("artistaId") Long artistaId) {

        Musica musica = musicaRepository.findById(musicaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada"));

        Artista artista = artistaRepository.findById(artistaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrado"));

        musica.remover(artista);

        musicaRepository.save(musica);

        return ResponseEntity.noContent().build();

    }
}
