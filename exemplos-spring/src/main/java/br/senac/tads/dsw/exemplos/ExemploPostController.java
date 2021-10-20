/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ftsuda
 */
@Controller
@RequestMapping("/exemplo-post")
public class ExemploPostController {
    
    @GetMapping
    public ModelAndView mostrarForm() {
        ModelAndView mv = new ModelAndView("formulario-bs");
        mv.addObject("dados", new DadosPessoais());
        return mv;
    }
    
    @GetMapping("/{id}")
    public ModelAndView mostrarFormPreenchido(@PathVariable Integer id) {
        DadosPessoais dados = new DadosPessoais();
        dados.setId(id);
        dados.setNome("Ciclana de Souza");
        dados.setDescricao("Descrição da Ciclana");
        dados.setDataNascimento(LocalDate.of(1998, 3, 20));
        dados.setEmail("ciclana@teste.com.br");
        dados.setTelefone("(11) 99999-9988");
        dados.setSenha("abcd1234");
        dados.setRepetirSenha("abcd1234");
        dados.setNumero(15);
        dados.setAltura(new BigDecimal("1.68"));
        dados.setPeso(new BigDecimal("76.2"));
        dados.setGenero(0);
        List<String> interesses = new ArrayList<>();
        interesses.add("Gastronomia");
        interesses.add("Viagens");
        dados.setInteresses(interesses);
        
        ModelAndView mv = new ModelAndView("formulario-bs");
        mv.addObject("dados", dados);
        return mv;
    }
    
    @PostMapping("/salvar")
    public ModelAndView salvarDados(@ModelAttribute DadosPessoais dados) {
        ModelAndView mv = new ModelAndView("resultado-bs");
        mv.addObject("dados", dados);
        return mv;
    }
    
    @PostMapping("/salvar-post-redirect-get")
    public ModelAndView salvarDadosComPostRedirectGet(@ModelAttribute DadosPessoais dados, 
            RedirectAttributes redirectAttributes) {
        // Escopo Flash - Mantém as informações entre duas requisições consecutivas
        redirectAttributes.addFlashAttribute("dados", dados);
        
        ModelAndView mv = new ModelAndView("redirect:/exemplo-post/resultado");
        return mv;
    }
    
    @GetMapping("/resultado")
    public ModelAndView mostrarResultado() {
        return new ModelAndView("resultado-bs");
    }
    
}
