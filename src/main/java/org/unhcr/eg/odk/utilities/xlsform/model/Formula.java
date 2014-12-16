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
 * @author UNHCRuser
 */
public class Formula {

    private final XLSFormModel.SheetColumn description;
    private String value;
    private static final Logger LOG = Logger.getLogger(Formula.class.getName());

    public Formula(XLSFormModel.SheetColumn description, String value) {
        this.description = description;
        this.value = value;
    }

}
