package com.springboot.springboot.controller;

import com.springboot.springboot.Repository.UsuarioRepository;
import com.springboot.springboot.model.Role;
import com.springboot.springboot.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(value = "**/salvarusuario")
    public ModelAndView cadastroUsuario(@Valid Usuario usuario, BindingResult bindingResult){

        ModelAndView modelAndView = new ModelAndView("/index");
        List<String> msg = new ArrayList<>();

        if (bindingResult.hasErrors()){
            for(ObjectError objectError : bindingResult.getAllErrors()){
                msg.add(objectError.getDefaultMessage());
            }
            modelAndView.addObject("msg", msg);
            modelAndView.addObject("usuario",usuario);

            return modelAndView;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode(usuario.getSenha());
        System.out.println(result + "\n" + usuario.getLogin());

        usuario.setSenha(result);

        try {
            usuarioRepository.save(usuario);

        }catch (Exception e){
            msg.add(e.getMessage());
        }

        try {
            usuarioRepository.salveRole();

        }catch (Exception e){
            msg.add(e.getMessage());
        }



        //Long id = usuarioRepository.lastId();


        modelAndView.addObject("msg", msg);
        modelAndView.addObject("usuario",new Usuario());


        return modelAndView;

    }

}
