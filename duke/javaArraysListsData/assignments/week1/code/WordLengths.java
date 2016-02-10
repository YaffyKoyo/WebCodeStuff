
/**
 * week1a21
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        int index = 0;
        for(String word:resource.words()){
            StringBuilder pureWord = new StringBuilder(word);
            if(!Character.isLetter(pureWord.charAt(0))){
                pureWord.deleteCharAt(0);
            }
            if(!Character.isLetter(pureWord.charAt(pureWord.length()-1))){
                pureWord.deleteCharAt(pureWord.length()-1);
            }
            System.out.println(pureWord.toString());
            counts[pureWord.length()]+=1;
            index++;
        }
        for(int i=0;i<counts.length;i++){
                    System.out.println(i+" "+counts[i]);}
    }
    
    public int indexOfMax(int[] values){
        int max=0;
        for(int i=0;i<values.length;i++){
         if(max<values[i]){
            max = values[i];
            }
        }
        return max;
    }
    
    public void testCountWordLength(){
        FileResource resource = new FileResource("data/smallHamlet.txt");
        int[] count = new int[31];
        countWordLengths(resource,count);
        System.out.println(indexOfMax(count));
    }
}
