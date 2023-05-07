package tn.esprit.pibakcend.security.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.entities.Evenement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfGeneratorService {
    public void generatePDF(List<Evenement> events) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("events_list.pdf"));

            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Events List", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // table with two columns: "Title" and "Description"
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            Font tableHeaderFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            PdfPCell titleHeader = new PdfPCell(new Phrase("Title", tableHeaderFont));
            PdfPCell descriptionHeader = new PdfPCell(new Phrase("Description", tableHeaderFont));
            table.addCell(titleHeader);
            table.addCell(descriptionHeader);

            Font tableCellFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            for (Evenement event : events) {
                PdfPCell titleCell = new PdfPCell(new Phrase(event.getTitreEvennement(), tableCellFont));
                PdfPCell descriptionCell = new PdfPCell(new Phrase(event.getDescription(), tableCellFont));
                table.addCell(titleCell);
                table.addCell(descriptionCell);
            }

            document.add(table);
            document.close();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
