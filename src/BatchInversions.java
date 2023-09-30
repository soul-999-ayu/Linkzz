/**
 * Inverts colors in a image file (photographic negative).
 * 
 * @author (Elvis Morales) 
 * @version (1.0)
 */

import edu.duke.*;

public class BatchInversions {
    
    public ImageResource makeInversion(ImageResource inImage) {
        
        ImageResource outImage = new ImageResource(inImage);
        
        for (Pixel pixel: outImage.pixels()) {
            
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            
            int invRed = 255 - inPixel.getRed();
            int invGreen = 255 - inPixel.getGreen();
            int invBlue = 255 - inPixel.getBlue();
            
            pixel.setRed(invRed);
            pixel.setGreen(invGreen);
            pixel.setBlue(invBlue);
        }
        
        return outImage;
        
    }
    
}