
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
            //System.out.println("starts at "+loc);
            int stopLoc = findStopIndex(lowerDNA,loc);
            //System.out.println("stop at "+stopLoc);
            if(stopLoc+3<dna.length())             
            {
               String onePiece = dna.substring(loc,stopLoc+3);
                    store.add(onePiece);
               start = stopLoc+3;            }else{start = loc+3;}
        }
        System.out.println("all genes' count: "+store.size());
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
        return (countC+countG)*1.0/dna.length();
    }

    public void printGenes(StorageResource sr){
        int countLessThan60 = 0;
        int cgMoreThan035 = 0;
        int count = 0;
        int contain = 0;
        int max = 0;
        for(String gene:sr.data()){
            if(max<gene.length()){max = gene.length();}
        }

        
        
        for(String gene:sr.data()){
            if(gene.length()>60){
                System.out.println(gene.length()+"\t"+gene);
                countLessThan60++;
            }
        }
        
         for(String gene:sr.data()){
            if(gene.contains("CTG")){
                System.out.println(gene.length()+"\t"+gene);
                contain++;
            }
        }

        for(String gene:sr.data()){

            if(cgRatio(gene)>0.35){
                //System.out.println(cgRatio(gene)+"\t"+gene);
                cgMoreThan035++;}
        }
        System.out.println("count less than 60: "+countLessThan60);
        System.out.println("cgRatio morethan: "+cgMoreThan035);
                System.out.println("CTGRatio morethan: "+contain);
                        System.out.println("longest: "+max);
    }

    public void test(){
        String a = "cccatggggcatgcctttaacccataataattataggagagagagagagatgagttt";
        printGenes(findProtein(a));
    }

    public void testFile(){
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String sr = fr.asString();
            System.out.println("string length: "+sr.length());
            printGenes(findProtein(sr));
        }
    }
}
