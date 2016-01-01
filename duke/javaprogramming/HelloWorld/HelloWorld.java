
/**
 * Write a description of HelloWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class HelloWorld {
	public void runHello () {
		URLResource res = new URLResource("http://www.dukelearntoprogram.com/java/somefile.txt");
		for (String line : res.words()) {
			
		    System.out.println(line);
		}
	}
}

