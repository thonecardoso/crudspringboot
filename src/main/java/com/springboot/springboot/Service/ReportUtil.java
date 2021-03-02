package com.springboot.springboot.Service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Component
public class ReportUtil implements Serializable {

    public byte[] gerarRelatorio(List listDados,
                                 String relatorio, ServletContext servletContext) throws Exception{
        var jrbcds = new JRBeanCollectionDataSource(listDados);

        String caminhoJasper = servletContext.getRealPath("relatorios")
                + File.separator + relatorio + ".jasper";

        JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoJasper, new HashMap<>(),jrbcds);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }


}
