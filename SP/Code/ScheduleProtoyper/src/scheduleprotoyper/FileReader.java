/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleprotoyper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author hmeng
 */


//CSV is comma separated variable, a type of file in which each line is a 
public class FileReader {
    public static List readFile(){
        List<String> listofclasses = new ArrayList<String>();
        //create blank list of strings for class objects
        Scanner scanner = null;
            try {
                //Get the scanner instance
                scanner = new Scanner(new File("filename.csv"));
                //skip initialize scanner
                while(scanner.hasNext()){
                    //read single line, put in string
                    String data = scanner.next();
                    listofclasses.add(data);
            }
            }
            //check whether or not the file exists
            catch (FileNotFoundException fe) 
            {
                    fe.printStackTrace();
            }
            finally
            {
                    scanner.close();
            }
            return(listofclasses);
    }
}
