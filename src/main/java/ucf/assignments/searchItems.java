package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class searchItems {
    private boolean searchResult;
    searchItems(){

    }

    searchItems(itemData currentItem, String searchEntry, int searchOptions){
        //if searchEntry is empty or null, return with the original list
        if(currentItem == null)
            this.searchResult = false;
        else if(searchEntry == null || searchEntry.toString().equals(""))
            this.searchResult = true;
        else{
            this.searchResult = false;
            setResult(searchOptions, currentItem, searchEntry);
        }
        //set search items list to be a clone of the current list of items
        //switch case based on which item in the toggle group is currently toggled
        //if serial number, return false if the current item serial number does not contain the searched serial number
        //if value, return false if the current item value does not contain the searched value
        //if name, return false if the current item name does not contain the searched name
    }
    //second constructor for caseSensitive Searches
    searchItems(itemData currentItem, String searchEntry, int searchOptions, boolean caseSensitive){
        if(currentItem == null)
            this.searchResult = false;
        else if(searchEntry == null || searchEntry.toString().equals(""))
            this.searchResult = true;
        else{
            this.searchResult = false;
            setResult(searchOptions, currentItem, searchEntry, caseSensitive);
        }
    }
    private void setResult(int searchOptions, itemData currentitem, String searchEntry) {
            switch (searchOptions) {
                case (0) -> {
                    //check for serial numbers that contain this serial number
                    if(currentitem.getSerialNumber().toLowerCase().contains(searchEntry.toLowerCase())) {
                        searchResult = true;
                    }
                }
                case (1) -> {
                    //check for values that contain this name
                    if(currentitem.getValue().toLowerCase().contains(searchEntry.toLowerCase())) {
                        searchResult = true;
                    }
                }
                case (2) -> {
                    //check for names that contain this serial number
                    if(currentitem.getName().toLowerCase().contains(searchEntry.toLowerCase())) {
                        searchResult = true;
                    }
                }
            }
    }
    private void setResult(int searchOptions, itemData currentitem, String searchEntry, boolean caseSensitive) {
        switch (searchOptions) {
            case (0) -> {
                //check for serial numbers that contain this serial number
                if(currentitem.getSerialNumber().contains(searchEntry)) {
                    searchResult = true;
                }
            }
            case (1) -> {
                //check for values that contain this name
                if(currentitem.getValue().contains(searchEntry)) {
                    searchResult = true;
                }
            }
            case (2) -> {
                //check for names that contain this serial number
                if(currentitem.getName().contains(searchEntry)) {
                    searchResult = true;
                }
            }
        }
    }
    public boolean getResult(){
        return this.searchResult;
    }
}
