package br.com.zup.edu.nossospotify.musica;

public class MusicaNomeResponse {

    private String nome;

    public MusicaNomeResponse(Musica musica) {
        this.nome = musica.getNome();
    }

    public String getNome() {
        return nome;
    }
}
