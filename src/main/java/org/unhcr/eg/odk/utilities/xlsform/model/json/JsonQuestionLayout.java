/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model.json;

/**
 *
 * @author Stanyslas Matayo
 */
public class JsonQuestionLayout {

    private String key;
    private String prepend;
    private String append;
    private boolean notitle;
    private String htmlClass;
    private String fieldHtmlClass;
    private boolean disabled;
    private String allowEmpty;

    // possible values : text, password, textarea, ace,  date, datetime, datetime-local, email, month, search, tel, time, url, week (for type string in schema0
    // possible values : range (for type number in schema)
    // possible values : checkboxes (for array type with enum values in schema)
    // possible values : radios (for type string, number,integer with enum values schema0
    private String type;
    private String aceMode;
    private String aceTheme;
    private String width;
    private String height;
    // format just for type string : color, 
    private String format;
    // step just for type range
    private int step;
    // Just for boolean
    private String inlinetitle;
    // Just for Value with enum
    private String titleMap;
    
    

}
