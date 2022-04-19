package br.com.zup.edu.nossospotify.musica;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MusicaRequest {

    @NotBlank
    private String nome;

    @NotNull
    private Long donoId;

    public MusicaRequest(String nome, Long donoId) {
        this.nome = nome;
        this.donoId = donoId;
    }

    public String getNome() {
        return nome;
    }

    public Long getDonoId() {
        return donoId;
    }

    public Musica paraMusica(ArtistaRepository artistaRepository) {
        Artista dono = artistaRepository.findById(donoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Artista não encontrado"));

        return new Musica(nome,dono);
    }
}
