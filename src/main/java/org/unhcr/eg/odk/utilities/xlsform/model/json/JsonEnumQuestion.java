/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model.json;

import java.util.List;

/**
 *
 * @author UNHCRuser
 */
public class JsonEnumQuestion extends JsonQuestion {

    private List<String> enumeration;

    public JsonEnumQuestion(String type) {
        super(type);
    }

}
