package br.com.zup.edu.nossospotify.musica;

import java.util.Set;
import java.util.stream.Collectors;

public class ArtistaResponse {

    private String nome;
    private Set<AlbumResponse> albuns;
    private Set<MusicaNomeResponse> musicas;

    public ArtistaResponse(Artista artista) {
        this.nome = artista.getNome();
        this.albuns = artista.getAlbuns().stream()
                .map(a -> new AlbumResponse(a))
                .collect(Collectors.toSet());
        this.musicas = artista.getMusicas().stream()
                .map(m -> new MusicaNomeResponse(m))
                .collect(Collectors.toSet());
    }

    public String getNome() {
        return nome;
    }

    public Set<AlbumResponse> getAlbuns() {
        return albuns;
    }

    public Set<MusicaNomeResponse> getMusicas() {
        return musicas;
    }
}
