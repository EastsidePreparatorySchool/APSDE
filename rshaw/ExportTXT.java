/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exporttxt;

/**
 *
 * @author RShaw
 */
import java.io.PrintWriter;
import java.util.*;

public class ExportTXT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    private Formatter x;
    String classes[] = {"F,9-12,Acting,ttaylor,Fine and Performing Arts", "G,9-12,Programming 1,gmein,Technology", "FREE"};
    String fName = "R";
    String lName = "Shaw";
    Date date = new Date();
    String name = fName+lName+".txt";

    public void fileCreate() {
        try {
            x = new Formatter(name);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void addClasses() {
        try {
            PrintWriter writer = new PrintWriter(name);
            for (String classe : classes) {
                writer.println(classe);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Whoops");
        }
    }

    public void closeFile() {
        x.close();
    }
}

}
