
/**
 * Write a description of w1a1_WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class w1a1_WordPlay {
    public boolean isVowel(char ch){
        if("aeiouAEIOU".indexOf(ch)<0){
            return false;
        }else{return true;}
    }
    
    public String replaceVowels(String phrase, char ch){
        for(int i=0;i<phrase.length();i++){
            if(isVowel(phrase.charAt(i))){
phrase = phrase.replace(phrase.charAt(i),ch);
            }
        }
        return phrase;
    }
    
    public String emphasize(String phrase, char ch){
        ch=Character.toLowerCase(ch);
        StringBuilder phrase_b = new StringBuilder(phrase);
        for(int i=0;i<phrase_b.length();i++){
            if(Character.toLowerCase(phrase_b.charAt(i))==ch){
                if(i%2==0){
                phrase_b.setCharAt(i,'*');
            }else{phrase_b.setCharAt(i,'+');}
            }
        }
        return phrase_b.toString();
    }
    
    public void testWordPlay(){
        System.out.println(isVowel('e'));
        System.out.println(replaceVowels("hello, computer!!",'#'));
        System.out.println(emphasize("Mary Bella Abracadabra",'a'));
    }
}
