package com.rhgreat.api.restful;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CpfValidator implements ConstraintValidator<CpfValidoConstraint, String> {
    @Override
    public void initialize(CpfValidoConstraint cpf) {

    }

    @Override
    public boolean isValid(String cpfField, ConstraintValidatorContext cxt) {
        if(cpfField == null || cpfField.length() != 11) return false;

        char[] cpf = cpfField.toCharArray();
        int[] digits = new int[11];

        for(int i=0; i<11; i++) {
            digits[i] = cpf[i] - '0';
        }

        int digit10 = 0;
        for(int i=0; i<9; i++) {
            digit10 += digits[i] * (10-i);
        }
        digit10 = ((digit10 * 10) % 11) % 10;
        if(digit10 != digits[9]) return false;

        int digit11 = 0;
        for(int i=0; i<10; i++) {
            digit11 += digits[i] * (11-i);
        }
        digit11 = ((digit11 * 10) % 11) % 10;
        if(digit11 != digits[10]) return false;

        return true;
    }
}
