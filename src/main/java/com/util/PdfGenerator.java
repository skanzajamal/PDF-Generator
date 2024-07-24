package com.util;

import com.entity.StaffEntity;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class PdfGenerator {

    public void generatePdf(List<StaffEntity> employeeList, HttpServletResponse response)  throws DocumentException, IOException {

        Document document = new Document();

        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // add logo
        Image jpg = Image.getInstance("logo.jpg");
        document.add(jpg);

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

        for (StaffEntity employee : employeeList) {

            PdfPCell cell = new PdfPCell();
            cell.setPaddingLeft(4);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);

            cell.setPhrase(new Phrase(String.valueOf(employee.getId())));
            table.addCell(cell);

            cell.setPhrase(new Phrase(employee.getFullName()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(employee.getJobTitle()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(employee.getDepartment()));
            table.addCell(cell);
        }

        document.add(table);
        document.close();
    }

} //ENDCLASS
