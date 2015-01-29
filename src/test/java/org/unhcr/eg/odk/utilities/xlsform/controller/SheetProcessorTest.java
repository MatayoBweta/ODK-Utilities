/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.TreeMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.unhcr.eg.odk.utilities.xlsform.model.Item;
import org.unhcr.eg.odk.utilities.xlsform.model.ListItem;
import org.unhcr.eg.odk.utilities.xlsform.model.Question;
import org.unhcr.eg.odk.utilities.xlsform.model.QuestionPosition;
import org.unhcr.eg.odk.utilities.xlsform.model.Survey;

/**
 *
 * @author Stanyslas Matayo
 */
public class SheetProcessorTest {

    public SheetProcessorTest() {
    }

    public static final String SEAP_TEST_FILE = "/Vulnerabilty_Assessment_Survey_with_DB_20140721_1_1.xls";

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of processSurveySheet method, of class SheetProcessor.
     *
     * @throws java.net.URISyntaxException
     */
    @Test
    public void testSurveySheet() throws URISyntaxException, IOException {
        System.out.println("processSurveySheet");

        File testf = new File(this.getClass().getResource(SEAP_TEST_FILE).toURI());

        Workbook wb = new HSSFWorkbook(new FileInputStream(testf));
        Survey survey = SheetProcessor.processSettingsSheet(wb, new Survey("ENG"));
        survey = SheetProcessor.processChoicesSheet(wb, survey);
        survey = SheetProcessor.processSurveySheet(wb, survey);
        TreeMap<String, ListItem> choices = survey.getChoices();
        for (Map.Entry<String, ListItem> entrySet : choices.entrySet()) {
            String key = entrySet.getKey();
            System.out.println("List Name :" + key);
            ListItem value = entrySet.getValue();
            TreeMap<String, Item> listOfItems = value.getListOfItems();
            for (Map.Entry<String, Item> entrySet1 : listOfItems.entrySet()) {
                String key1 = entrySet1.getKey();
                System.out.println("-----Item Name :" + key1);
                Item value1 = entrySet1.getValue();
                System.out.println("-----Item Name :" + value1.getName());

            }
            assertNotNull("Survery not loaded", survey);

        }
        TreeMap<QuestionPosition, Question> questions = survey.getQuestions();
        for (Map.Entry<QuestionPosition, Question> entrySet : questions.entrySet()) {
            QuestionPosition key = entrySet.getKey();
            System.out.println("Question " + key.getExcelPosition());
            System.out.println("Question Internal Position " + key.getPosition());
            System.out.println("------Name " + key.getName());
           
            Question value = entrySet.getValue();
             System.out.println("------Type " + value.getType().getValue());
             System.out.println("------Choice " + value.getType().getChoiceName());

        }
    }
}
