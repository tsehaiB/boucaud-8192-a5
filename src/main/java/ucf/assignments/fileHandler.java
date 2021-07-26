package ucf.assignments;

import com.jayway.jsonpath.JsonPath;
import javafx.collections.ObservableList;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class fileHandler {
    //define instance variables for filetype, file directory, and items being passed
    private int saveType;
    private File saveFile;
    private ObservableList<itemData> allItems;
    //Create default constructor
    fileHandler() {
        setSaveFile(new File("src/main/java/ucf/assignments"));
        setSaveType(0);
    }
    //create paramaterized constructor with filetype, file directory, name of file, and items being passed
    fileHandler(int saveType, File saveFile, String name, ObservableList<itemData> allItems) {
        //set instance values
        setSaveFile(saveFile);
        setSaveType(saveType);
        setAllItems(allItems);
        //switchcase based on which type of file (0 is TSV, 1 is HTML, 2 is JSON)
        switch(saveType){
            case(0):
            {
                //if TSV, call TSV file writing functions to write tsv text file to the directory
                try {
                    writeTSVToFile(parseAsTSV(), name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            case(1):{
                //if html, call html file writing functions to write .html file to the directory
                return;
            }
            case(2):{
                //if JSON, call JSON file writing functions to write .json file to the directory
                try {
                    writeJsonToFile(parseAsJson(), name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }

    }
        //create setters for instance variables
        public void setSaveType (int type){
            this.saveType = type;
        }
        public void setSaveFile (File file){
            this.saveFile = file;
        }
        public void setAllItems(ObservableList<itemData> allItems){
        this.allItems = allItems;
         }
    //Function to turn all of the items in the items list into a tsv output
    public ArrayList<String> parseAsTSV(){
        //create list of strings to hold tsv output
        ArrayList<String> tsv = new ArrayList<>();
        //add header
        tsv.add(String.format("%s\t%s\t%s", "Serial Number", "Value", "Name"));
        //loop through items and append formatted text
        for(itemData item : allItems){
            String formatted = String.format("%s\t%s\t%s", item.getSerialNumber(), item.getValue(), item.getName());
            tsv.add(formatted);
        }
        return tsv;
    }
    //function to write tsv output to file "filename.txt" in the appropriate directory
    public String writeTSVToFile(ArrayList<String> tsvItems,  String name) throws IOException {
        //create a filewriter with the correct directory + the filename + format '.txt'
        FileWriter writer = new FileWriter((saveFile + "\\" + name + ".txt"));
        //create temporary file to return location
        File ret = new File(saveFile + "\\" + name + ".txt");
        //add all of the strings in the list of tsv text to a single string seperated by new lines
        String completeOutput = "";
        for(String str : tsvItems){
            completeOutput += (str + "\n");
        }
        //write to file
        writer.write(completeOutput);
        //flush and close
        writer.flush();
        writer.close();
        return ret.getPath();
    }
    public JSONObject parseAsJson(){
        //create a JSON Object
            JSONObject jsonItems = new JSONObject();
            //loop through all of the items in the database
            for(itemData item : allItems){
                //create a JSON array of strings for each item
                JSONArray currentItem = new JSONArray();
                //add the serial number to the jsonarray
                currentItem.add(item.getSerialNumber());
                //add the value to the jsonarray
                currentItem.add(item.getValue());
                //add the name to the jsonarray
                currentItem.add(item.getName());
                //add the json array to the json object as a string with the row number
                jsonItems.put(String.valueOf(allItems.indexOf(item)+1), currentItem);
            }
            //return json object
            return jsonItems;
        }
    public String writeJsonToFile(JSONObject jsonItems, String name) throws IOException {
            //create a filewriter with the correct directory + the filename + format '.json'
            FileWriter writer = new FileWriter((saveFile + "\\" + name + ".json"));
            //create temporary file to return the location
            File ret = new File(saveFile + "\\" + name + ".json");
            //write the jsonItems to the .json file as a JSON string.
            writer.write(jsonItems.toJSONString());
            //flush to file
            writer.flush();
            //close
            writer.close();
            //return location
            return ret.getPath();
        }
    }
