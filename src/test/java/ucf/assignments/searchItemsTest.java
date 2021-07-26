/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Tsehai Boucaud
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class searchItemsTest {

    @Test
    void getResult_returns_true_given_empty_searchText() {
        //given
        searchItems mySearch = new searchItems(new itemData("0123456789", "1","abc"), "", 0);
        //when
        boolean actual = mySearch.getResult();
        //then
        assertTrue(actual);
    }
    @Test
    void getResult_returns_false_given_null_item() {
        //given
        searchItems mySearch = new searchItems(null, "abc", 0);
        //when
        boolean actual = mySearch.getResult();
        //then
        assertFalse(actual);
    }
    @Test
    void getResult_returns_true_given_complete_serial_number_in_list() {
        //given
        itemData testItem = new itemData("0123456789", "12.5", "test");
        searchItems mySearch = new searchItems(testItem, "0123456789", 0);
        //when
        boolean actual = mySearch.getResult();
        //then
        assertTrue(actual);
    }
    @Test
    void getResult_returns_true_given_partial_serial_number_in_list() {
        //given
        itemData testItem = new itemData("0123456789", "12.5", "test");
        searchItems mySearch = new searchItems(testItem, "345678", 0);
        //when
        boolean actual = mySearch.getResult();
        //then
        assertTrue(actual);
    }
    @Test
    void getResult_returns_true_given_complete_value_in_list() {
        //given
        itemData testItem = new itemData("0123456789", "12.5", "test");
        searchItems mySearch = new searchItems(testItem, "12.5", 1);
        //when
        boolean actual = mySearch.getResult();
        //then
        assertTrue(actual);
    }
    @Test
    void getResult_returns_true_given_partial_value_in_list() {
        //given
        itemData testItem = new itemData("0123456789", "12.5", "test");
        searchItems mySearch = new searchItems(testItem, ".", 1);
        //when
        boolean actual = mySearch.getResult();
        //then
        assertTrue(actual);
    }
    @Test
    void getResult_returns_true_given_complete_name_in_list() {
        //given
        itemData testItem = new itemData("0123456789", "12.5", "test");
        searchItems mySearch = new searchItems(testItem, "test", 2);
        //when
        boolean actual = mySearch.getResult();
        //then
        assertTrue(actual);
    }
    @Test
    void getResult_returns_true_given_partial_name_in_list() {
        //given
        itemData testItem = new itemData("0123456789", "12.5", "test");
        searchItems mySearch = new searchItems(testItem, "es", 2);
        //when
        boolean actual = mySearch.getResult();
        //then
        assertTrue(actual);
    }
    @Test
    void getResult_returns_false_given_serial_number_not_in_list() {
        //given
        itemData testItem = new itemData("0123456789", "12.5", "test");
        searchItems mySearch = new searchItems(testItem, "abc", 0);
        //when
        boolean actual = mySearch.getResult();
        //then
        assertFalse(actual);
    }
    @Test
    void getResult_returns_false_given_value_not_in_list() {
        //given
        itemData testItem = new itemData("0123456789", "12.5", "test");
        searchItems mySearch = new searchItems(testItem, "90", 1);
        //when
        boolean actual = mySearch.getResult();
        //then
        assertFalse(actual);
    }
    @Test
    void getResult_returns_false_given_name_not_in_list() {
        //given
        itemData testItem = new itemData("0123456789", "12.5", "test");
        searchItems mySearch = new searchItems(testItem, "abc", 0);
        //when
        boolean actual = mySearch.getResult();
        //then
        assertFalse(actual);
    }
}