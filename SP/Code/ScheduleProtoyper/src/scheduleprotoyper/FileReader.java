/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleprotoyper;
import java.io.*;
/**
 *
 * @author hmeng
 */
public class FileReader {

    public FileReader() {
    }
    public static String readToString() {
        BufferedReader br = new BufferedReader(new FileReader());
try {
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();

    while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
    }
    String everything = sb.toString();
} finally {
    br.close();
}
        return null;
    }
}
