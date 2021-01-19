package com.springboot.springboot.controller;

import com.springboot.springboot.Repository.EstadosRepository;
import com.springboot.springboot.Repository.PaisRepository;
import com.springboot.springboot.Repository.PessoaRepository;
import com.springboot.springboot.Repository.TelefoneRepository;
import com.springboot.springboot.model.Estados;
import com.springboot.springboot.model.Pessoa;
import com.springboot.springboot.model.Telefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EstadosRepository estadosRepository;
    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;



    @RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
    public ModelAndView inicio(){


        var estadosIT = estadosRepository.findAll();
        var paisesIT = paisRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("pessoaobj", new Pessoa());
        modelAndView.addObject("estados", estadosIT);
        modelAndView.addObject("paises", paisesIT);
        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa")
    public ModelAndView salvar(Pessoa pessoa){
        pessoaRepository.save(pessoa);

        ModelAndView modelAndView = new ModelAndView("cadastro/listarpessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        modelAndView.addObject("pessoas", pessoasIt);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listapessoas")
    public ModelAndView pessoas(){
        ModelAndView modelAndView = new ModelAndView("cadastro/listarpessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        modelAndView.addObject("pessoas", pessoasIt);
        return modelAndView;
    }

    @GetMapping("/editarpessoa/{idpessoa}")
    public ModelAndView editar(@PathVariable("idpessoa") long idpessoa){
        var pessoa = pessoaRepository.findById(idpessoa);
        Iterable<Estados> estadosIT = estadosRepository.findAll();
        var paisesIT = paisRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
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
        ModelAndView modelAndView = new ModelAndView("cadastro/listarpessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        modelAndView.addObject("pessoas", pessoasIt);

        modelAndView.addObject("estados", estadosIT);
        modelAndView.addObject("paises", paisesIT);
        return modelAndView;
    }

    @PostMapping("**/pesquisarpessoa")
    public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa){
        ModelAndView modelAndView = new ModelAndView("cadastro/listarpessoa");
        modelAndView.addObject("pessoas", pessoaRepository.findPessoaByName(nomepesquisa));
        modelAndView.addObject("estados", estadosRepository.findAll());
        modelAndView.addObject("paises", paisRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/telefones/{idpessoa}")
    public ModelAndView telefones(@PathVariable("idpessoa") long idpessoa){
        var pessoa = pessoaRepository.findById(idpessoa);
        Iterable<Estados> estadosIT = estadosRepository.findAll();
        var paisesIT = paisRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
        modelAndView.addObject("pessoaobj", pessoa.get());
        modelAndView.addObject("estados", estadosIT);
        modelAndView.addObject("paises", paisesIT);
        return modelAndView;
    }

    @PostMapping("**/addFonePessoa/{pessoaid}")
    public ModelAndView addFonePessoa(Telefone telefone, @PathVariable("pessoaid") Long pessoaid){
        var pessoa = pessoaRepository.findById(pessoaid).get();
        var modelAndView = new ModelAndView("cadastro/telefones");

        telefoneRepository.save(telefone);

        modelAndView.addObject("pessoaobj", pessoa);

        var estadosIT = estadosRepository.findAll();
        var paisesIT = paisRepository.findAll();
        modelAndView.addObject("estados", estadosIT);
        modelAndView.addObject("paises", paisesIT);


        return modelAndView;
    }

}
