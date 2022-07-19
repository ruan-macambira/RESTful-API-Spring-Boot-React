package com.rhgreat.api.restful;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
        return SharedMethods.validUser();
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
        assertFalse(violations.isEmpty());
    }

    @Test
    public void EmptyMothersNameIsInvalid() {
        Usuario usuario = validUser();
        usuario.setNomeMae("");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void EmptyCPFIsInvalid() {
        Usuario usuario = validUser();
        usuario.setCpf("");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test void InvalidCPFIsInvalid() {
        Usuario usuario = validUser();
        usuario.setCpf(SharedMethods.gerarCpfInvalido());

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void EmptyRgIsInvalid() {
        Usuario usuario = validUser();
        usuario.setRg("");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void RgOnlyAcceptDigits() {
        Usuario usuario = validUser();
        usuario.setRg("111111111111a");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());
    }
}
