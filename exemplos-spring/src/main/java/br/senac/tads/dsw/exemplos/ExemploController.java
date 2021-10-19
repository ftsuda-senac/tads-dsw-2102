/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplos;

import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
