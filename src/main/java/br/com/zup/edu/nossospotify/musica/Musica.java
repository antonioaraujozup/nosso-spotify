package br.com.zup.edu.nossospotify.musica;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(optional = false)
    private Artista dono;

    @JoinTable(
            name = "participante_musica",
            joinColumns = @JoinColumn(name = "musica_id"),
            inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<Artista> participantes = new HashSet<>();

    @ManyToOne
    private Album album;

    public Musica(String nome, Artista dono) {
        this.nome = nome;
        this.dono = dono;
    }

    /**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
    public Musica() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeDono() {
        return this.dono.getNome();
    }

    public void adicionar(Set<Artista> artistasParticipantes) {

        this.participantes.addAll(artistasParticipantes);

        artistasParticipantes.forEach(artista -> artista.participou(this));
    }

    public void adicionar(Album album) {
        this.album = album;
    }

    public void remover(Artista artista) {
        if(!this.participantes.remove(artista)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Artista não é participante da música");
        }

        artista.remover(this);
    }
}
