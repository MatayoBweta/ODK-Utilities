/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.unhcr.eg.odk.utilities.xlsform.model.Survey;

/**
 *
 * @author Stanyslas Matayo
 */
public class XLSFormController {

    public void tranformXLSToJson(String fileLocation) throws FileNotFoundException, IOException, InvalidFormatException {

        //upload the excel sheet

        Workbook wb = new HSSFWorkbook(new FileInputStream(fileLocation));
        Survey survey = SheetProcessor.processSettingsSheet(wb, new Survey("ENG"));
        survey = SheetProcessor.processChoicesSheet(wb, survey);
        survey = SheetProcessor.processSurveySheet(wb, survey);

    }

}
