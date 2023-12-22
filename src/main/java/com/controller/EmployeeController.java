package com.controller;

import com.entity.Employee;
import com.lowagie.text.DocumentException;
import com.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.EmployeeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/export-to-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public void generatePdfReport(HttpServletResponse response) throws DocumentException, IOException {

        PdfGenerator generator = new PdfGenerator();
        List<Employee> listOfEmployees = employeeService.getEmployeeList();
        generator.generatePdf(listOfEmployees, setHeader(response));
    }

    public HttpServletResponse setHeader(HttpServletResponse response) {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employee" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        return response;
    }

}// ENDCLASS
