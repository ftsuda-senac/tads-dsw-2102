/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplos.validacao;

import br.senac.tads.dsw.exemplos.DadosPessoais;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author ftsuda
 */
public class SenhasIguaisValidator implements ConstraintValidator<SenhasIguais, DadosPessoais> {

    @Override
    public boolean isValid(DadosPessoais dados, ConstraintValidatorContext context) {
        boolean resultado = dados.getSenha().equals(dados.getRepetirSenha());
        return resultado;
    }

}
