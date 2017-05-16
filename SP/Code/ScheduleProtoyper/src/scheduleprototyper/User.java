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

public class User {
    private ArrayList<String> classes;
    private String name;
    public User(ArrayList<String> potentialClasses, String name){
        classes=potentialClasses;
        this.name=name;
    }
    public ArrayList<String> getPossibleClasses(){
        return classes;
    }
    public String  getName(){
        return name;
    }
     public static void main(String a[]){
    }
}
