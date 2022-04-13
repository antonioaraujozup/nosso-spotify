package br.com.zup.edu.nossospotify.musica;

import javax.validation.constraints.NotBlank;

public class AtualizaArtistaRequest {

    @NotBlank
    private String nome;

    public AtualizaArtistaRequest(String nome) {
        this.nome = nome;
    }

    public AtualizaArtistaRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
