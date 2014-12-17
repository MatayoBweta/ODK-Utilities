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
public class List {

    private final String name;
    private final HashMap<String, Item> listOfItems = new HashMap<>();

    public List(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Item> getListOfItems() {
        return listOfItems;
    }

}
