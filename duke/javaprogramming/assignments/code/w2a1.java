
/**
 * Write a description of w2a1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class w2a1 {

    public String findProtein(String dna) {
        String lowerDNA = dna.toLowerCase();
        int start = lowerDNA.indexOf("atg");

        int stop1 = lowerDNA.indexOf("tag", start+3);
        if ((stop1 - start) % 3 == 0) {
            return dna.substring(start, stop1+3);
        }
        int stop2 = lowerDNA.indexOf("tga", start+3);
        if ((stop2 - start) % 3 == 0) {
            return dna.substring(start, stop2+3);
        }
        int stop3 = lowerDNA.indexOf("taa", start+3);
        if ((stop3 - start) % 3 == 0) {
            return dna.substring(start, stop3+3);
        }
        else {
            return "";
        }   
    }

    public void printYouTubeURL( String link ){
        URLResource url = new URLResource(link);
        String url_s = url.asString();
        String url_s_l = url_s.toLowerCase();
        for(String word: url.words()){
            if (word.toLowerCase().contains("youtube.com")){
               int start = word.indexOf("\"");
               int end = word.lastIndexOf("\"");
                System.out.println(word.substring(start+1,end));
            System.out.println(word);
            }
        }
    }

    public void test(){
        printYouTubeURL("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
       // System.out.println(result);
    }
}
