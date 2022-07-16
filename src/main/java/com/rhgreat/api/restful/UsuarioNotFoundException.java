package com.rhgreat.api.restful;

public class UsuarioNotFoundException extends RuntimeException{
    UsuarioNotFoundException(Long id) {
    super("Could not find user " + id);
    }
}
