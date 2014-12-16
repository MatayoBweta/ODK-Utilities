/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model;

import java.util.HashMap;
import org.unhcr.eg.odk.utilities.xlsform.XLSFormModel.SheetColumn;

/**
 *
 * @author UNHCRuser
 */
public class Item {

    private SheetColumn description;
    private MultiLanguageValue label;
    private HashMap<String, Object> columns = new HashMap<>();

}
