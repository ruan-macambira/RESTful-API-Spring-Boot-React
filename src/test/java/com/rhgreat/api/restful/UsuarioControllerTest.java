package com.rhgreat.api.restful;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebMvc
public class UsuarioControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public Usuario validUser() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome");
        usuario.setNomeMae("Nome Mãe");
        usuario.setCpf("11111111111");
        usuario.setRg("11111111111111");
        
        return usuario;
    }

    public String toJSON(Usuario usuario) throws JSONException {
        JSONObject usuarioJSON = new JSONObject();
        usuarioJSON.put("nome", usuario.getNome());
        usuarioJSON.put("nomeMae", usuario.getNomeMae());
        usuarioJSON.put("cpf", usuario.getCpf());
        usuarioJSON.put("rg", usuario.getRg());

        return usuarioJSON.toString();
    }

    @Test
    public void validUserReturnsUserJSON() throws Exception {
        Usuario usuario = validUser();

        this.mockMvc.perform(
            post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJSON(usuario))
        ).andExpect(status().isCreated());
    }

    @Test
    public void invalidUserReturnsError() throws Exception {
        Usuario usuario = validUser();
        usuario.setNome("");

        this.mockMvc.perform(
            post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJSON(usuario))
        ).andExpect(status().isBadRequest());
    }
    
}
