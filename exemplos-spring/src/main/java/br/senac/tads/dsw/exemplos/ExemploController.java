/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExemploController {

    @GetMapping("/ex01")
    public String exemplo01() {
        return "exemplo01"; // Nome do arquivo que vai gerar a view;
    }
    
    @GetMapping("/ex02")
    public String exemplo02(Model model) {
        model.addAttribute("mensagem", "Texto gerado no Controller");
        model.addAttribute("numero", 2212);
        model.addAttribute("dataHoraAcesso", LocalDateTime.now());
        return "exemplo02";
    }

    @GetMapping("/ex02mv")
    public ModelAndView exemplo02b() {
        ModelAndView mv = new ModelAndView("exemplo02"); // Nome do arquivo que vai gerar a view;
        mv.addObject("mensagem", "Texto gerado no Controller usando ModelAndView");
        mv.addObject("numero", 2228);
        mv.addObject("dataHoraAcesso", LocalDateTime.now());
        return mv;
    }
    
    @GetMapping("/ex03")
    public ModelAndView exemplo03(
            @RequestParam("nome") String nome,
            @RequestParam(value = "numero", defaultValue = "0") int numero,
            @RequestParam(value = "data", required = false) String data) {
        ModelAndView mv = new ModelAndView("exemplo03");
        mv.addObject("nome", nome);
        mv.addObject("numero", numero);
        mv.addObject("data", data);
        return mv;
    }
    
    @GetMapping("/ex04/{apelido}")
    public ModelAndView exemplo04(
            @PathVariable("apelido") String apelido,
            @RequestParam(value = "numero", defaultValue = "0") int numero,
            @RequestParam(value = "data", required = false) String data) {
        ModelAndView mv = new ModelAndView("exemplo04");
        mv.addObject("apelido", apelido);
        mv.addObject("numero", numero);
        mv.addObject("data", data);
        return mv;
    }
    
    @GetMapping("/ex05")
    public ModelAndView exemplo05() {
        
        DadosPessoais dados = new DadosPessoais();
        dados.setNome("Fulano da Silva");
        dados.setApelido("fulano");
        dados.setDataNascimento(LocalDate.of(2000, 5, 20));
        dados.setNumero(54);
        dados.setEmail("fulano@teste.com.br");
        dados.setTelefone("(11) 98765-1122");
        
        ModelAndView mv = new ModelAndView("exemplo05");
        mv.addObject("dados", dados);
        return mv;
    }

    @GetMapping("/ex06")
    public ModelAndView exemplo06(
            @RequestParam(value = "apresentacao", defaultValue = "tabela") String modoApresentacao) {
        
        DadosPessoais d01 = new DadosPessoais();
        d01.setNome("Fulano da Silva");
        d01.setApelido("fulano");
        d01.setDataNascimento(LocalDate.of(2000, 5, 20));
        d01.setNumero(54);
        d01.setEmail("fulano@teste.com.br");
        d01.setTelefone("(11) 98765-1122");
        
        DadosPessoais d02 = new DadosPessoais();
        d02.setNome("Ciclana de Souza");
        d02.setApelido("ciclana");
        d02.setDataNascimento(LocalDate.of(1998, 3, 15));
        d02.setNumero(87);
        d02.setEmail("ciclana@teste.com.br");
        d02.setTelefone("(11) 91234-9988");
        
        DadosPessoais d03 = new DadosPessoais();
        d03.setNome("Beltrano das Cruzes");
        d03.setApelido("beltrano");
        d03.setDataNascimento(LocalDate.of(2001, 12, 5));
        d03.setNumero(36);
        d03.setEmail("beltrano@teste.com.br");
        d03.setTelefone("(11) 97654-1234");
        
        List<DadosPessoais> lista = new ArrayList<>();
        lista.add(d01);
        lista.add(d02);
        lista.add(d03);
        
        String arquivoView;
        if ("lista".equalsIgnoreCase(modoApresentacao)){
            // Mostrar as informações em uma lista não ordenada (<ul>)
            arquivoView = "exemplo06-lista";
        } else {
            // Mostrar as informaçoes em uma <table>
            arquivoView = "exemplo06-tabela";
        }
        
        ModelAndView mv = new ModelAndView(arquivoView);
        mv.addObject("itens", lista);
        return mv;
    }
    
    @GetMapping("/ex07")
    public ModelAndView exemplo07(@RequestHeader("user-agent") String userAgent) {
        
        boolean clientMobile = userAgent.toLowerCase().contains("mobile");
        
        ModelAndView mv = new ModelAndView("exemplo07");
        mv.addObject("mobile", clientMobile);
        mv.addObject("ua", userAgent);
        return mv;
    }
    
    @GetMapping("/ex08")
    public ModelAndView exemplo08(@RequestHeader Map<String, String> cabecalhos) {
        ModelAndView mv = new ModelAndView("exemplo08");
        mv.addObject("cabecalhos", cabecalhos);
        return mv;
    }

}
