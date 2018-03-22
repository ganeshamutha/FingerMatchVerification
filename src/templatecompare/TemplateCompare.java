/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templatecompare;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import MFS100.*;


/**
 *
 * @author ganesh
 */
public class TemplateCompare implements MFS100Event {

    private byte[] template1;
    private byte[] template2;
    
    String file1 = "./pl_ansi.template";
    //String file2 = "./gr_ansi.template";
    String file2 = "./pl_ansi.template";
            
    
    TemplateCompare() {
        template1 = new byte[512];
        template2 = new byte[512];
    }
    
    
    void startLoading() throws FileNotFoundException, IOException {

        
        FileInputStream fis = new FileInputStream(file1);
        fis.read(template1);
        fis.close();


        FileInputStream fis2 = new FileInputStream(file2);
        fis2.read(template2);
        fis2.close();
        
    }
    
    
    int compareTemplate() {
        
        //deviceManger.

        MFS100 device = new MFS100(this);

        device.Init();
        
        int ret = device.MatchANSI(template1, template2);

        System.out.println("The Match score " + Integer.toString(ret));

        if(ret < 0) {
            System.out.println("Error while matching " + device.GetErrorMsg(ret));
        }
        
        return ret;
        
    }
            
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        System.out.println("The Current working directory " + System.getProperty("user.dir") );

        TemplateCompare tc = new TemplateCompare();
        
        tc.startLoading();
        
        tc.compareTemplate();
        
        return;
    }

    @Override
    public void OnPreview(FingerData fd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnCaptureCompleted(boolean bln, int i, String string, FingerData fd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
