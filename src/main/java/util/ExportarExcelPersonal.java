
package util;

 
import es.demo.domain.Personal;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportarExcelPersonal {
 private List<Personal> Personal;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public ExportarExcelPersonal(List<Personal> Personal) {
		this.Personal = Personal;
		workbook = new XSSFWorkbook();
	}

	private void writeHeader() {
		sheet = workbook.createSheet("Personal");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Código", style);
		createCell(row, 1, "Nombre", style);
		createCell(row, 2, "Primer Apellido", style);
		createCell(row, 3, "Segundo Apellido", style);
		createCell(row, 4, "Email", style);
		createCell(row, 5, "Dni", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} 
        else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void write() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Personal persona : Personal) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, persona.getCOD_PERSONAL(), style);
			createCell(row, columnCount++, persona.getNOMBRE(), style);
			createCell(row, columnCount++, persona.getAPELLIDO1(), style);
			createCell(row, columnCount++, persona.getAPELLIDO2(), style);
			createCell(row, columnCount++, persona.getEMAIL(), style);
			createCell(row, columnCount++, persona.getDNI(), style);

		}
	}

	public void generate(HttpServletResponse response) throws IOException {
		writeHeader();
		write();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}
