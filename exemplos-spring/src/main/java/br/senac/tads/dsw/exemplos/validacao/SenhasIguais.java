/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplos.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

// Referência: https://www.baeldung.com/spring-mvc-custom-validator
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SenhasIguaisValidator.class)
public @interface SenhasIguais {

    String message() default "A senha e a repetição devem ser iguais";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
