/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Stanyslas Matayo
 */
public class Survey {

    private final HashMap<Integer, Column> columns = new HashMap<>();
    private final HashMap<String, String> settings = new HashMap<>();
    private final HashMap<QuestionPosition, Question> questions = new HashMap<>();
    private final HashMap<Integer, List> choices = new HashMap<>();
    private String default_language;
    private final static String DEFAULT_LANGUAGE = "ENG";

    public HashMap<Integer, Column> getColumns() {
        return columns;
    }

    public HashMap<String, String> getSettings() {
        return settings;
    }

    public HashMap<QuestionPosition, Question> getQuestions() {
        return questions;
    }

    public HashMap<Integer, List> getChoices() {
        return choices;
    }

    public String getDefault_language() {
        if (default_language != null) {
            return default_language;
        }
        return DEFAULT_LANGUAGE;
    }

    public Survey(String default_language) {
        this.default_language = default_language;
    }

    public String addNextQuestion(Question question) {
        
        Set<QuestionPosition> questionPositions = questions.keySet();
        for (QuestionPosition next : questionPositions) {
            
        }

        return null;
    }

}
