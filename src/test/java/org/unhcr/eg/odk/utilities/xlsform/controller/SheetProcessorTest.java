/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.controller;

import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.unhcr.eg.odk.utilities.xlsform.model.Item;
import org.unhcr.eg.odk.utilities.xlsform.model.Question;
import org.unhcr.eg.odk.utilities.xlsform.model.Survey;

/**
 *
 * @author UNHCRuser
 */
public class SheetProcessorTest {
    
    public SheetProcessorTest() {
    }
    
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
     */
    @Test
    public void testSurveySheet() {
        System.out.println("processSurveySheet");
        Workbook wb = null;
        Survey survey = null;
        Survey expResult = null;
        Survey result = SheetProcessor.processSurveySheet(wb, survey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}
