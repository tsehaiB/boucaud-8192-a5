/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Tsehai Boucaud
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class fileHandlerTest {

    @Test
    void parseAsTSV_Returns_formatted_ArrayList() {
        //given
        ObservableList<itemData> myItems = FXCollections.observableArrayList();
        myItems.add(new itemData("0123456789", "123", "abc"));
        myItems.add(new itemData("0000000000", "456", "xyz"));
        myItems.add(new itemData("9999999999", "20.5", "Alphabet123 225"));
        File myFile = new File("C:\\Users\\Art3mis\\IdeaProjects\\boucaud-8192-a5\\src\\test\\resources");
        fileHandler handler = new fileHandler(0, myFile, "testFile", myItems);
        //when
        ArrayList<String> expected = new ArrayList<String>();
        expected.add(String.format("%s\t%s\t%s", "Serial Number", "Value", "Name"));
        expected.add(String.format("%s\t%s\t%s", "0123456789", "123", "abc"));
        expected.add(String.format("%s\t%s\t%s", "0000000000", "456", "xyz"));
        expected.add(String.format("%s\t%s\t%s", "9999999999", "20.5", "Alphabet123 225"));
        ArrayList<String> actual = handler.parseAsTSV();
        //then
        assertEquals(expected.toString(), actual.toString());
    }
    @Test
    void parseAsJSON_Returns_formatted_JSONObject() {
        //given
        ObservableList<itemData> myItems = FXCollections.observableArrayList();
        myItems.add(new itemData("0123456789", "123", "abc"));
        myItems.add(new itemData("0000000000", "456", "xyz"));
        myItems.add(new itemData("9999999999", "20.5", "Alphabet123 225"));
        File myFile = new File("C:\\Users\\Art3mis\\IdeaProjects\\boucaud-8192-a5\\src\\test\\resources");
        fileHandler handler = new fileHandler(2, myFile, "testFile", myItems);
        //when
        String expected = "{\"1\":[\"0123456789\",\"123\",\"abc\"],\"2\":[\"0000000000\",\"456\",\"xyz\"],\"3\":[\"9999999999\",\"20.5\",\"Alphabet123 225\"]}";
        JSONObject actual = handler.parseAsJson();
        //then
        assertEquals(expected, actual.toJSONString());
    }
    @Test
    void writeTSVToFile_returns_correct_path_and_creates_text_file() throws IOException {
        //given
        ObservableList<itemData> myItems = FXCollections.observableArrayList();
        myItems.add(new itemData("0123456789", "123", "abc"));
        myItems.add(new itemData("0000000000", "456", "xyz"));
        myItems.add(new itemData("9999999999", "20.5", "Alphabet123 225"));
        File myFile = new File("src\\test\\resources");
        fileHandler handler = new fileHandler(0, myFile, "testFile", myItems);
        //when
        String expected = "src\\test\\resources\\testFile.txt";
        String actual = handler.writeTSVToFile(handler.parseAsTSV(), "testFile");
        //then
        assertEquals(expected, actual);
    }


    @Test
    void writeJsonToFile() throws IOException {
        //given
        ObservableList<itemData> myItems = FXCollections.observableArrayList();
        myItems.add(new itemData("0123456789", "123", "abc"));
        myItems.add(new itemData("0000000000", "456", "xyz"));
        myItems.add(new itemData("9999999999", "20.5", "Alphabet123 225"));
        File myFile = new File("src\\test\\resources");
        fileHandler handler = new fileHandler(2, myFile, "testFile", myItems);
        //when
        String expected = "src\\test\\resources\\testFile.json";
        String actual = handler.writeJsonToFile(handler.parseAsJson(), "testFile");
        //then
        assertEquals(expected, actual);
    }
}