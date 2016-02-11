
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }

    public void tester(){
        findUnique();
        System.out.println("# unique words: "+myWords.size());
        for(int i = 0; i < myWords.size(); i++) {   
            System.out.println(myWords.get(i)+"\t"+myFreqs.get(i));
        } 
        //int index = findMax();
        //System.out.println("max word/freq: "+myWords.get(index)+" "+myFreqs.get(index));
    }

}
