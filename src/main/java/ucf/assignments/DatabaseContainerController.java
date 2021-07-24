/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Tsehai Boucaud
 */
package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML private AnchorPane mainWindow;
    //Initialize objects and lists
    addItem itemAdder;
    public DatabaseContainerController() {

    }

    @FXML
    public void initialize() {
        //set call value types for the serial number, name, and value columns
        serialColumn.setCellValueFactory(new PropertyValueFactory<itemData, SimpleStringProperty>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<itemData, SimpleStringProperty>("value"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<itemData, SimpleStringProperty>("name"));
    }
    public void addButtonClicked(ActionEvent actionEvent) {
        //construct item adder with current text fields as parameters, as well as the observable list for validating reasons
        itemAdder = new addItem(serialNum.getText(), valueNum.getText(), name.getText(), tableview.getItems());

    }


}

