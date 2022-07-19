package com.rhgreat.api.restful;

import java.sql.Date;
import java.util.concurrent.ThreadLocalRandom;

public class SharedMethods {
    public static Usuario validUser() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome");
        usuario.setNomeMae("Nome Mãe");
        usuario.setCpf(gerarCpfValido());
        usuario.setRg("11111111111111");
        usuario.setDataNascimento(today());
        
        return usuario;
    }

    private static Date today() {
        long ms = new java.util.Date().getTime();
        return new Date(ms);
    }

    public static String gerarCpfValido() {
        
        return new String(gerarCpfChar());
    }

    public static String gerarCpfInvalido() {
        char[] cpf = gerarCpfChar();
        int rndIndex = ThreadLocalRandom.current().nextInt(11);

        cpf[rndIndex] = cpf[rndIndex] == '0' ? '1' : '0';

        return new String(cpf);
    }

    private static char[] gerarCpfChar() {
        char[] digits = new char[11];
        
        for(int i=0; i<9; i++) {
            digits[i] = (char)ThreadLocalRandom.current().nextInt(10);
        }
        
        int digit10 = 0;
        for(int i=0; i<9; i++) {
            digit10 += digits[i] * (10-i);
        }
        digit10 = ((digit10 * 10) % 11) % 10;
        digits[9] = (char)digit10;

        int digit11 = 0;
        for(int i=0; i<10; i++) {
            digit11 += digits[i] * (11-i);
        }
        digit11 = ((digit11 * 10) % 11) % 10;
        digits[10] = (char)digit11;

        for(int i=0; i < 11; i++) {
            digits[i] += '0';
        }
        return digits;
    }
}
