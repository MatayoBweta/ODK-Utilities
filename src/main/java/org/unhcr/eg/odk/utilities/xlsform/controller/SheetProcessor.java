/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.controller;

import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.unhcr.eg.odk.utilities.xlsform.XLSFormModel;
import org.unhcr.eg.odk.utilities.xlsform.model.Column;
import org.unhcr.eg.odk.utilities.xlsform.model.Formula;
import org.unhcr.eg.odk.utilities.xlsform.model.MultiLanguageValue;
import org.unhcr.eg.odk.utilities.xlsform.model.Question;
import org.unhcr.eg.odk.utilities.xlsform.model.Survey;

/**
 *
 * @author Stanyslas Matayo
 */
public class SheetProcessor {

    public static Survey processSurveySheet(Workbook wb, Survey survey) {
        Sheet sheet = wb.getSheet(XLSFormModel.SheetName.SURVEY.value());
        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        int i = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next(); //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            if (i == 0) {
                loadColumns(cellIterator, survey);
            } else {
                Question q = createQuestion(cellIterator, survey);
                
            }
            i++;
        }
        return survey;

    }

    protected static void loadColumns(Iterator<Cell> cellIterator, Survey survey) {
        int j = 0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String columnName = cell.getStringCellValue();
            Column c = new Column(columnName);
            survey.getColumns().put(j, c);
            j++;
        }
    }

    protected static Question createQuestion(Iterator<Cell> cellIterator, Survey survey) {
        Question q = new Question();
        int j = 0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            Column c = survey.getColumns().get(j);
            if (c.getName().equals(XLSFormModel.SheetColumn.SURVEY_APPEARANCE.value())) {
                q.setAppearance(new Formula(XLSFormModel.SheetColumn.SURVEY_APPEARANCE, cell.getStringCellValue()));
            } else if (c.getName().equals(XLSFormModel.SheetColumn.SURVEY_CALCULATION.value())) {
                q.setCalculation(new Formula(XLSFormModel.SheetColumn.SURVEY_CALCULATION, cell.getStringCellValue()));
            } else if (c.getName().equals(XLSFormModel.SheetColumn.SURVEY_CHOICE_FILTER.value())) {
                q.setChoice_filer(new Formula(XLSFormModel.SheetColumn.SURVEY_CHOICE_FILTER, cell.getStringCellValue()));
            } else if (c.getName().equals(XLSFormModel.SheetColumn.SURVEY_CONSTRAINT.value())) {
                q.setConstraint(new Formula(XLSFormModel.SheetColumn.SURVEY_CONSTRAINT, cell.getStringCellValue()));
            } else if (c.getName().equals(XLSFormModel.SheetColumn.SURVEY_CONSTRAINT_MESSAGE.value())) {
                MultiLanguageValue constraint_message = q.getConstraint_message();
                if (constraint_message == null) {
                    constraint_message = new MultiLanguageValue(XLSFormModel.SheetColumn.SURVEY_CONSTRAINT_MESSAGE);
                }
                if (c.getLanguage() != null) {
                    constraint_message.addValuePerLanguage(c.getLanguage(), cell.getStringCellValue());
                } else if (survey.getSettings().get(XLSFormModel.SheetColumn.SETTINGS_DEFAULT_LANGUAGE.value()) != null) {
                    constraint_message.addValuePerLanguage(survey.getSettings().get(XLSFormModel.SheetColumn.SETTINGS_DEFAULT_LANGUAGE.value()), cell.getStringCellValue());
                } else {
                    constraint_message.addValuePerLanguage(survey.getDefault_language(), cell.getStringCellValue());
                }
                q.setConstraint_message(constraint_message);
            } else if (c.getName().equals(XLSFormModel.SheetColumn.SURVEY_DEFAULT.value())) {
                q.setDefault_value(cell.getStringCellValue());
            } else if (c.getName().equals(XLSFormModel.SheetColumn.SURVEY_HINT.value())) {

                MultiLanguageValue hint = q.getHint();
                if (hint == null) {
                    hint = new MultiLanguageValue(XLSFormModel.SheetColumn.SURVEY_HINT);
                }
                if (c.getLanguage() != null) {
                    hint.addValuePerLanguage(c.getLanguage(), cell.getStringCellValue());
                } else if (survey.getSettings().get(XLSFormModel.SheetColumn.SETTINGS_DEFAULT_LANGUAGE.value()) != null) {
                    hint.addValuePerLanguage(survey.getSettings().get(XLSFormModel.SheetColumn.SETTINGS_DEFAULT_LANGUAGE.value()), cell.getStringCellValue());
                } else {
                    hint.addValuePerLanguage(survey.getDefault_language(), cell.getStringCellValue());
                }
                q.setHint(hint);
            } else if (c.getName().equals(XLSFormModel.SheetColumn.SURVEY_LABEL.value())) {
                MultiLanguageValue label = q.getLabel();
                if (label == null) {
                    label = new MultiLanguageValue(XLSFormModel.SheetColumn.SURVEY_LABEL);
                }
                if (c.getLanguage() != null) {
                    label.addValuePerLanguage(c.getLanguage(), cell.getStringCellValue());
                } else if (survey.getSettings().get(XLSFormModel.SheetColumn.SETTINGS_DEFAULT_LANGUAGE.value()) != null) {
                    label.addValuePerLanguage(survey.getSettings().get(XLSFormModel.SheetColumn.SETTINGS_DEFAULT_LANGUAGE.value()), cell.getStringCellValue());
                } else {
                    label.addValuePerLanguage(survey.getDefault_language(), cell.getStringCellValue());
                }
                q.setLabel(label);
            } else if (c.getName().equals(XLSFormModel.SheetColumn.SURVEY_NAME.value())) {
                q.setName(cell.getStringCellValue());
            } else if (c.getName().equals(XLSFormModel.SheetColumn.SURVEY_READ_ONLY.value())) {
                q.setRead_only(new Formula(XLSFormModel.SheetColumn.SURVEY_READ_ONLY, cell.getStringCellValue()));
            } else if (c.getName().equals(XLSFormModel.SheetColumn.SURVEY_RELEVANT.value())) {
                q.setRelevant(new Formula(XLSFormModel.SheetColumn.SURVEY_RELEVANT, cell.getStringCellValue()));
            } else if (c.getName().equals(XLSFormModel.SheetColumn.SURVEY_TYPE.value())) {
                q.setType(new Formula(XLSFormModel.SheetColumn.SURVEY_TYPE, cell.getStringCellValue()));
            }
            j++;
        }
        return q;
    }

    public static Survey processSettingsSheet(Workbook wb, Survey survey) {
        Sheet sheet = wb.getSheet(XLSFormModel.SheetName.SETTINGS.value());

        return survey;

    }

    public static Survey processChoicesSheet(Workbook wb, Survey survey) {
        Sheet sheet = wb.getSheet(XLSFormModel.SheetName.CHOICES.value());

        return survey;

    }

    protected static void copyContent(Sheet sheetSource) {
        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheetSource.iterator();
        int i = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            i++;
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            int j = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                j++;
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        cell.getBooleanCellValue();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            cell.getDateCellValue();
                        } else {
                            cell.getNumericCellValue();
                        }
                        break;
                    case Cell.CELL_TYPE_STRING:
                        cell.getRichStringCellValue();
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        cell.getErrorCellValue();
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        cell.getCellFormula();
                        break;
                }

            }

        }

    }

}
