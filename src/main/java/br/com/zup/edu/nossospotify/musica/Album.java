package br.com.zup.edu.nossospotify.musica;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(optional = false)
    private Artista dono;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "album")
    private List<Musica> musicas = new ArrayList<>();

    public Album(String nome, Artista dono) {
        this.nome = nome;
        this.dono = dono;
    }

    /**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
    public Album() {
    }

    public void adicionarMusica(Musica novaMusica) {
        novaMusica.adicionar(this);
        this.musicas.add(novaMusica);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
