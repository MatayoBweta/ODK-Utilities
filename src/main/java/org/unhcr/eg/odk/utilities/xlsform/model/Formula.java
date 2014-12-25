/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model;

import java.util.logging.Logger;
import org.unhcr.eg.odk.utilities.xlsform.XLSFormModel;

/**
 *
 * @author Stanyslas Matayo
 */
public class Formula {

    private final XLSFormModel.SheetColumn description;
    private String value;
    private static final Logger LOG = Logger.getLogger(Formula.class.getName());

    public Formula(XLSFormModel.SheetColumn description, String value) {
        this.description = description;
        this.value = value;
    }

    public String getChoiceName() {
        String choice = null;
        if (isChoiceQuestion()) {
            choice = value.replace(XLSFormModel.Type_Field.SELECT_MULTIPLE.value(), "")
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
        return description.equals(XLSFormModel.SheetColumn.SURVEY_TYPE) && (value.contains(XLSFormModel.Type_Field.SELECT_MULTIPLE.value()) || value.contains(XLSFormModel.Type_Field.SELECT_ONE.value()));
    }
}
