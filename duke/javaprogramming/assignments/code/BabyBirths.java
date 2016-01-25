
/**
 * The final miniproject
 * 
 * @author Yafei Qu 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                    " Gender " + rec.get(1) +
                    " Num Born " + rec.get(2));
            }
        }
    }

    public void totalNames (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += 1;
            if (rec.get(1).equals("M")) {
                totalBoys += 1;
            }
            else {
                totalGirls += 1;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public int getRank(int year, String name, String gender){
        int rank=0;
        FileResource fr = new FileResource("data/yob"+Integer.toString(year)+".csv");
        for(CSVRecord rec: fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                rank++;
            }
            if(rec.get(1).equals(gender)&&rec.get(0).equals(name)){
                return rank;
            }
        }
        return -1;
    }

    public String getName(int year, int rank, String gender){
        String name = "NO NAME";
        int currentRank = 0;
        FileResource fr = new FileResource("data/yob"+Integer.toString(year)+".csv");        
        for(CSVRecord rec: fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                currentRank++;
            }
            if(rank==currentRank&&rec.get(1).equals(gender)){
                name = rec.get(0);
            }
        }
        return name;
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        String newName = getName(newYear,getRank(year,name,gender),gender);
        System.out.println(name+" born in "+year+" would be "+newName+" if she/he was born in "+newYear);
    }

    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int rank = 300000;
        int yearRank = 0;
        for(File f:dr.selectedFiles()){
            int year = Integer.parseInt(f.getName().substring(3,7));
            int currentRank = getRank(year,name,gender);
            if(rank > currentRank ){
                rank = currentRank;
                yearRank = year;
            }
        }
        if(rank==300000){
            return -1;
        }
        return yearRank;
    }

    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int sum = 0;
        int fileAmount = 0;
        for(File f:dr.selectedFiles()){
            int year = Integer.parseInt(f.getName().substring(3,7));
            int currentRank = getRank(year,name,gender);
            sum+=currentRank;
            fileAmount++;
        }
        if(sum==0){
            return -1;
        }else{return sum*1.0/fileAmount;}
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int sum = 0;
        FileResource fr = new FileResource("data/yob"+Integer.toString(year)+".csv");
        int threshold = 0;
        for(CSVRecord rec: fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)&&rec.get(0).equals(name)){
                threshold=Integer.parseInt(rec.get(2));
                break;
            }
        }
        for(CSVRecord rec: fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)&&Integer.parseInt(rec.get(2))>threshold){
                sum+=Integer.parseInt(rec.get(2));
            }
        }
        return sum;
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob1905.csv");
        totalNames(fr);
        
        System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));

    }
}

