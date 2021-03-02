package com.springboot.springboot.Service;

import com.springboot.springboot.Repository.PRepository;
import com.springboot.springboot.Repository.PessoaRepository;
import com.springboot.springboot.model.Pessoa;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private PRepository repository;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "/home/thone/report";
        var pessoas = repository.findAll();



        //load file and compile it
        File file = ResourceUtils.getFile("classpath:Cherry.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pessoas);

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("createdBy", "Java Techie");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "/employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/employees.pdf");
        }

        return "report generated in path : " + path;
    }
}
