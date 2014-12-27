/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model;

import java.util.HashMap;

/**
 *
 * @author Stanyslas Matayo
 */
public class Survey {

    private final HashMap<Integer, Column> columns = new HashMap<>();
    private final HashMap<Integer, Column> choicesColumns = new HashMap<>();
    private final HashMap<Integer, Column> settingsColumns = new HashMap<>();

    private final HashMap<String, String> settings = new HashMap<>();
    private final HashMap<QuestionPosition, Question> questions = new HashMap<>();
    private final HashMap<String, ListItem> choices = new HashMap<>();
    private String default_language;
    private final static String DEFAULT_LANGUAGE = "ENG";
    private NextAction nextAction = NextAction.GIVE_NEXT;
    private String currentQuestionNumber = "";

    public enum NextAction {

        GIVE_NEXT,
        GIVE_PARENT_NEXT,
        GIVE_CHILD;

    }

    public HashMap<Integer, Column> getColumns() {
        return columns;
    }

    public HashMap<Integer, Column> getChoicesColumns() {
        return choicesColumns;
    }

    public HashMap<Integer, Column> getSettingsColumns() {
        return settingsColumns;
    }

    public HashMap<String, String> getSettings() {
        return settings;
    }

    public HashMap<QuestionPosition, Question> getQuestions() {
        return questions;
    }

    public HashMap<String, ListItem> getChoices() {
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

    public NextAction getNextAction() {
        return nextAction;
    }

    public void setNextAction(NextAction nextAction) {
        this.nextAction = nextAction;
    }

    public String getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public void setCurrentQuestionNumber(String currentQuestionNumber) {
        this.currentQuestionNumber = currentQuestionNumber;
    }

}
