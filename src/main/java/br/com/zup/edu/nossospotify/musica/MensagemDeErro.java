package br.com.zup.edu.nossospotify.musica;

import org.springframework.validation.FieldError;

public class MensagemDeErro {

    private String campo;
    private String mensagem;

    public MensagemDeErro(FieldError fieldError) {
        this.campo = fieldError.getField();
        this.mensagem = fieldError.getDefaultMessage();
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
