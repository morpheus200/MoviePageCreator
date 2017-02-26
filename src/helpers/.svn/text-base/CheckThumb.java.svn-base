/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.io.IOException;

/**
 *
 * @author frank
 */
public class CheckThumb {
    
     private static CheckThumb instance = null;

    public static CheckThumb getInstance() throws IOException {
        if (instance == null) {
            instance = new CheckThumb();
        }
        return instance;
    }
    
    public boolean checkSingle(String moviename, String workingDir) throws IOException{
        String thumbDir = workingDir+"\\thumbs\\";        
        return Moviename.getInstance().FileExist(thumbDir + moviename+".jpg");
    }
    
  /*  public void checkAll(String moviename, String workingDir){
        String thumbDir = workingDir+"\\thumbs\\";
    }*/
    
}
