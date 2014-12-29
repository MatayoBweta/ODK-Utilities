/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model;

import java.util.TreeMap;

/**
 *
 * @author Stanyslas Matayo
 */
public class Survey {

    private final TreeMap<Integer, Column> columns = new TreeMap<>();
    private final TreeMap<Integer, Column> choicesColumns = new TreeMap<>();
    private final TreeMap<Integer, Column> settingsColumns = new TreeMap<>();

    private final TreeMap<String, String> settings = new TreeMap<>();
    private final TreeMap<QuestionPosition, Question> questions = new TreeMap<>();
    private final TreeMap<String, ListItem> choices = new TreeMap<>();
    private String default_language;
    private final static String DEFAULT_LANGUAGE = "ENG";
    private NextAction nextAction = NextAction.GIVE_NEXT;
    private String currentQuestionNumber = "0";

    public enum NextAction {

        GIVE_NEXT,
        GIVE_PARENT_NEXT,
        GIVE_CHILD;

    }

    public TreeMap<Integer, Column> getColumns() {
        return columns;
    }

    public TreeMap<Integer, Column> getChoicesColumns() {
        return choicesColumns;
    }

    public TreeMap<Integer, Column> getSettingsColumns() {
        return settingsColumns;
    }

    public TreeMap<String, String> getSettings() {
        return settings;
    }

    public TreeMap<QuestionPosition, Question> getQuestions() {
        return questions;
    }

    public TreeMap<String, ListItem> getChoices() {
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
