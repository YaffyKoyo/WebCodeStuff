
/**
 * The program for the assignment of week 2.....Strings in Java
 * 
 * @author Yafei Qu
 * @version 09/01/2016
 */

import java.io.*;
import edu.duke.*;

public class w2a3 {

    public StorageResource findProtein(String dna) {
        String lowerDNA = dna.toLowerCase();
        StorageResource store = new StorageResource();
        int start = 0;
        while(true){
            int loc = lowerDNA.indexOf("atg",start);
            if (loc==-1){
                break;}
            //System.out.println("starts at "+loc);
            int stopLoc = findStopIndex(lowerDNA,loc);
            //System.out.println("stop at "+stopLoc);
            if(stopLoc==dna.length()){
                String onePiece = dna.substring(loc,stopLoc);            
                store.add(onePiece);           
            }else{
                String onePiece = dna.substring(loc,stopLoc+3);
                store.add(onePiece);
            }
            start = loc+3;
        }
        return store;
    }

    public int findStopIndex(String dna, int startLoc){
        int stop1 = dna.indexOf("tag", startLoc+3);
        if (stop1==-1||(stop1 - startLoc) % 3 != 0) {
            stop1 = dna.length();
        }
        int stop2 = dna.indexOf("taa", startLoc+3);
        if (stop2==-1||(stop2 - startLoc) % 3 != 0) {
            stop2 = dna.length();
        }
        int stop3 = dna.indexOf("tga", startLoc+3);
        if (stop3==-1||(stop3 - startLoc) % 3 != 0) {
            stop3 = dna.length();
        }
        return Math.min(stop1,Math.min(stop2,stop3));
    }

    public double cgRatio(String dna){
        int countC = 0;
        int countG = 0;
        for(int i=0;i<dna.length();i++){
            if(dna.charAt(i)=='c'){
                countC++;}
            if(dna.charAt(i)=='g'){
                countG++;}
        }
        return (countC+countG)*1.0/dna.length();
    }

    public void postGenes(StorageResource sr){
        int countContainsCTG = 0;
        int max = 0;
        for(String gene:sr.data()){
            int count = (gene.length()-gene.replace("CTG","").length())/3;
            countContainsCTG+=count;
            System.out.println(gene);
        }

        for(String gene:sr.data()){      
            if(gene.length()>max){
                max = gene.length();
            }
        }
        System.out.println(max);
        System.out.println("ctg appear amount:  "+ countContainsCTG);
    }

    public StorageResource findURLs(String url) {
        URLResource page = new URLResource(url);
        String source = page.asString();
        StorageResource store = new StorageResource();
        int start = 0;
        while (true) {
            int index = source.indexOf("href=", start);
            if (index == -1) {
                break;
            }
            int firstQuote = index+6; // after href="
            int endQuote = source.indexOf("\"", firstQuote);
            String sub = source.substring(firstQuote, endQuote);
            if (sub.startsWith("https")) {
                store.add(sub);
            }
            start = endQuote + 1;
        }
        return store;
    }

    public void test(){
        String a = "cccatggggccctttaacccataataattataggagagagagagagatgagttt";
        findProtein(a);
    }

    public void testAssignment1(){
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String sr = fr.asString();
            postGenes(findProtein(sr));
        }
    }

    public void testURLWithStorage(){
        StorageResource srURL = findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        int Fcount = 0;
        int Bcount = 0;
        for(String url_s: srURL.data()){
            if(url_s.contains(".com")){
                if(url_s.indexOf(".com")>=url_s.length()-6){
                    System.out.println(url_s);
                    Bcount++;
                }
                Fcount++;       
            }

        }
        int countDot=0;
        for(String url_s: srURL.data()){
            if(url_s.contains("http")){
                int count = (url_s.length()-url_s.replace(".","").length());
                System.out.println(count+"\t"+url_s);
                countDot+=count;
            }
        }
        System.out.println(srURL.size());
        System.out.println(".com amount: "+Fcount+"\t"+".com/com amount: "+Bcount+"\t"+"dotCount: "+"\t"+countDot);;
    }
}
