/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Tsehai Boucaud
 */
package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.util.List;

public class addItem {
    private itemData newItem;
    private String error = "";
    addItem(){
        //empty constructor
        this.newItem = new itemData();
    }
    addItem(String sn, String val, String name, ObservableList<itemData> list){
        //Validate item, and then add it or print an error
        if(validateItem(sn, val, name, list)){
            this.newItem = new itemData(sn, val, name);
            }
    }
    public boolean validateItem(String sn, String val, String name, ObservableList<itemData> list){
        //check if SN is valid
        //check if Name is valid
        //Check if the Value is numeric, and can be turned into a double
        //return true if and only if all parameters have met constraints
        if(!validateSN(sn, list))
            error += "Serial must be unique, 10 characters\n";
        if(!validateValue(val))
            error += "Value must be a number\n";
        if(!validateName(name))
            error += "Name should be between 2-256 Characters\n";
        return validateSN(sn, list) && validateName(name) && validateValue(val);
    }
    private boolean validateSN(String sn, ObservableList<itemData> list){
        //Check if Serial Number is the correct length
        boolean snValid = (sn.length() == 10);
        //Check if Serial Number is alphanumeric
        for(char c : sn.toCharArray()){
            if(!Character.isLetterOrDigit(c))
                snValid = false;
        }
        //check if Serial Number is Unique (Check against the rest of the database)
        for(itemData data : list){
            if(list.get(list.indexOf(data)).getSerialNumber().compareTo(sn) == 0)
                snValid = false;
        }
        return snValid;
    }
    private boolean validateName(String name){
        //check if Name is within the correct range of lengths
        return (name.length() >= 2 && name.length() <= 256);
    }
    private boolean validateValue(String val){
        if(val == null)
            return false;
        try {
            Double.parseDouble(val);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public String getError(){
        return this.error;
    }
    public itemData appendItem(){
        return this.newItem;
    }
}
