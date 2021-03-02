package com.springboot.springboot.controller;

import com.springboot.springboot.Repository.PRepository;
import com.springboot.springboot.Service.ReportService;
import com.springboot.springboot.model.Pessoa;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private PRepository pessoaRepository;
    @Autowired
    private ReportService service;

    @GetMapping("/getEmployees")
    public List<Pessoa> getEmployees() {

        return pessoaRepository.findAll();
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return service.exportReport(format);
    }
}
