/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model;

import java.util.StringTokenizer;
import org.unhcr.eg.odk.utilities.xlsform.XLSFormModel.SheetColumn;

/**
 *
 * @author UNHCRuser
 */
public class Column {

    private SheetColumn description;
    private String name;
    private String label;
    private String language;
    private boolean supportLangage;
    private boolean multiLanguage = false;
    private boolean supportExpression;

    public SheetColumn getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    public boolean isMultiLanguage() {
        return multiLanguage;
    }

    public boolean isSupportLangage() {
        return supportLangage;
    }

    public boolean isSupportExpression() {
        return supportExpression;
    }

    public String getLabel() {
        return label;
    }

    public Column(String label) {
        processName(label);
    }

    protected final void processName(String label) {
        String finalName = this.label = label;
        boolean finalSupportLanguage;
        this.description = SheetColumn.getSheetColumn(label);
        if (description != null) {
            finalSupportLanguage = description.supportLangage();
            this.supportExpression = description.supportExpression();
        } else {
            finalSupportLanguage = false;
            this.supportExpression = false;
        }
        if (label.contains("::")) {
            StringTokenizer tokenizer = new StringTokenizer(label, "::");
            finalName = tokenizer.nextToken();
            finalSupportLanguage = true;
            this.language = tokenizer.nextToken();
            this.multiLanguage = true;
        }
        this.name = finalName;
        this.supportLangage = finalSupportLanguage;
    }

}
