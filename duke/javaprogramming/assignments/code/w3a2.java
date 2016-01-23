
/**
 * Write a description of w3a2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class w3a2 {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord currentRow:parser){
            coldestSoFar = getColdestOfTwo(currentRow,coldestSoFar);
        }
        return coldestSoFar;
    }

    public CSVRecord getColdestOfTwo (CSVRecord currentRow, CSVRecord coldestSoFar) {
        //If coldestSoFar is nothing
        if (coldestSoFar == null) {
            coldestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > coldestSoFar’s
            if (currentTemp < coldestTemp&&currentTemp>-100.0) {
                //If so update coldestSoFar to currentRow
                coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
    }

    public CSVRecord getLowestOfTwo (CSVRecord currentRow, CSVRecord lowestSoFar) {
        //If coldestSoFar is nothing
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        }
        //Otherwise
        else {
            if(currentRow.get("Humidity").equals("N/A")){
                return lowestSoFar;
            }
            double currentHum = Double.parseDouble(currentRow.get("Humidity"));
            double lowestHum = Double.parseDouble(lowestSoFar.get("Humidity"));
            //Check if currentRow’s temperature > coldestSoFar’s
            if (currentHum < lowestHum&&currentHum>-100.0) {
                //If so update coldestSoFar to currentRow
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        for(CSVRecord currentRow:parser){
            lowestSoFar = getLowestOfTwo(currentRow,lowestSoFar);
        }
        return lowestSoFar;
    }

    public String fileWithColdestTemperature(){
        CSVRecord coldestSoFar = null;
        File coldestFile = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
            if(currentRow.get("TemperatureF").equals(coldestSoFar.get("TemperatureF"))){
                coldestFile = f;
            }
        }
        FileResource cfr = new FileResource(coldestFile);
        for(CSVRecord record: cfr.getCSVParser()){
           // System.out.println(record.get("DateUTC")+": "+record.get("TemperatureF"));
        }
        System.out.println(coldestSoFar.get("TemperatureF")+" "+coldestSoFar.get("DateUTC"));
        return coldestFile.getName();
    }

    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0;
        int count = 0;
        for(CSVRecord record:parser){
            sum+=Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
        return sum/count;
    }
    
    public double averageTemperatureWithHumidityInFile(CSVParser parser){
        double sum = 0;
        int count = 0;
        for(CSVRecord record:parser){
            if(Double.parseDouble(record.get("Humidity"))>80){
            sum+=Double.parseDouble(record.get("TemperatureF"));
            count++;}
        }
        return sum/count;
    }
    
    public void testAverageTemperatureInFile(){
     FileResource fr = new FileResource("C:/st/WebCode/duke/javaprogramming/assignments/WeekThreeAssignments/nc_weather/2013/weather-2013-09-02.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Average in file: "+averageTemperatureInFile(fr.getCSVParser()));
        System.out.println("Average Temp with high Hum in file: "+averageTemperatureWithHumidityInFile(fr.getCSVParser()));
    }

    public void testTempfile(){
        System.out.println(fileWithColdestTemperature());
    }

    public void testHumfile(){
        CSVRecord test = lowestHumidityInManyFiles();
        System.out.println(test.get("Humidity")+" at "+test.get("DateUTC"));
    }

    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource("C:/st/WebCode/duke/javaprogramming/assignments/WeekThreeAssignments/nc_weather/2014/weather-2014-07-22.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }

    public void testColdestHourInFile () {
        FileResource fr = new FileResource("C:/st/WebCode/duke/javaprogramming/assignments/WeekThreeAssignments/nc_weather/2015/weather-2015-01-01.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
            " at " + coldest.get("TimeEST"));
    }
}
