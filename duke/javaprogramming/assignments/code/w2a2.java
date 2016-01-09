
/**
 * Write a description of w2a2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import edu.duke.*;

public class w2a2 {

    public StorageResource findProtein(String dna) {
        String lowerDNA = dna.toLowerCase();
        StorageResource store = new StorageResource();
        int start = 0;
        while(true){
            int loc = lowerDNA.indexOf("atg",start);
            if (loc==-1){
                break;}
            System.out.println("starts at "+loc);
            int stopLoc = findStopIndex(lowerDNA,loc);
            System.out.println("stop at "+stopLoc);
            String onePiece = dna.substring(loc+3,stopLoc);
            store.add(onePiece);
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
            if(dna.toLowerCase().charAt(i)=='c'){
                countC++;}
            if(dna.toLowerCase().charAt(i)=='g'){
                countG++;}
        }
        return countC*1.0/countG;
    }

    public void printGenes(StorageResource sr){
        int countLessThan60 = 0;
        int cgMoreThan035 = 0;
        for(String gene:sr.data()){
            if(gene.length()<60){
                System.out.println(gene.length()+"\t"+gene);
                countLessThan60++;
            }
        }

        for(String gene:sr.data()){

            if(cgRatio(gene)>0.35){
                System.out.println(cgRatio(gene)+"\t"+gene);
                cgMoreThan035++;}
        }
        System.out.println(countLessThan60);
        System.out.println(cgMoreThan035);
    }

    public void test(){
        String a = "cccatggggcatgcctttaacccataataattataggagagagagagagatgagttt";
        findProtein(a);
    }

    public void testFile(){
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String sr = fr.asString();
            printGenes(findProtein(sr));
  
        }
    }
}
