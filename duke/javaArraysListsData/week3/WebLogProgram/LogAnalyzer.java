
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;
    private ArrayList<String> uIPs;
    //private ArrayList<Integer> status;

    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<LogEntry>();
        uIPs = new ArrayList<String>();
    }

    public void readFile(String filename) {
        // complete method
        FileResource fr = new FileResource(filename);
        for(String line: fr.lines()){
            LogEntry curr = WebLogParser.parseEntry(line);
            records.add(curr);
        }
    }

    public int countUniqueIPs(){
        for(LogEntry le:records){
            String ipAddr = le.getIpAddress();
            if(!uIPs.contains(ipAddr)){
                uIPs.add(ipAddr);
            }
        }
        return uIPs.size();
    }

    public void printAllHigherThanNum(int num){
        for(LogEntry le:records){
            int ls = le.getStatusCode();
            if(ls>num){
                System.out.println(le.toString());
            }
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){        
        ArrayList<String> dateIPs = new ArrayList<String>();
        for(LogEntry le:records){
            String ld = le.getAccessTime().toString();
            if(ld.contains(someday)){
                if(!dateIPs.contains(le.getIpAddress())){
                    dateIPs.add(le.getIpAddress());
                }
            }
        }
        System.out.println(dateIPs.size());
        return dateIPs;
    } 
    
    public int countUniqueIPsInRange(int low, int high){
                ArrayList<String> statusIPs = new ArrayList<String>();
        for(LogEntry le:records){
            int ls = le.getStatusCode();
            if(ls>=low&&ls<=high){
if(!statusIPs.contains(le.getIpAddress())){
                    statusIPs.add(le.getIpAddress());
                }
            }
        }
        return statusIPs.size();
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le.toString());
        }
    }

}
