
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> myName;
    private ArrayList<Integer> myCounts;

    public CharactersInPlay(){
        myName = new ArrayList<String>();
        myCounts = new ArrayList<Integer>();
    }

    public void update(String person){
        person = person.toUpperCase();
        int index = myName.indexOf(person);
        if(index==-1){
            myName.add(person);
            myCounts.add(1);
        }else{
            myCounts.set(index,myCounts.get(index)+1);
        }

    }

    public void findAllCharacters(){
        myName.clear();
        myCounts.clear();
        FileResource file = new FileResource();
        for(String l:file.lines()){
            int index = l.indexOf('.');
            if(index!=-1){
                update(l.substring(0,index));
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i < myName.size(); i++) {
            int count = myCounts.get(i);
            if(count>=num1&&count<=num2){
                System.out.println(count+"\t"+myName.get(i));
            }
        }
    }

    public void tester(){
        findAllCharacters();
        charactersWithNumParts(5,40);
    }

}
