package com.rhgreat.api.restful;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioTest {
    private Validator validator;

    public Usuario validUser() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome");
        usuario.setNomeMae("Nome Mãe");
        usuario.setCpf("11111111111");
        usuario.setRg("11111111111111");
        
        return usuario;
    }

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void isValid() {
        Usuario usuario = validUser();

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void EmptyNameIsInvalid() {
        Usuario usuario = validUser();
        usuario.setNome("");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertEquals(violations.size(), 1);
    }

    @Test
    public void EmptyMothersNameIsInvalid() {
        Usuario usuario = validUser();
        usuario.setNomeMae("");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertEquals(violations.size(), 1);
    }

    @Test
    public void EmptyCPFIsInvalid() {
        Usuario usuario = validUser();
        usuario.setCpf("");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertEquals(violations.size(), 1);
    }

    @Test
    public void EmptyRgIsInvalid() {
        Usuario usuario = validUser();
        usuario.setRg("");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertEquals(violations.size(), 1);
    }
}
