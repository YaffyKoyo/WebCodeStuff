
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
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

    public int maxIndex(int[] arr){
        int max = 0;
        int index = 0;
        for(int i=0;i<arr.length;i++){
            if(max<arr[i]){ max=arr[i];index=i;}
        }
        return index;
    }

    public String halfOfString(String message, int start){
        StringBuilder message_b = new StringBuilder("");
        for(int i=0;i<message.length();i++){
            if(start==0&&i%2==0){
                message_b.append(message.charAt(i));
            }else if(start==1&&i%2==1){
                message_b.append(message.charAt(i));
            }
        }
        return message_b.toString();
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


    public String decrypt(String encrypted, int key){
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26-key);
        return message;
    }

    public String decryptTwoKeys(String encrypted){
        String firstHalf = halfOfString(encrypted,0);
        String secondHalf = halfOfString(encrypted,1);
        int firstKey = getKey(firstHalf);
        int secondKey = getKey(secondHalf);
        System.out.println("First key: "+firstKey+"; Second Key: "+secondKey);
        StringBuilder total = new StringBuilder("");
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encryptTwoKeys(encrypted,26-firstKey,26-secondKey);
        return message;
    }
    
    public void test(){
        System.out.println(decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbgbgbgbgbgbgu"));
    }
    
    public void testMain(){
        int key = 23;
        FileResource resource = new FileResource("data/wordsLotsOfEsEncrypted.txt");
        System.out.println(decrypt(resource.asString(),key));
    }

}
