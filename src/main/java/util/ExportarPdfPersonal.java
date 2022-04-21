package util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import es.demo.domain.Personal;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class ExportarPdfPersonal {

    private List<Personal> Personal;

    public ExportarPdfPersonal(List<Personal> Personal) {
        this.Personal = Personal;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.decode("#81be41"));
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Codigo", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Nombre", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Apellido1", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Apellido2", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Dni", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Personal persona : Personal) {
            table.addCell(persona.getCOD_PERSONAL());
            table.addCell(persona.getNOMBRE());
            table.addCell(persona.getAPELLIDO1());
            table.addCell(persona.getAPELLIDO2());
            table.addCell(persona.getEMAIL());
            table.addCell(persona.getDNI());

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        //abrimos el documento
        document.open();
        //le damos formato al header del documento
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);
        //creamos el titulo de cabecera del documento
        Paragraph p = new Paragraph("Listado de personal", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        //le damos formato a la tabla, como ancho de columnas y numero...
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{0.2f, 0.2f, 0.3f, 0.3f, 0.5f, 0.3f});
        table.setSpacingBefore(10);
        //se imprime la cabecera y la tabla
        writeTableHeader(table);
        writeTableData(table);
        //añadimos la tabla al documento
        document.add(table);
        document.close();

    }
}
