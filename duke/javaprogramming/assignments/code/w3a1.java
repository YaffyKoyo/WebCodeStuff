
/**
 * Programming Exercise: Parsing Export Data.
 * 
 * @author yafei 
 * @version 21/01/2016
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class w3a1{
    public void countryInfo(CSVParser parser, String country){
        boolean found = false;
        for(CSVRecord record : parser){
            if(record.get("Country").equals(country)){
                System.out.println(country+": "+record.get("Exports")+": "+record.get("Value (dollars)"));
                found = true;
            }
        }
        if(found==false){
            System.out.println("NOT FOUND");
        }
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        boolean found = false;
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem1)&&export.contains(exportItem2)){
                System.out.println(record.get("Country"));
                found = true;
            }
        }
        if(found==false){
            System.out.println("NOT FOUND");
        }
    }

    public void numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem)){
                count++;
            }
        }
        System.out.println(count);
    }

    public void bigExporters(CSVParser parser,String amount){
        String price_s = amount.replace("$","").replace(",","");
        long price_l = Long.parseLong(price_s);
        for(CSVRecord record : parser){
            String value_s = record.get("Value (dollars)").replace("$","").replace(",","");
            long value_l = Long.parseLong(value_s);
            if(value_l>=price_l){
                System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
            }
        }
    }

    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String country = "China";
        String item1 = "cocoa";
        String item2 = "flowers";
        String amount = "$999,999,999,999";
        // countryInfo(parser,country);
        //listExportersTwoProducts(parser, item1, item2);
        //numberOfExporters(parser,item1);
       bigExporters(parser,amount);
    }
}
