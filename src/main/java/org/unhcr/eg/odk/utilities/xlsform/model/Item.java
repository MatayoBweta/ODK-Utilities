/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model;

import java.util.HashMap;
import org.unhcr.eg.odk.utilities.xlsform.XLSFormModel;

/**
 *
 * @author Stanyslas Matayo
 */
public class Item {

    private String listName;
    private String name;
    private MultiLanguageValue label = new MultiLanguageValue(XLSFormModel.SheetColumn.CHOICE_LABEL);
    private HashMap<String, Object> columns = new HashMap<>();

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultiLanguageValue getLabel() {
        return label;
    }

    public HashMap<String, Object> getColumns() {
        return columns;
    }

}
