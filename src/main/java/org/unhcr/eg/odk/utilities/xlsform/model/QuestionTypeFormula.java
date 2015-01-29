/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model;

import org.unhcr.eg.odk.utilities.xlsform.XLSFormModel;

/**
 *
 * @author Stanyslas Matayo
 */
public class QuestionTypeFormula extends Formula {

    public QuestionTypeFormula(XLSFormModel.SheetColumn column, String value) {
        super(column, value);
        if (!column.equals(XLSFormModel.SheetColumn.SURVEY_TYPE)) {
            throw new IllegalArgumentException("The formula should be for question type only");
        }
    }

    public String getChoiceName() {
        String choice = null;
        if (isChoiceQuestion()) {
            choice = getValue().replace(XLSFormModel.Type_Field.SELECT_MULTIPLE.value(), "")
                    .replace(XLSFormModel.Type_Field.SELECT_ONE.value(), "")
                    .replace("or_other", "")
                    .replace(" ", "");

        } else {
            choice = NOT_A__CHOICE_COLUMN_DESCRPTION;
        }
        return choice;
    }
    public static final String NOT_A__CHOICE_COLUMN_DESCRPTION = "Not a Choice column descrption";

    public boolean isChoiceQuestion() {
        return getColumn().equals(XLSFormModel.SheetColumn.SURVEY_TYPE) && (getValue().contains(XLSFormModel.Type_Field.SELECT_MULTIPLE.value()) || getValue().contains(XLSFormModel.Type_Field.SELECT_ONE.value()));
    }
      
    public boolean isParentQuestion() {
        return getColumn().equals(XLSFormModel.SheetColumn.SURVEY_TYPE) && (getValue().contains(XLSFormModel.Type_Field.BEGIN_GROUP.value()) || getValue().contains(XLSFormModel.Type_Field.BEGIN_REPEAT.value()));
    }
    
    public boolean isEndOfParentQuestion() {
        return getColumn().equals(XLSFormModel.SheetColumn.SURVEY_TYPE) && (getValue().contains(XLSFormModel.Type_Field.END_GROUP.value()) || getValue().contains(XLSFormModel.Type_Field.END_REPEAT.value()));
    }
    

}
