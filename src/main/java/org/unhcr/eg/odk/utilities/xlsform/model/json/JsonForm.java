/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model.json;

import org.apache.commons.beanutils.DynaBean;

/**
 *
 * @author Stanyslas Matayo
 */
public class JsonForm {

    private DynaBean schema;
    private DynaBean form;
    private String onSubmitValid;

    public JsonForm(DynaBean schema, DynaBean form, String onSubmitValid) {
        this.schema = schema;
        this.form = form;
        this.onSubmitValid = onSubmitValid;
    }

    public DynaBean getSchema() {
        return schema;
    }

    public void setSchema(DynaBean schema) {
        this.schema = schema;
    }

    public DynaBean getForm() {
        return form;
    }

    public void setForm(DynaBean form) {
        this.form = form;
    }

    public String getOnSubmitValid() {
        return onSubmitValid;
    }

    public void setOnSubmitValid(String onSubmitValid) {
        this.onSubmitValid = onSubmitValid;
    }

}
