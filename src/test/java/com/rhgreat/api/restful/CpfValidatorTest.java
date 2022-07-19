package com.rhgreat.api.restful;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class CpfValidatorTest {
    private class CpfValidoTester {
        @CpfValidoConstraint
        String cpf;

        public CpfValidoTester(String cpf) {
            this.cpf = cpf;
        }
    }

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void ValidFixedCPF() {
        CpfValidoTester CPF = new CpfValidoTester("52998224725");

        Set<?> violations = validator.validate(CPF);
        assertTrue(violations.isEmpty());
    }

    @Test
    void InvalidFixedCPF() {
        CpfValidoTester CPF = new CpfValidoTester("52998224726");

        Set<?> violations = validator.validate(CPF);
        assertFalse(violations.isEmpty());
    }

    @RepeatedTest(value = 10)
    void ValidRandomCPF() {
        CpfValidoTester CPF = new CpfValidoTester(SharedMethods.gerarCpfValido());

        Set<?> violations = validator.validate(CPF);
        assertTrue(violations.isEmpty());
    }

    @RepeatedTest(value = 10)
    void InvalidRandomCPF() {
        CpfValidoTester CPF = new CpfValidoTester(SharedMethods.gerarCpfInvalido());

        Set<?> violations = validator.validate(CPF);
        assertFalse(violations.isEmpty());
    }
}
