/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sem;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author PSharp
 */
public class brightness_contrast {

    public static void main(String[] args) {
        brightness_contrast bright = new brightness_contrast();
        BufferedImage img = bright.loadImage("C:\\Users\\psharp\\Documents\\test_image.png");
        //BufferedImage img2 = bright.changeBrightness(img, 40f);
        BufferedImage img2 = bright.changeContrast(img, 1.1f);
        try {
            ImageIO.write(img2, "png", new File("C:\\Users\\psharp\\Documents\\test_image_2.png"));
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("file failed to save");
        }
    }
    
    private BufferedImage loadImage(String filename) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(filename));
            return image;
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("failed to load image.");
        }
        return null;
    }
    
    // Pre:  BufferedImage param must be greyscale, throws IllegalArgumentException otherwise
    // Post: returns image with all pixels brightness values incremented by float param
    public BufferedImage changeBrightness(BufferedImage image, float bright_amt) throws IllegalArgumentException {
        /*Raster raster = image.getRaster();
        if (raster.getNumDataElements() != 1) { // checks if image is greyscale (i.e. only has one color channel)
            throw new IllegalArgumentException();
        }*/
        if (image.getType() !=  BufferedImage.TYPE_BYTE_GRAY) { // checks if image is greyscale (i.e. only has one color channel)
            throw new IllegalArgumentException();
        }
        RescaleOp bright_ro = new RescaleOp(1f, bright_amt, null);
        return bright_ro.filter(image,null);
    }
    
    public BufferedImage changeContrast(BufferedImage image, float contrast_amt) {
        
        return null;
    }
    
    public BufferedImage autoAdjust(BufferedImage image) {
        Raster raster = image.getRaster();
        //byte[] bufferbyte = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
        /* int[] histogram = new int[65536]
        Raster raster = image.getRaster();
        final int w = image.getWidth();
        final int h = image.getHeight();
        double[] r = new double[w * h];
        r = raster.getSamples(0, 0, w, h, 0, r);
        */
        return null;
    }
}
