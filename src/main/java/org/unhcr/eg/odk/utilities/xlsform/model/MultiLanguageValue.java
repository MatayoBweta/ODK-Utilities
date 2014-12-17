/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model;

import java.util.HashMap;
import java.util.logging.Logger;
import org.unhcr.eg.odk.utilities.xlsform.XLSFormModel;

/**
 *
 * @author Stanyslas Matayo
 */
public class MultiLanguageValue {

    private XLSFormModel.SheetColumn description;
    private HashMap<String, String> values = new HashMap<>();
    private static final Logger LOG = Logger.getLogger(MultiLanguageValue.class.getName());

    public MultiLanguageValue(XLSFormModel.SheetColumn description) {
        this.description = description;
    }

    public void addValuePerLanguage(String language, String value) {
        values.put(language, value);
    }
    
    public void getValuePerLanguage(String language) {
        values.get(language);
    }

}
