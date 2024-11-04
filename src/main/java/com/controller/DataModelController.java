package com.controller;

import com.entity.StaffEntity;
import com.lowagie.text.DocumentException;
import com.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.service.DataModelService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DataModelController {

    @Autowired
    private DataModelService dataModelService;

    //export pdf

    @GetMapping(value = "/export-to-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public void generatePdfReport(HttpServletResponse response) throws DocumentException, IOException {

        PdfGenerator generator = new PdfGenerator();
        List<StaffEntity> listOfEmployees = dataModelService.getStaffList();
        generator.generatePdf(listOfEmployees, setHeader(response));
    }

    private HttpServletResponse setHeader(HttpServletResponse response) {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employee" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        return response;
    }

}// ENDCLASS
