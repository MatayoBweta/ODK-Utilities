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
public class ListItem {

    private final String name;
    private final TreeMap<String, Item> listOfItems = new TreeMap<>();

    public ListItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public TreeMap<String, Item> getListOfItems() {
        return listOfItems;
    }

}
