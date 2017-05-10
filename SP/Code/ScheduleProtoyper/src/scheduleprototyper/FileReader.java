/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleprototyper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
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
        List<String> listOfClasses = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        //create blank list of strings for class objects
            try {
                //Get the scanner instance
                URL path = FileReader.class.getResource("rshaw.csv");
                Scanner s = new Scanner(new File(path.getFile()));
                //skip initialize scanner
                while(s.hasNextLine()){
                    //read single line, put in string
                    String data = s.nextLine();
                    listOfClasses.add(data);
            }
                s.close();
            }
            //check whether or not the file exists
            catch (FileNotFoundException fe) 
            {
                    fe.printStackTrace();
            }
            for (String s : listOfClasses) {
                courses.add(new Course(s));
                System.out.println(s);
            }
            return courses;
    }
}
