
/**
 * Write a description of TestOOCaesarCipher here.
 * 
 * @author Yafei Qu
 * @version 08/02/2016
 */

import edu.duke.*;
public class TestOOCaesarCipher {
    public void countLetters(String resource, int[] counts){
        int index = 0;
        String alphabet= "abcdefghijklmnopqrstuvwxyz";
        char[] lettersChar = new char[26];
        for(int i=0;i<26;i++){
            lettersChar[i]=alphabet.charAt(i);
        }
        StringBuilder line_b = new StringBuilder(resource.toLowerCase());
        for(int i=0;i<line_b.length();i++){
            if(Character.isLetter(line_b.charAt(i))){
                counts[alphabet.indexOf(line_b.charAt(i))]+=1;
            }
        }
    }

        public int getKey(String s){
        int[] counts = new int[26];
        int max = 0;
        countLetters(s,counts);
        max = maxIndex(counts);
        if(max-4<=0) return 26+max-4;
        else return max-4;
        //return 5-maxIndex(counts);
    }
    
    public int maxIndex(int[] arr){
        int max = 0;
        int index = 0;
        for(int i=0;i<arr.length;i++){
            if(max<arr[i]){ max=arr[i];index=i;}
        }
        return index;
    }

    public String breakCaesarCipher(String input){
        int key = getKey(input);
        OOCaesarCipher cc = new OOCaesarCipher(key);
        String de = cc.decrypt(input);
        return de;
    }
    
    public void simpleTests(){
        FileResource resource = new FileResource("data/smallHamlet.txt");
        OOCaesarCipher cc = new OOCaesarCipher(18);
        String en = cc.encrypt(resource.asString());
        String de = cc.decrypt(en);
        System.out.println(en);
        System.out.println(de);
        System.out.println(breakCaesarCipher(en));
    }

}
