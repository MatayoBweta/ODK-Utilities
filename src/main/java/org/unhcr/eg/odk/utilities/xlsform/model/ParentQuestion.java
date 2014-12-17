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
public class ParentQuestion {

    private Question parentQuestion;
    private Question q;
    private int status = 0;
    private static final int PENDING = 0;
    private static final int CLOSED = 1;

    public ParentQuestion(Question q, Question parentQuestion) {
        this.parentQuestion = parentQuestion;
        this.q = q;
    }

}
