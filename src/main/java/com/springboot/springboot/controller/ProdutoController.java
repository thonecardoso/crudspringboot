package com.springboot.springboot.controller;

import com.springboot.springboot.Repository.ProdutoRepository;
import com.springboot.springboot.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController {



    @Autowired
    private ProdutoRepository produtoRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastrarproduto")
    public ModelAndView cadastro(){
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastrarproduto");
        modelAndView.addObject("produtoobj", new Produto());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listarproduto")
    public ModelAndView produtos(){

        ModelAndView modelAndView = new ModelAndView("cadastro/listarproduto");

        var produtosIT = produtoRepository.findAll();
        modelAndView.addObject("produtos", produtosIT);

        return modelAndView;
    }




    @RequestMapping(method = RequestMethod.POST, value = "**/salvarproduto")
    public ModelAndView salvar(Produto produto){
        produtoRepository.save(produto);

        ModelAndView modelAndView = new ModelAndView("cadastro/listarproduto");
        var produtosIT = produtoRepository.findAll();
        modelAndView.addObject("produtos", produtosIT);

        return modelAndView;

    }
}
