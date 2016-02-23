
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

    public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for(LogEntry le:records){
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            }else{
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
        int max = 0;
        for(String w:counts.keySet()){
            int curr = counts.get(w);
            if(curr>max){
                max = curr;}
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
        int max = mostNumberVisitsByIP(counts);
        ArrayList<String> iPs = new ArrayList<String>();
        for(String w: counts.keySet()){
            int curr = counts.get(w);
            if(curr==max){
                iPs.add(w);
            }
        }
        return iPs;
    }

    public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> iPdays = new HashMap<String, ArrayList<String>>();
        for(LogEntry le:records){
            String date = le.getAccessTime().toString().substring(4,10);
            String ip = le.getIpAddress();
                ArrayList<String> iPs = new ArrayList<String>();
            if(!iPdays.containsKey(date)){

                iPs.add(ip);
                iPdays.put(date,iPs);
            }else{
                    iPs = iPdays.get(date);
                    iPs.add(ip);
                    iPdays.put(date,iPs );
            }
        }
        return iPdays;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPdays){
        int max = 0;
        String maxDay="";
        for(String w: iPdays.keySet()){
            int curr = iPdays.get(w).size();
            if(curr>max){
                maxDay = w;
                max = curr;
            }
        }
        System.out.println(iPdays.size());
        return maxDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPdays, String date){
        ArrayList<String> ips = uniqueIPVisitsOnDay(date);
        int[] counters = new int[ips.size()];
        ArrayList<String> ipsDay = iPdays.get(date);
        ArrayList<String> out = new ArrayList<String>();
        for(int i=0;i<counters.length;i++){
            counters[i]=1;
        }
        for(int i=0;i<ipsDay.size();i++){
            if(ips.contains(ipsDay.get(i))){
                int index = ips.indexOf(ipsDay.get(i));
                counters[index]++;
            }
        }
        
        int max = 0;
        
        for(int i=0;i<counters.length;i++){
            if(counters[i]>max) max = counters[i];
        }
        
        for(int i=0;i<counters.length;i++){
            if(counters[i]==max){
                out.add(ips.get(i));
            }
        }
        return out;
        
    }
    
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le.toString());
        }
    }

}
