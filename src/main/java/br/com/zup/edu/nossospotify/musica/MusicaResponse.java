package br.com.zup.edu.nossospotify.musica;

public class MusicaResponse {

    private String nome;
    private String artista;

    public MusicaResponse(Musica musica) {
        this.nome = musica.getNome();
        this.artista = musica.getNomeDono();
    }

    public String getNome() {
        return nome;
    }

    public String getArtista() {
        return artista;
    }
}
