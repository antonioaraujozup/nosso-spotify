package br.com.zup.edu.nossospotify.musica;

public class AlbumResponse {

    private String nome;

    public AlbumResponse(Album album) {
        this.nome = album.getNome();
    }

    public String getNome() {
        return nome;
    }
}
