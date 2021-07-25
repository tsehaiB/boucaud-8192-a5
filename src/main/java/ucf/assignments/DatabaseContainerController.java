/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Tsehai Boucaud
 */
package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ResourceBundle;

public class DatabaseContainerController {
    //Initialize FXML variables for the Table, and the controllers
    @FXML private TableView<itemData> tableview;
    @FXML private TableColumn<itemData, SimpleStringProperty> valueColumn;
    @FXML private TableColumn<itemData, SimpleStringProperty> serialColumn;
    @FXML private TableColumn<itemData, SimpleStringProperty> nameColumn;
    @FXML private TextField valueNum;
    @FXML private TextField serialNum;
    @FXML private TextArea name;
    @FXML private TextArea searchText;
    @FXML private ToggleGroup searchOptions;
    @FXML private AnchorPane mainWindow;
    //Initialize objects and lists
    addItem itemAdder;
    searchItems itemSearcher;
    //ObservableList<itemData> unfilteredData = FXCollections.observableArrayList();
    ObservableList<itemData> myItems = FXCollections.observableArrayList();
    public DatabaseContainerController() {

    }

    @FXML
    public void initialize() {
        //set call value types for the serial number, name, and value columns
        serialColumn.setCellValueFactory(new PropertyValueFactory<itemData, SimpleStringProperty>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<itemData, SimpleStringProperty>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<itemData, SimpleStringProperty>("value"));
        tableview.setEditable(true);
        search();
        //unfilteredData.setAll(tableview.getItems());
    }
    public void addButtonClicked(ActionEvent actionEvent) {
        //construct item adder with current text fields as parameters, as well as the observable list for validating reasons
        itemAdder = new addItem(serialNum.getText(), valueNum.getText(), name.getText(), tableview.getItems());
        myItems.add(itemAdder.appendItem());
        tableview.getItems().setAll(myItems);
        //unfilteredData.setAll(tableview.getItems());
    }
    public void search(){
        //Check currently selected toggle
        //Create a filtered list (clones myList and filters based on conditions)
        FilteredList<itemData> searchResults = new FilteredList<>(myItems, b -> true);
        //add a listener to my search box text area
        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            //set predicate equal to the output of a function that contains all of the different conditions
            searchResults.setPredicate(itemData -> {
                int currentToggle = searchOptions.getToggles().indexOf(searchOptions.getSelectedToggle());
                itemSearcher = new searchItems(itemData, searchText.getText(), currentToggle);
                return itemSearcher.getResult();
            });
            tableview.setItems(searchResults);
        });
    }
}

