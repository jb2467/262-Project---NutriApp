package Backend.Adapter;

import java.util.HashMap;

public class NewDatatypeAdapter implements CSVHandler {
    private String inputFile;
    private String outputFile;
    private HashMap<String, CanParseCanPrint> databaseMap;
    private NewDatatypeHandler newDatatypeHandler;
    
    public NewDatatypeAdapter(String inputFile, String outputFile, HashMap<String, CanParseCanPrint> databaseMap, NewDatatypeHandler newDatatypeHandler) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.databaseMap = databaseMap;
        this.newDatatypeHandler = newDatatypeHandler;
    }

    @Override
    public String getInputFile() {
        return newDatatypeHandler.getInputFile();
    }
    @Override
    public void setNewInputFile() {
        newDatatypeHandler.setNewInputFile();
    }
    @Override
    public void parseCSV(String inputFile, HashMap<String, CanParseCanPrint> databaseMap) {
        newDatatypeHandler.parse(inputFile, databaseMap);
    }
    @Override
    public String getOutputFile() {
        return newDatatypeHandler.getOutputFile();
    }
    @Override
    public void setNewOutputFile() {
        newDatatypeHandler.setNewOutputFile();
    }
    @Override
    public void printCSV(String outputFile, HashMap<String, CanParseCanPrint> databaseMap) {
        newDatatypeHandler.print(outputFile, databaseMap);
    }
}
