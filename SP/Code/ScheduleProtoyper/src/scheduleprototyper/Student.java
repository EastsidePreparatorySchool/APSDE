/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleprototyper;

/**
 *
 * @author abedi
 */

import java.util.ArrayList;


public class Student {
    private ArrayList<String> classes;
    private String name;
    public Student(ArrayList<String> potentialClasses, String name){
        classes=potentialClasses;
        this.name=name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public void setClasses(ArrayList<String> potentialClasses){
        classes=potentialClasses;
    }
    public ArrayList<String> getClasses(){
        return classes;
    }
    public String getName(){
        return name;
    }
     public static void main(String a[]){
//      ArrayList<String> courses = new ArrayList<String>();
//      courses.add("BOB");
//      courses.add("JOHN");
//      Student student = new Student(courses,"Kawarjit");

    }
}

