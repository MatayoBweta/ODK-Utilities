/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model;

/**
 *
 * @author Stanyslas Matayo
 */
public class QuestionPosition {

    private String position;
    private String name;

    public QuestionPosition(String position, String name) {
        this.position = position;
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

}
