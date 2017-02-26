/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author frank
 */
public class ResizeImage {
    
     private static ResizeImage instance = null;
     private static final int IMG_WIDTH = 214;
     private static final int IMG_HEIGHT = 317;
    
     public static ResizeImage getInstance() throws IOException {
        if (instance == null) {
            instance = new ResizeImage();
        }
        return instance;
    }
    
    public void resizeIMG (String pathOrigPic, String resizedPath, String resizedName) throws IOException{
        BufferedImage originalImage = ImageIO.read(new File(pathOrigPic));
        int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        
        BufferedImage resizedImage = resize(originalImage, type);
        String thumbFile = resizedPath+"\\"+resizedName;
        ImageIO.write(resizedImage, "jpg", new File(thumbFile));
    }
     
     
    private BufferedImage resize(BufferedImage originalImage, int type){
        
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage,0, 0,IMG_WIDTH, IMG_HEIGHT,null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        return resizedImage;
    } 
     
}
