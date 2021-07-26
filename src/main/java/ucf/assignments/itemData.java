/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Tsehai Boucaud
 */
package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;

public class itemData {
    private SimpleStringProperty value, serialNumber, name;
    itemData(){
        setSerialNumber("");
        setName("");
        setValue("");
    }
    itemData(String serialNumber, String value, String name){
        setSerialNumber(serialNumber);
        setName(name);
        setValue(value);
    }

    public String getValue() {
        return value.get();
    }
    public String getSerialNumber() {
        return serialNumber.get();
    }
    public String getName() {
        return name.get();
    }
    public void setValue(String value) {
        this.value = new SimpleStringProperty(value);
    }
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = new SimpleStringProperty(serialNumber);
    }
}
