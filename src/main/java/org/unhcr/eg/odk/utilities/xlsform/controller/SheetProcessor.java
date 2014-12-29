/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.controller;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.unhcr.eg.odk.utilities.xlsform.XLSFormModel;
import org.unhcr.eg.odk.utilities.xlsform.model.Column;
import org.unhcr.eg.odk.utilities.xlsform.model.Formula;
import org.unhcr.eg.odk.utilities.xlsform.model.Item;
import org.unhcr.eg.odk.utilities.xlsform.model.ListItem;
import org.unhcr.eg.odk.utilities.xlsform.model.MultiLanguageValue;
import org.unhcr.eg.odk.utilities.xlsform.model.Question;
import org.unhcr.eg.odk.utilities.xlsform.model.QuestionPosition;
import org.unhcr.eg.odk.utilities.xlsform.model.QuestionTypeFormula;
import org.unhcr.eg.odk.utilities.xlsform.model.Survey;

/**
 *
 * @author Stanyslas Matayo This class will help to upload the content of excel
 * sheet describe for ODK Collection Platform and transform it in Java Object
 * The transformation to Java Object will facilitate the conversation to another
 * format if is needed
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
                String position = getQuestionPosition(q, survey);
                survey.getQuestions().put(new QuestionPosition(i, position, q.getName()), q);
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

    protected static void loadChoicesColumns(Iterator<Cell> cellIterator, Survey survey) {
        int j = 0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String columnName = cell.getStringCellValue();
            Column c = new Column(columnName);
            survey.getChoicesColumns().put(j, c);
            j++;
        }
    }

    protected static void loadSettingsColumns(Iterator<Cell> cellIterator, Survey survey) {
        int j = 0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String columnName = cell.getStringCellValue();
            Column c = new Column(columnName);
            survey.getSettingsColumns().put(j, c);
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
                final QuestionTypeFormula formula = new QuestionTypeFormula(XLSFormModel.SheetColumn.SURVEY_TYPE, cell.getStringCellValue());
                q.setType(formula);
            }
            j++;
        }
        return q;
    }

    protected static Item createChoice(Iterator<Cell> cellIterator, Survey survey) {
        Item q = new Item();
        int j = 0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            Column c = survey.getChoicesColumns().get(j);
            if (c.getName().equals(XLSFormModel.SheetColumn.CHOICE_LIST_NAME.value())) {
                q.setListName(cell.getStringCellValue());
            } else if (c.getName().equals(XLSFormModel.SheetColumn.CHOICE_LABEL.value())) {
                MultiLanguageValue label = q.getLabel();
                if (c.getLanguage() != null) {
                    label.addValuePerLanguage(c.getLanguage(), cell.getStringCellValue());
                } else if (survey.getSettings().get(XLSFormModel.SheetColumn.SETTINGS_DEFAULT_LANGUAGE.value()) != null) {
                    label.addValuePerLanguage(survey.getSettings().get(XLSFormModel.SheetColumn.SETTINGS_DEFAULT_LANGUAGE.value()), cell.getStringCellValue());
                } else {
                    label.addValuePerLanguage(survey.getDefault_language(), cell.getStringCellValue());
                }
            } else if (c.getName().equals(XLSFormModel.SheetColumn.CHOICE_NAME.value())) {
                q.setName(getWithIntCelValue(cell).toString());
            } else {
                q.getColumns().put(c.getName(), getCelValue(cell));
            }
            j++;
        }
        return q;
    }

    public static Survey processSettingsSheet(Workbook wb, Survey survey) {
        Sheet sheet = wb.getSheet(XLSFormModel.SheetName.SETTINGS.value());
        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        int i = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next(); //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            if (i == 0) {
                loadSettingsColumns(cellIterator, survey);
            } else if (i == 1) {
                survey = createSetting(cellIterator, survey);
            }
            i++;
        }
        return survey;
    }

    public static Survey processChoicesSheet(Workbook wb, Survey survey) {
        Sheet sheet = wb.getSheet(XLSFormModel.SheetName.CHOICES.value());
        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        int i = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next(); //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            if (i == 0) {
                loadChoicesColumns(cellIterator, survey);
            } else {
                Item q = createChoice(cellIterator, survey);
                ListItem listItem = survey.getChoices().get(q.getListName());
                if (listItem == null) {
                    listItem = new ListItem(q.getListName());
                }
                listItem.getListOfItems().put(q.getName(), q);
                survey.getChoices().put(listItem.getName(), listItem);
            }
            i++;
        }
        return survey;

    }

    protected static Survey createSetting(Iterator<Cell> cellIterator, Survey survey) {
        int j = 0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            Column c = survey.getSettingsColumns().get(j);
            survey.getSettings().put(c.getName(), cell.getStringCellValue());
            j++;
        }
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
                getCelValue(cell);
            }

        }

    }

    public static Object getCelValue(Cell cell) {
        Object cellValue = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue();
                } else {
                    cellValue = cell.getNumericCellValue();
                }
                break;
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                cellValue = new String("");
                break;
            case Cell.CELL_TYPE_ERROR:
                cellValue = cell.getErrorCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
        }
        return cellValue;
    }

    public static Object getWithIntCelValue(Cell cell) {
        Object cellValue = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue();
                } else {
                    cellValue = cell.getNumericCellValue();
                    Double i = (Double) cellValue;
                    cellValue = i.intValue();
                }
                break;
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                cellValue = new String("");
                break;
            case Cell.CELL_TYPE_ERROR:
                cellValue = cell.getErrorCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
        }
        return cellValue;
    }

    protected static String getQuestionPosition(Question q, Survey survey) {
        String questionNumber = survey.getCurrentQuestionNumber();

        if (survey.getNextAction().equals(Survey.NextAction.GIVE_NEXT)) {
            String getLastNumber = survey.getCurrentQuestionNumber();
            if (!q.getType().isEndOfParentQuestion()) {
                questionNumber = getNextToken(getLastNumber);
            } else {
                questionNumber = getParentNumber(getLastNumber);
            }
        } else if (survey.getNextAction().equals(Survey.NextAction.GIVE_CHILD)) {
            questionNumber = survey.getCurrentQuestionNumber().concat("#").concat("1");
        }

        if (q.getType().isParentQuestion()) {
            survey.setNextAction(Survey.NextAction.GIVE_CHILD);
        } else {
            survey.setNextAction(Survey.NextAction.GIVE_NEXT);
        }

        survey.setCurrentQuestionNumber(questionNumber);
        return questionNumber;
    }

    public static String getNextToken(String getLastNumber) throws NumberFormatException {
        String nextValue = null;
        Integer next;
        if (getLastNumber.contains("#")) {
            TreeMap<Integer, Integer> tokens = new TreeMap<>();
            int tokenPlace = extractTokens(getLastNumber, tokens);
            next = tokens.get(tokenPlace) + 1;
            for (int j = 1; j <= tokenPlace; j++) {
                if (j == 1) {
                    nextValue = Integer.toString(tokens.get(j));
                } else if (j < tokenPlace) {
                    nextValue = nextValue.concat("#").concat(Integer.toString(tokens.get(j)));
                } else if (j == tokenPlace) {
                    nextValue = nextValue.concat("#").concat(next.toString());
                }
            }
        } else {
            next = Integer.parseInt(getLastNumber) + 1;
            nextValue = next.toString();
        }
        return nextValue;
    }

    public static int extractTokens(String getLastNumber, TreeMap<Integer, Integer> tokens) throws NumberFormatException {
        int tokenPlace = 0;
        for (StringTokenizer stringTokenizer = new StringTokenizer(getLastNumber, "#"); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            tokenPlace++;
            tokens.put(tokenPlace, Integer.parseInt(token));
        }
        return tokenPlace;
    }

    private static String getParentNumber(String currentQuestionNumber) {
        String parentID = null;
        TreeMap<Integer, Integer> tokens = new TreeMap<>();
        if (currentQuestionNumber.contains("#")) {
            int tokenPlace = extractTokens(currentQuestionNumber, tokens);
            for (int j = 1; j < tokenPlace; j++) {
                if (j == 1) {
                    parentID = Integer.toString(tokens.get(j));
                } else if (j < tokenPlace) {
                    parentID = parentID.concat("#").concat(Integer.toString(tokens.get(j)));
                }
            }
        }
        return parentID;
    }

    private static String getNextParentNumber(String currentQuestionNumber) {
        String nextID = getNextToken(getParentNumber(currentQuestionNumber));
        return nextID;
    }

}
