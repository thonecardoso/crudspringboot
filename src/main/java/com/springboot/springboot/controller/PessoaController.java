package com.springboot.springboot.controller;

import com.springboot.springboot.Repository.EstadosRepository;
import com.springboot.springboot.Repository.PaisRepository;
import com.springboot.springboot.Repository.PessoaRepository;
import com.springboot.springboot.model.Estados;
import com.springboot.springboot.model.Pais;
import com.springboot.springboot.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EstadosRepository estadosRepository;
    @Autowired
    private PaisRepository paisRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
    public ModelAndView inicio(){


        var estadosIT = estadosRepository.findAll();
        var paisesIT = paisRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa1");
        modelAndView.addObject("pessoaobj", new Pessoa());
        modelAndView.addObject("estados", estadosIT);
        modelAndView.addObject("paises", paisesIT);
        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa")
    public ModelAndView salvar(Pessoa pessoa){
        pessoaRepository.save(pessoa);

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        modelAndView.addObject("pessoas", pessoasIt);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listapessoas")
    public ModelAndView pessoas(){
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        modelAndView.addObject("pessoas", pessoasIt);
        return modelAndView;
    }

    @GetMapping("/editarpessoa/{idpessoa}")
    public ModelAndView editar(@PathVariable("idpessoa") long idpessoa){
        var pessoa = pessoaRepository.findById(idpessoa);
        Iterable<Estados> estadosIT = estadosRepository.findAll();
        var paisesIT = paisRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa1");
        modelAndView.addObject("pessoaobj", pessoa.get());
        modelAndView.addObject("estados", estadosIT);
        modelAndView.addObject("paises", paisesIT);
        return modelAndView;
    }

    @GetMapping("/excluirpessoa/{idpessoa}")
    public ModelAndView excluir(@PathVariable("idpessoa") long idpessoa){
        pessoaRepository.deleteById(idpessoa);

        Iterable<Estados> estadosIT = estadosRepository.findAll();
        var paisesIT = paisRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        modelAndView.addObject("pessoas", pessoasIt);

        modelAndView.addObject("estados", estadosIT);
        modelAndView.addObject("paises", paisesIT);
        return modelAndView;
    }

}
