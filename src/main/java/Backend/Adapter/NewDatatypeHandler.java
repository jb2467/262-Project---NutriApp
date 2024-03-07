package Backend.Adapter;

import java.util.HashMap;

public interface NewDatatypeHandler {
    //String outputFile
    //String inputFile;
    //HashMap<String, CanParseCanPrint> databaseMap;

    // NewDatatypeHandler(String inputFile, String outputFile, HashMap<String, CanParseCanPrint> databaseMap);
    String getInputFile();
    void setNewInputFile();
    void parse(String inputFile, HashMap<String, CanParseCanPrint> databaseMap); 
    String getOutputFile();
    void setNewOutputFile();
    void print(String outputFile, HashMap<String, CanParseCanPrint> databaseMap);
}
