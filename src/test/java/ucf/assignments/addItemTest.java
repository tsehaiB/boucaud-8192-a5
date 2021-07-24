package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class addItemTest {
    private ObservableList<itemData> items = FXCollections.observableArrayList();
    @Test
    void validateItem_returns_true_given_valid_parameters_with_numberSN() {
        //given
        addItem item = new addItem();
        //when
        boolean actual = item.validateItem("1234567890", "10.2", "abc", items);
        //then
        assertTrue(actual);
    }
    @Test
    void validateItem_returns_true_given_valid_parameters_with_letterSN() {
        //given
        addItem item = new addItem();
        //when
        boolean actual = item.validateItem("abcdefghij", "10.2", "abc", items);
        //then
        assertTrue(actual);
    }
    @Test
    void validateItem_returns_false_given_invalid_sn() {
        //given
        addItem item = new addItem();
        //when
        boolean actual = item.validateItem("123$7890", "10.2", "abc", items);
        //then
        assertFalse(actual);
    }
    @Test
    void validateItem_returns_false_given_invalid_value() {
        //given
        addItem item = new addItem();
        //when
        boolean actual = item.validateItem("1234567890", "abc", "abc", items);
        //then
        assertFalse(actual);
    }
    @Test
    void validateItem_returns_false_given_empty() {
        //given
        addItem item = new addItem();
        //when
        boolean actual = item.validateItem("", "", "", items);
        //then
        assertFalse(actual);
    }
    @Test
    void validateItem_returns_false_given_invalid_name() {
        //given
        addItem item = new addItem();
        //when
        boolean actual = item.validateItem("1234567890", "10.2", "a", items);
        //then
        assertFalse(actual);
    }
}