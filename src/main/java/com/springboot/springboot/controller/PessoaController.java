package com.springboot.springboot.controller;

import com.springboot.springboot.Repository.*;
import com.springboot.springboot.Service.ReportUtil;
import com.springboot.springboot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ReportUtil reportUtil;



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
    public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult bindingResult){


        if (bindingResult.hasErrors()){
            ModelAndView modelAndView1 = new ModelAndView("cadastro/cadastropessoa");
            List<String> msg = new ArrayList<String>();
            for(ObjectError objectError : bindingResult.getAllErrors()){
                msg.add(objectError.getDefaultMessage());
            }
            modelAndView1.addObject("msg", msg);
            modelAndView1.addObject("pessoaobj", pessoa);

            return modelAndView1;
        }

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

    @GetMapping("**/pesquisarpessoa")
    public void imprimePDF(@RequestParam("nomepesquisa") String nomepesquisa,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        var pessoas = pessoaRepository.findPessoaByName(nomepesquisa);

        byte[] pdf = reportUtil.gerarRelatorio(pessoas, "pessoasReport", request.getServletContext());

        response.setContentLength(pdf.length);

        response.setContentType("application/octet-stream");

        String headerKey = "Content-disposition";
        String headerValue = String.format("Attachment; filename=\"%s\"", "relatorio.pdf");

        response.getOutputStream().write(pdf);

    }

    @GetMapping("/detalhesPessoa/{idpessoa}")
    public ModelAndView telefones(@PathVariable("idpessoa") long idpessoa){
        var pessoa = pessoaRepository.findById(idpessoa);
        Iterable<Estados> estadosIT = estadosRepository.findAll();
        var paisesIT = paisRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("cadastro/detalhesPessoa");
        modelAndView.addObject("pessoaobj", pessoa.get());
        modelAndView.addObject("estados", estadosIT);
        modelAndView.addObject("paises", paisesIT);
        return modelAndView;
    }

    @PostMapping("**/addFonePessoa/{pessoaid}")
    public ModelAndView addFonePessoa(Telefone telefone, @PathVariable("pessoaid") Long pessoaid){
        var pessoa = pessoaRepository.findById(pessoaid).get();
        var modelAndView = new ModelAndView("cadastro/detalhesPessoa");

        telefone.setPessoa(pessoa);

        telefoneRepository.save(telefone);

        modelAndView.addObject("pessoaobj", pessoa);

        var estadosIT = estadosRepository.findAll();
        var paisesIT = paisRepository.findAll();
        modelAndView.addObject("estados", estadosIT);
        modelAndView.addObject("paises", paisesIT);


        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.GET, value = "**/addPais")
    public ModelAndView addPais(@ModelAttribute(value="paisadd") Pais pais){

        paisRepository.save(pais);
        var estadosIT = estadosRepository.findAll();
        var paisesIT = paisRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("pessoaobj", new Pessoa());
        modelAndView.addObject("estados", estadosIT);
        modelAndView.addObject("paises", paisesIT);
        return modelAndView;
    }

    @GetMapping("**/excluirTelefone/{idtelefone}")
    public ModelAndView excluirTelefone(@PathVariable("idtelefone") Long id){



        var modelAndView = new ModelAndView("cadastro/detalhesPessoa");

        var pessoaid = telefoneRepository.findIdPessoa(id);
        var pessoa = pessoaRepository.findById(pessoaid);
        telefoneRepository.deleteById(id);

        modelAndView.addObject("pessoaobj", pessoa.get());

        var estadosIT = estadosRepository.findAll();
        var paisesIT = paisRepository.findAll();
        modelAndView.addObject("estados", estadosIT);
        modelAndView.addObject("paises", paisesIT);


        return modelAndView;
    }

    @GetMapping("**/novoendereco/{pessoaid}")
    public ModelAndView novoEndereco(@PathVariable("pessoaid") Long pessoaid){

        var modelAndView = new ModelAndView("cadastro/cadastroendereco");
        var endereco = new Endereco();
        endereco.setPessoa(pessoaRepository.findById(pessoaid).get());
        modelAndView.addObject("idpessoa", pessoaid);
        modelAndView.addObject("Objendereco", endereco);

        return modelAndView;
    }

    @PostMapping("**/addEnderecoPessoa/{pessoaid}")
    public ModelAndView addEnderecoPessoa(Endereco endereco, @PathVariable("pessoaid") Long pessoaid){
        var pessoa = pessoaRepository.findById(pessoaid);
        var modelAndView = new ModelAndView("cadastro/detalhesPessoa");

        endereco.setPessoa(pessoa.get());

        enderecoRepository.save(endereco);

        modelAndView.addObject("pessoaobj", pessoa.get());

        var estadosIT = estadosRepository.findAll();
        var paisesIT = paisRepository.findAll();
        modelAndView.addObject("estados", estadosIT);
        modelAndView.addObject("paises", paisesIT);


        return modelAndView;
    }

    @GetMapping("**/excluirEndereco/{idendereco}")
    public ModelAndView excluirEndereco(@PathVariable("idendereco") Long id){



        var modelAndView = new ModelAndView("cadastro/detalhesPessoa");

        var pessoa = enderecoRepository.findById(id).get().getPessoa();

        enderecoRepository.deleteById(id);

        modelAndView.addObject("pessoaobj", pessoa);

        var estadosIT = estadosRepository.findAll();
        var paisesIT = paisRepository.findAll();
        modelAndView.addObject("estados", estadosIT);
        modelAndView.addObject("paises", paisesIT);


        return modelAndView;
    }

    @GetMapping("/editarendereco/{idendereco}")
    public ModelAndView editarEndereco(@PathVariable("idendereco") long idendereco){
        var endereco = enderecoRepository.findById(idendereco);


        ModelAndView modelAndView = new ModelAndView("cadastro/cadastroendereco");

        modelAndView.addObject("Objendereco", endereco.get());

        return modelAndView;
    }

}
