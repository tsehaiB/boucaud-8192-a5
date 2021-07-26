/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Tsehai Boucaud
 */
package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

public class DatabaseContainerController {
    //Initialize FXML variables for the Table, and the controllers
    @FXML
    private TableView<itemData> tableview;
    @FXML
    private TableColumn<itemData, SimpleStringProperty> valueColumn;
    @FXML
    private TableColumn<itemData, SimpleStringProperty> serialColumn;
    @FXML
    private TableColumn<itemData, SimpleStringProperty> nameColumn;
    @FXML
    private TextField valueNum;
    @FXML
    private TextField serialNum;
    @FXML
    private TextArea name;
    @FXML
    private TextArea searchText;
    @FXML
    private ToggleGroup searchOptions;
    @FXML
    private TextField directoryTextField;
    @FXML
    private ToggleGroup saveOptions;
    @FXML
    private CheckBox caseSensitivityCheckBox;
    @FXML
    private AnchorPane mainWindow;
    @FXML
    private TextField fileNameTextField;
    @FXML
    private Label errorDisplay;
    //Initialize objects and lists
    addItem itemAdder;
    searchItems itemSearcher;
    fileHandler handler;
    ObservableList<itemData> myItems = FXCollections.observableArrayList();

    public DatabaseContainerController() {

    }

    @FXML
    public void initialize() {
        //set call value types for the serial number, name, and value columns
        serialColumn.setCellValueFactory(new PropertyValueFactory<itemData, SimpleStringProperty>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<itemData, SimpleStringProperty>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<itemData, SimpleStringProperty>("value"));
        //unfilteredData.setAll(tableview.getItems());
    }

    public void addButtonClicked(ActionEvent actionEvent) {
        //construct item adder with current text fields as parameters, as well as the observable list for validating reasons
        itemAdder = new addItem(serialNum.getText(), valueNum.getText(), name.getText(), tableview.getItems());
        if (itemAdder.appendItem() != null) {
            myItems.add(itemAdder.appendItem());
            tableview.getItems().setAll(myItems);
        }//unfilteredData.setAll(tableview.getItems());
        else {
            errorDisplay.setText(itemAdder.getError());
        }
    }

    public void search() {
        //Check currently selected toggle
        //Create a filtered list (clones myList and filters based on conditions)
        FilteredList<itemData> searchResults = new FilteredList<>(myItems, b -> true);
        //add a listener to my search box text area
        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            //set predicate equal to the output of a function that contains all of the different conditions
            searchResults.setPredicate(itemData -> {
                //check to see if the user wants to search in Serial Numbers, Values, or Names
                int currentToggle = searchOptions.getToggles().indexOf(searchOptions.getSelectedToggle());
                //if case sensitive checkbox is selected, run case sensitive item searcher
                if (caseSensitivityCheckBox.isSelected())
                    itemSearcher = new searchItems(itemData, searchText.getText(), currentToggle, true);
                else
                    //if not run case insensitive searcher
                    itemSearcher = new searchItems(itemData, searchText.getText(), currentToggle);
                //either way, return the result to the searchResults list, and continue the loop
                return itemSearcher.getResult();
            });
            //make it so that this new filtered list is still sortable
            SortedList<itemData> sortedList = new SortedList<>(searchResults);
            sortedList.comparatorProperty().bind(tableview.comparatorProperty());
            //set table view items to equal sorted list items
            tableview.setItems(sortedList);
        });
    }

    public void browseButtonClicked(ActionEvent actionEvent) throws InterruptedException {
        if (!fileNameTextField.getText().isEmpty()) {
            final DirectoryChooser directory = new DirectoryChooser();

            Stage primaryWindow = (Stage) mainWindow.getScene().getWindow();
            File saveFile = directory.showDialog(primaryWindow);
            handler = new fileHandler(saveOptions.getToggles().indexOf(saveOptions.getSelectedToggle()), saveFile, fileNameTextField.getText(), myItems);
            directoryTextField.setText(saveFile.toString());
        } else {
            errorDisplay.setText("You must enter a file name before choosing a directory");
        }

    }
    
    public void onRemoveClicked(ActionEvent actionEvent) {
        itemData removeItem = tableview.getSelectionModel().getSelectedItem();
        myItems.remove(removeItem);
        tableview.getItems().setAll(myItems);
    }


}
