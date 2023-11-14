package com.util;

import com.entity.Employee;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class PdfGenerator {

    public void generatePdf(List<Employee> employeeList, HttpServletResponse response)  throws DocumentException, IOException {

        Document document = new Document();

        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // add text to PDF file
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.black);

        Paragraph para = new Paragraph("Employee Record", font);
        para.setAlignment(Element.ALIGN_CENTER);
        document.add(para);
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(4);

        // add PDF table header
        Stream.of("ID", "Full Name", "Job Title", "Department").forEach(headerTitle ->
        {
            PdfPCell header = new PdfPCell();
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            header.setBackgroundColor(Color.LIGHT_GRAY);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(headerTitle, headerFont));
            table.addCell(header);
        });

        for (Employee employee : employeeList) {

            PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(employee.getId())));
            idCell.setPaddingLeft(4);
            idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            idCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(idCell);

            PdfPCell fullNameCell = new PdfPCell(new Phrase(employee.getFullName()));
            fullNameCell.setPaddingLeft(4);
            fullNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            fullNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(fullNameCell);

            PdfPCell jobTitleCell = new PdfPCell(new Phrase(employee.getJobTitle()));
            jobTitleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            jobTitleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            jobTitleCell.setPaddingRight(4);
            table.addCell(jobTitleCell);

            PdfPCell departmentCell = new PdfPCell(new Phrase(employee.getDepartment()));
            departmentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            departmentCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            departmentCell.setPaddingRight(4);
            table.addCell(departmentCell);
        }

        document.add(table);
        document.close();
    }

} //ENDCLASS
