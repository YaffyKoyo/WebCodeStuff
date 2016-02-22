
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, Integer> map;
    public GladLibMap(){
        map = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna){
        map.clear();
        int index = start;
        while(true){
            if (index>dna.length()-3){
                break;
            }
            String frame = dna.substring(index,index+=3);
            if(!map.containsKey(frame)){
                map.put(frame,1);
            }else{
                map.put(frame,map.get(frame)+1);
            }
        }
    }

    public String getMostCommonCodon(){
        int max = 0;
        String maxCondon = "";
        for(String w: map.keySet()){
            int curr = map.get(w);
            if(curr>max){
                max = curr;
                maxCondon = w;}
        }
        return maxCondon;
    }

    public void printCodonCounts(int start, int end){
        for(String w: map.keySet()){
            System.out.println(w+"\t count:"+map.get(w));
        }    
    }

    public void tester(){
        FileResource resource = new FileResource();
        String dna = resource.asString().trim();
        buildCodonMap(2,dna);
        printCodonCounts(1,5);
        System.out.println(getMostCommonCodon()+" has the most count:"+map.get(getMostCommonCodon()));
    }
}
