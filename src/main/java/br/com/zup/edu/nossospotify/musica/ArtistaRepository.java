package br.com.zup.edu.nossospotify.musica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista,Long> {

    @Query("select a from Artista a join fetch a.albuns join fetch a.musicas where a.id=:id")
    Optional<Artista> consultaArtistaPorIdComAlbunsEMusicas(Long id);

}
