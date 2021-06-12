package br.com.mpetech.resources;

import br.com.mpetech.model.Usuarios;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
public class Usuarios_ExportExel {

    public static ByteArrayInputStream usuariosExportExel(List<Usuarios> usuarios) {

        try (Workbook documento = new XSSFWorkbook()) {

            Sheet folha = documento.createSheet("usuarios");

            Row row = folha.createRow(0);
            CellStyle headerCellStyle = documento.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            

            Cell cell = row.createCell(0);
            cell.setCellValue("ID");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Nome");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("Email");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(3);
            cell.setCellValue("Perfil");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(4);
            cell.setCellValue("Status");
            cell.setCellStyle(headerCellStyle);


            for (int i = 0; i < usuarios.size(); i++) {
                Row dataRow = folha.createRow(i + 1);
                dataRow.createCell(0).setCellValue(usuarios.get(i).getId());
                dataRow.createCell(1).setCellValue(usuarios.get(i).getNome());
                dataRow.createCell(2).setCellValue(usuarios.get(i).getEmail());
                dataRow.createCell(3).setCellValue(usuarios.get(i).getPerfil());
                dataRow.createCell(4).setCellValue(usuarios.get(i).getStatus());
            }

            folha.autoSizeColumn(0);
            folha.autoSizeColumn(1);
            folha.autoSizeColumn(2);
            folha.autoSizeColumn(3);
            folha.autoSizeColumn(4);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            documento.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException erro) {
            erro.getMessage();
            return null;
        }
    }
}
