package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class CsvReader {
    
    private String filename;

    public CsvReader () {}

    public CsvReader (String filename) {
        this.filename = filename;
    }

    public Vector<String> readCsv() {

        Vector<String> lines = new Vector<String>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String nextLine = reader.readLine();
            
            while ((nextLine = reader.readLine()) != null) {
                lines.add(nextLine);
            }

            reader.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        return lines;
    }

}
