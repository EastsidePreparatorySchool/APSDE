/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleprotoyper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author hmeng
 */
public class FileReader {
    public static String readFile(int linenumber){
        Scanner scanner = null;
            try {
                //Get the scanner instance
                scanner = new Scanner(new File("filename.csv"));
                //Use Delimiter as COMMA
                
            }
            catch (FileNotFoundException fe) 
            {
                    fe.printStackTrace();
            }
            finally
            {
                    scanner.close();
            }
        return null;
    }
}
