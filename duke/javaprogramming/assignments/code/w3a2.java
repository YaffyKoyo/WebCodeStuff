
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
    
	public String fileWithColdestTemperature(){
	   CSVRecord coldestSoFar = null;
	   String coldestFileName = null;
	   DirectoryResource dr = new DirectoryResource();
	   for(File f:dr.selectedFiles()){
	       FileResource fr = new FileResource(f);
	       CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
	       coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
	       if(fr.asString().contains(coldestSoFar.get("TemperatureF"))){
	           coldestFileName = f.getName();
	       }
	   }
	   return coldestFileName;
}

public void testfile(){
   System.out.println(fileWithColdestTemperature());
}
    
    public void testColdestHourInFile () {
		FileResource fr = new FileResource("C:/st/WebCode/duke/javaprogramming/assignments/WeekThreeAssignments/nc_weather/2015/weather-2015-01-01.csv");
		CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
		System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
				   " at " + coldest.get("TimeEST"));
	}
}
