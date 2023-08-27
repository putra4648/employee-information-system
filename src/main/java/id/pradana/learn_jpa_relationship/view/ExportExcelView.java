
package id.pradana.learn_jpa_relationship.view;

import id.pradana.learn_jpa_relationship.dto.EmployeeDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

public class ExportExcelView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        SXSSFWorkbook workbook = new SXSSFWorkbook();

        List<EmployeeDto> employees = (List<EmployeeDto>) model.get("employees");
        List<String> columnNames = Arrays.asList("Employee ID", "Employee Name",
                "Hiring Date", "Work From Date");

        SXSSFSheet sheet = workbook.createSheet("List Employees");

        int rowIndex = 0;
        int colIndex = 0;
        SXSSFRow row;
        SXSSFCell cell;
        CellStyle style;
        Font font;

        // Styling
        style = workbook.createCellStyle();
        font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        sheet.trackAllColumnsForAutoSizing();

        // Create column
        row = sheet.createRow(rowIndex);
        for (String col : columnNames) {
            cell = row.createCell(colIndex);
            cell.setCellValue(col.toUpperCase());
            cell.setCellStyle(style);
            sheet.trackColumnForAutoSizing(colIndex);
            colIndex++;
        }

        rowIndex++;

        // Fill date
        colIndex = 0;

        for (EmployeeDto data : employees) {
            row = sheet.createRow(rowIndex);

            cell = row.createCell(0);
            cell.setCellValue(data.getId());
            sheet.autoSizeColumn(0);

            cell = row.createCell(1);
            cell.setCellValue(data.getFullname());
            sheet.autoSizeColumn(1);

            cell = row.createCell(2);
            cell.setCellValue(sdf.format(new Date(data.getBirthdate())));
            sheet.autoSizeColumn(2);

            cell = row.createCell(3);
            cell.setCellValue(sdf.format(new Date(data.getHiredate())));
            sheet.autoSizeColumn(3);

            rowIndex++;
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String filename = "List Employee " + currentDateTime;
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + filename + ".xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
