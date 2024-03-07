package Backend.Adapter;

import java.util.HashMap;

public interface CSVHandler {
    //String outputFile
    //String inputFile;
    //HashMap<String, CanParseCanPrint> databaseMap;

    // CSVHandler(String inputFile, String outputFile, HashMap<String, CanParseCanPrint> databaseMap);
    String getInputFile();
    void setNewInputFile();
    void parseCSV(String inputFile, HashMap<String, CanParseCanPrint> databaseMap); 
    String getOutputFile();
    void setNewOutputFile();
    void printCSV(String outputFile, HashMap<String, CanParseCanPrint> databaseMap);
}
