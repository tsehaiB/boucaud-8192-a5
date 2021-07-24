package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.util.List;

public class addItem {
    private itemData newItem;
    addItem(){
        //empty constructor
        this.newItem = new itemData();
    }
    addItem(String sn, String val, String name, ObservableList<itemData> list){
        //Validate item, and then add it or print an error
        if(validateItem(sn, val, name, list)){
            this.newItem = new itemData(sn, val, name);
            list.add(newItem);}
        else{
            System.out.println("Item Invalid. Name should be between 2-256 Characters\n" +
                                             "Serial Number should be 10 characters long and alphanumeric\n" +
                                             "Value should be a number that could represent currency");}
    }
    public boolean validateItem(String sn, String val, String name, ObservableList<itemData> list){
        //Check if Serial Number is the correct length
        boolean snValid = (sn.length() == 10);
        //Check if Serial Number is alphanumeric
        for(char c : sn.toCharArray()){
            if(!Character.isLetterOrDigit(c))
                snValid = false;
        }
        for(itemData data : list){
            if(list.get(list.indexOf(data)).getSerialNumber().compareTo(sn) == 0)
                snValid = false;
        }
        //check if Name is within the correct range of lengths
        boolean nameValid = (name.length() >= 2 && name.length() <= 256);
        //Check if the Value is numeric, and can be turned into a double
        boolean valValid = val != null;
        try {
            Double.parseDouble(val);
        } catch (NumberFormatException nfe) {
            valValid = false;
        }
        //return true if and only if all parameters have met constraints
        return snValid && valValid && nameValid;
    }
    //given the itemlist being used by the database, append the item to the end.
    public boolean appendItem(List<itemData> itemsList){
        //add item to itemslist
        return itemsList.add(newItem);
    }
    public itemData getNewItem(){
        return this.newItem;
    }
    public boolean hasItem(){
        return (this.newItem != null);
    }
}
