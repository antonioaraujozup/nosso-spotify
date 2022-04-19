package br.com.zup.edu.nossospotify.musica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class AdicionarMusicaEmAlbumController {

    private final AlbumRepository albumRepository;
    private final ArtistaRepository artistaRepository;

    public AdicionarMusicaEmAlbumController(AlbumRepository albumRepository, ArtistaRepository artistaRepository) {
        this.albumRepository = albumRepository;
        this.artistaRepository = artistaRepository;
    }

    @Transactional
    @PostMapping("/albuns/{albumId}/musicas")
    public ResponseEntity<?> adicionarMusicaEmAlbum(@PathVariable("albumId") Long albumId,
                                                    @RequestBody @Valid MusicaRequest request,
                                                    UriComponentsBuilder uriComponentsBuilder) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Álbum não encontrado"));

        Musica novaMusica = request.paraMusica(artistaRepository);

        album.adicionarMusica(novaMusica);

        albumRepository.flush();

        URI location = uriComponentsBuilder.path("/albuns/{albumId}/musicas/{musicaId}")
                .buildAndExpand(album.getId(), novaMusica.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
