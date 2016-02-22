
/**
 * Write a description of w1a2_CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {

    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet_u = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet_l = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet_u = alphabet_u.substring(key)+alphabet_u.substring(0,key);
        String shiftedAlphabet_l = alphabet_l.substring(key)+alphabet_l.substring(0,key);        
        for(int i=0;i<encrypted.length();i++){
            char currChar = encrypted.charAt(i);
            int idx_u = alphabet_u.indexOf(currChar);
            int idx_l = alphabet_l.indexOf(currChar);
            if(idx_u!=-1){
                char newChar = shiftedAlphabet_u.charAt(idx_u);
                encrypted.setCharAt(i,newChar);
            }
            if(idx_l!=-1){
                char newChar = shiftedAlphabet_l.charAt(idx_l);
                encrypted.setCharAt(i,newChar);
            }
        }
        return encrypted.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2){
        String odd = encrypt(input,key1);
        String even = encrypt(input,key2);
        StringBuilder odd_b = new StringBuilder(odd);
        for(int i=0;i<odd_b.length();i++){
            if(i%2==1){
                odd_b.setCharAt(i,even.charAt(i));
            }
        }
        return odd_b.toString();
    }

    public void testCaesar() {
        int key = 17;
        /* FileResource fr = new FileResource();
        String message = fr.asString();
         */String encrypted = encryptTwoKeys("First Legion",23, key);
        System.out.println(encrypted);
    }
}

