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
public class Question {

    private QuestionTypeFormula type;
    private String name;
    private MultiLanguageValue label;
    private Formula choice_filer;
    private String default_value;
    private MultiLanguageValue hint;
    private Formula constraint;
    private MultiLanguageValue constraint_message;
    private Formula appearance;
    private Formula relevant;
    private Formula read_only;
    private Formula calculation;
    private String path;

    public Question(QuestionTypeFormula type, String name, MultiLanguageValue label, Formula choice_filer, String default_value, MultiLanguageValue hint, Formula constraint, MultiLanguageValue constraint_message, Formula appearance, Formula relevant, Formula read_only, Formula calculation) {
        this.type = type;
        this.name = name;
        this.label = label;
        this.choice_filer = choice_filer;
        this.default_value = default_value;
        this.hint = hint;
        this.constraint = constraint;
        this.constraint_message = constraint_message;
        this.appearance = appearance;
        this.relevant = relevant;
        this.read_only = read_only;
        this.calculation = calculation;
    }

    public Question() {
       
    }


    public QuestionTypeFormula getType() {
        return type;
    }

    public void setType(QuestionTypeFormula type) {
        this.type = type;
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

    public void setLabel(MultiLanguageValue label) {
        this.label = label;
    }

    public Formula getChoice_filer() {
        return choice_filer;
    }

    public void setChoice_filer(Formula choice_filer) {
        this.choice_filer = choice_filer;
    }

    public String getDefault_value() {
        return default_value;
    }

    public void setDefault_value(String default_value) {
        this.default_value = default_value;
    }

    public MultiLanguageValue getHint() {
        return hint;
    }

    public void setHint(MultiLanguageValue hint) {
        this.hint = hint;
    }

    public Formula getConstraint() {
        return constraint;
    }

    public void setConstraint(Formula constraint) {
        this.constraint = constraint;
    }

    public MultiLanguageValue getConstraint_message() {
        return constraint_message;
    }

    public void setConstraint_message(MultiLanguageValue constraint_message) {
        this.constraint_message = constraint_message;
    }

    public Formula getAppearance() {
        return appearance;
    }

    public void setAppearance(Formula appearance) {
        this.appearance = appearance;
    }

    public Formula getRelevant() {
        return relevant;
    }

    public void setRelevant(Formula relevant) {
        this.relevant = relevant;
    }

    public Formula getRead_only() {
        return read_only;
    }

    public void setRead_only(Formula read_only) {
        this.read_only = read_only;
    }

    public Formula getCalculation() {
        return calculation;
    }

    public void setCalculation(Formula calculation) {
        this.calculation = calculation;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    

}
