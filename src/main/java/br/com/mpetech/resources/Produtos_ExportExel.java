package br.com.mpetech.resources;

import br.com.mpetech.model.Produtos;
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
public class Produtos_ExportExel {

    public static ByteArrayInputStream produtosExportExel(List<Produtos> produtos) {

        try (Workbook documento = new XSSFWorkbook()) {

            Sheet folha = documento.createSheet("produtos");

            Row row = folha.createRow(0);
            CellStyle headerCellStyle = documento.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            

            Cell cell = row.createCell(0);
            cell.setCellValue("ID");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Tipo");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("Marca");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(3);
            cell.setCellValue("Nome");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(4);
            cell.setCellValue("Descrição");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(5);
            cell.setCellValue("Status estoque");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(6);
            cell.setCellValue("Status produto");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(7);
            cell.setCellValue("1° imagem");
            cell.setCellStyle(headerCellStyle);
            
            cell = row.createCell(8);
            cell.setCellValue("2° imagem");
            cell.setCellStyle(headerCellStyle);
            
            cell = row.createCell(9);
            cell.setCellValue("3° imagem");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(10);
            cell.setCellValue("Estrelas");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(11);
            cell.setCellValue("Valor Produto");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(12);
            cell.setCellValue("Quatidade");
            cell.setCellStyle(headerCellStyle);

            for (int i = 0; i < produtos.size(); i++) {
                Row dataRow = folha.createRow(i + 1);
                dataRow.createCell(0).setCellValue(produtos.get(i).getId());
                dataRow.createCell(1).setCellValue(produtos.get(i).getEnumTipoProduto().getSigla());
                dataRow.createCell(2).setCellValue(produtos.get(i).getEnumMarcaProduto().getSigla());
                dataRow.createCell(3).setCellValue(produtos.get(i).getNomeProduto());
                dataRow.createCell(4).setCellValue(produtos.get(i).getDescricaoProduto());
                dataRow.createCell(5).setCellValue(produtos.get(i).getEnumStatusEstoque().getSigla());
                dataRow.createCell(6).setCellValue(produtos.get(i).getEnumStatusProduto().getSigla());
                dataRow.createCell(7).setCellValue(produtos.get(i).getImagemPrimaria());
                dataRow.createCell(8).setCellValue(produtos.get(i).getImagemSecundaria());
                dataRow.createCell(9).setCellValue(produtos.get(i).getImagemTerciaria());
                dataRow.createCell(10).setCellValue(produtos.get(i).getEstrelaProduto());
                dataRow.createCell(11).setCellValue(produtos.get(i).getValorProduto().toPlainString());
                dataRow.createCell(12).setCellValue(produtos.get(i).getQuantidadeProduto());
            }

            folha.autoSizeColumn(0);
            folha.autoSizeColumn(1);
            folha.autoSizeColumn(2);
            folha.autoSizeColumn(3);
            folha.autoSizeColumn(4);
            folha.autoSizeColumn(5);
            folha.autoSizeColumn(6);
            folha.autoSizeColumn(7);
            folha.autoSizeColumn(8);
            folha.autoSizeColumn(9);
            folha.autoSizeColumn(10);
            folha.autoSizeColumn(11);
            folha.autoSizeColumn(12);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            documento.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException erro) {
            erro.getMessage();
            return null;
        }
    }
}
