/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.excel;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Stanyslas Matayo
 */
public class ExcelFileUtility {

    public static void addToDestination(HSSFWorkbook source, HSSFWorkbook destination) {
        ArrayList<String> listOfSheet = getListOfSheets(destination);
        int numberOfSheet = source.getNumberOfSheets();
        int j = 0;
        int sheetDestinationIndex;
        for (int i = 0; i < numberOfSheet; i++) {
            Sheet sheetSource = source.getSheetAt(i);
            String newName = sheetSource.getSheetName();
            sheetDestinationIndex = destination.getSheetIndex(newName);
            while (listOfSheet.contains(newName)) {
                newName = sheetSource.getSheetName() + "_" + j;
                newName = newName.substring(Math.max(newName.length() - 31, 0), Math.min(newName.length(), 31));
                sheetDestinationIndex = destination.getSheetIndex(newName);
                j++;
            }
            listOfSheet.add(newName);
            Sheet sheetDestination = destination.createSheet(newName);
            copyContent(sheetSource, sheetDestination);
        }
    }

    protected static ArrayList<String> getListOfSheets(HSSFWorkbook workbook) {
        ArrayList<String> listOfSheets = new ArrayList<>();
        int numberOfSheet = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheet; i++) {
            listOfSheets.add(workbook.getSheetName(i));
        }
        return listOfSheets;
    }

    protected static void copyContent(Sheet sheetSource, Sheet sheetDestination) {
        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheetSource.iterator();
        int i = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Row rowDestination = sheetDestination.createRow(i);
            i++;
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            int j = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                Cell cellDestination = rowDestination.createCell(j);
                j++;
                cellDestination.setCellComment(cell.getCellComment());
//                cellDestination.setCellStyle(cell.getCellStyle());
                cellDestination.setCellType(cell.getCellType());
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        cellDestination.setCellValue(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            cellDestination.setCellValue(cell.getDateCellValue());
                        } else {
                            cellDestination.setCellValue(cell.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_STRING:
                        cellDestination.setCellValue(cell.getRichStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        cellDestination.setCellValue(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        cellDestination.setCellValue(cell.getErrorCellValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        cellDestination.setCellFormula(cell.getCellFormula());
                        break;
                }

            }

        }

    }
}
