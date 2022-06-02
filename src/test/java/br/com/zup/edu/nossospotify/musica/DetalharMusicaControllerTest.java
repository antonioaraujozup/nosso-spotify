package br.com.zup.edu.nossospotify.musica;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles("test")
class DetalharMusicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    @BeforeEach
    void setUp() {
        this.musicaRepository.deleteAll();
        this.artistaRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve detalhar uma música cadastrada")
    void deveDetalharUmaMusicaCadastrada() throws Exception {

        // Cenário
        Artista artista = new Artista("Antonio Eloy", "Vila Velha", "Espírito Santo");
        Artista participante1 = new Artista("Maiana", "Vila Velha", "Espírito Santo");
        Artista participante2 = new Artista("Rodolfo", "Vila Velha", "Espírito Santo");
        Set<Artista> participantes = Set.of(participante1,participante2);

        Musica musica = new Musica("O Sol", artista);
        musica.adicionar(participantes);

        this.artistaRepository.saveAll(List.of(artista,participante1,participante2));
        this.musicaRepository.save(musica);

        MockHttpServletRequestBuilder request = get("/musicas/{id}", musica.getId());

        // Ação e Corretude
        String payloadResponse = mockMvc.perform(request)
                .andExpect(
                        status().isOk()
                )
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        DetalharMusicaResponse detalharMusicaResponse = mapper.readValue(payloadResponse, DetalharMusicaResponse.class);

        // Asserts
        assertNotNull(detalharMusicaResponse);
        assertEquals(musica.getNome(), detalharMusicaResponse.getNome());
        assertEquals(musica.getDono().getNome(), detalharMusicaResponse.getDono());
        assertEquals(2, detalharMusicaResponse.getParticipacoes().size());
        assertTrue(detalharMusicaResponse.getParticipacoes().contains("Maiana"));
        assertTrue(detalharMusicaResponse.getParticipacoes().contains("Rodolfo"));
    }

    @Test
    @DisplayName("Não deve detalhar uma música não cadastrada")
    void naoDeveDetalharUmaMusicaNaoCadastrada() throws Exception {

        // Cenário
        MockHttpServletRequestBuilder request = get("/musicas/{id}", Integer.MAX_VALUE);

        // Ação e Corretude
        Exception resolvedException = mockMvc.perform(request)
                .andExpect(
                        status().isNotFound()
                )
                .andReturn()
                .getResolvedException();

        // Asserts
        assertNotNull(resolvedException);
        assertEquals(ResponseStatusException.class, resolvedException.getClass());
        assertEquals("Musica nao cadastrada.", ((ResponseStatusException) resolvedException).getReason());

    }

}