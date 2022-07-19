package com.rhgreat.api.restful;

import java.util.HashMap;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class UsuarioError extends RepresentationModel<UsuarioError> {
    private Usuario usuario;
    private HashMap<String, String> errors;

    public UsuarioError(MethodArgumentNotValidException ex) {
        this.usuario = (Usuario) ex.getTarget();
        this.errors = new HashMap<String, String>();

        for(FieldError error: ex.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public HashMap<String,String> getErrors() {
        return this.errors;
    }

}
