
/**
 * Write a description of HelloWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class w1as {
    public ImageResource makeInverse (ImageResource image) {
            ImageResource outImage = new ImageResource(image.getWidth(),image.getHeight());
            for(Pixel pixel: outImage.pixels()){
             Pixel inPixel = image.getPixel(pixel.getX(),pixel.getY());
             pixel.setRed(255-inPixel.getRed());
             pixel.setBlue(255-inPixel.getBlue());
             pixel.setGreen(255-inPixel.getGreen());
            }
            saveFile(image,outImage,"inverse_");
            return outImage;
    }
    
    public ImageResource makeGray (ImageResource image) {
            ImageResource outImage = new ImageResource(image.getWidth(),image.getHeight());
            for(Pixel pixel: outImage.pixels()){
             Pixel inPixel = image.getPixel(pixel.getX(),pixel.getY());
             int average = (inPixel.getRed()+inPixel.getGreen()+inPixel.getBlue())/3;
             pixel.setRed(average);
             pixel.setBlue(average);
             pixel.setGreen(average);
            }
            saveFile(image,outImage,"gray_");
            return outImage;
    }
    
    public void saveFile(ImageResource inImage, ImageResource outImage, String addName){
        String outName = addName+inImage.getFileName();
        outImage.setFileName(outName);
        outImage.save();
    }
    
    
    public void testMethod(){
        DirectoryResource res = new DirectoryResource();
        for (File file: res.selectedFiles()){
            ImageResource inImage = new ImageResource(file);
            ImageResource outImage = makeInverse(inImage);
            ImageResource gray_outImage = makeGray(inImage);
            outImage.draw();
            gray_outImage.draw();
        }
    }
}

