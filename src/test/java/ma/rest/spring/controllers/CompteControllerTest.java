package ma.rest.spring.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CompteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllComptesJson() throws Exception {
        mockMvc.perform(get("/banque/comptes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetAllComptesXml() throws Exception {
        mockMvc.perform(get("/banque/comptes")
                .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML));
    }

    @Test
    void testGetCompteByIdJson() throws Exception {
        mockMvc.perform(get("/banque/comptes/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetCompteByIdXml() throws Exception {
        mockMvc.perform(get("/banque/comptes/1")
                .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML));
    }

    @Test
    void testCreateCompteJson() throws Exception {
        String json = "{\"solde\": 1500.0, \"dateCreation\": \"2024-11-01\", \"type\": \"COURANT\"}";
        mockMvc.perform(post("/banque/comptes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.solde").value(1500.0))
                .andExpect(jsonPath("$.type").value("COURANT"));
    }

    @Test
    void testCreateCompteXml() throws Exception {
        String xml = "<compte><solde>1500.0</solde><dateCreation>2024-11-01</dateCreation><type>COURANT</type></compte>";
        mockMvc.perform(post("/banque/comptes")
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
                .content(xml))
                .andExpect(status().isOk())
                .andExpect(xpath("/compte/solde").string("1500.0"))
                .andExpect(xpath("/compte/type").string("COURANT"));
    }

    @Test
    void testUpdateCompteJson() throws Exception {
        String json = "{\"solde\": 2000.0, \"dateCreation\": \"2024-11-01\", \"type\": \"EPARGNE\"}";
        mockMvc.perform(put("/banque/comptes/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.solde").value(2000.0))
                .andExpect(jsonPath("$.type").value("EPARGNE"));
    }

    @Test
    void testUpdateCompteXml() throws Exception {
        String xml = "<compte><solde>2000.0</solde><dateCreation>2024-11-01</dateCreation><type>EPARGNE</type></compte>";
        mockMvc.perform(put("/banque/comptes/2")
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
                .content(xml))
                .andExpect(status().isOk())
                .andExpect(xpath("/compte/solde").string("2000.0"))
                .andExpect(xpath("/compte/type").string("EPARGNE"));
    }

    @Test
    void testDeleteCompte() throws Exception {
        mockMvc.perform(delete("/banque/comptes/3"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/banque/comptes/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testOpenApiDocs() throws Exception {
        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}

