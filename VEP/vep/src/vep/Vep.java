/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vep;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 *
 * @author HSamuelson
 */
public class Vep extends Application {

    Scene scene;

    //String styleFile = "vep/css/verifiableElectionStyle1.css";
    //String styleFile = "vep/css/verifiableElectionStyle2.css";
    String styleFile = "vep/css/verifiableElectionStyle3.css";

    public String FirstName = null;
    public String LastName = null;
    public String ID = null;

    @Override
    public void start(Stage primaryStage) {
        scene = new Scene(root, 2000, 1000);
        scene.getStylesheets().add(styleFile);
        primaryStage.setTitle("EPS Student Body President and Vice President Election 2017");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openVotingUserInterface() {
        //build proper scene and put it in place. Garbage collector will kill off the old one
        BorderPane root = new VotingUserInterface(this);
        scene.setRoot(root);
    }

    public void openLoginUserInterface() {
        //build proper scene and put it in place. Garbage collector will kill off the old one
        BorderPane root = new LoginUserInterface(this);
        scene.setRoot(root);
    }

    public void openFinalResultsUserInterface(String victor) {
        //build results page and show it
        BorderPane root = new FinalResultsUserInterface(victor, this);
        scene.setRoot(root);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        launch(args);
        System.out.println("hellow world");
        //Fartingaroundmain.path;
        System.out.println(System.getProperty("user.dir").toLowerCase());
        //String cmd = (System.getProperty("user.dir").toLowerCase()).replace("C:/", " ")+"\\hsamuelsonRstandalone\\R-3.3.0\\bin\\rscript.exe" + (System.getProperty("user.dir").toLowerCase()).replace("C:/", " ");
        // String cmd = (System.getProperty("user.dir") ).replace("C:", " ")+"\\hsamuelsonRstandalone\\R-3.3.0\\bin\\rscript.exe " + (System.getProperty("user.dir")).replace("C:", " ") + "\\tester.R 2977 WaltzerAdam 1";

//        System.out.println(cmd);
//        Runtime r = Runtime.getRuntime();
//        Process pr = r.exec(cmd);
//
//        BufferedReader stdInput = new BufferedReader(
//                new InputStreamReader(pr.getInputStream()));
//
//        String s;
//
//        while ((s = stdInput.readLine()) != null) {
//            System.out.println(s);
//        }
    }

    public static int IDChecker(String ID, String Lastname, String Firstname, String Vote) throws IOException {

        String cmd = (System.getProperty("user.dir")).replace("C:", " ") + "\\hsamuelsonRstandalone\\R-3.3.0\\bin\\rscript.exe " + (System.getProperty("user.dir")).replace("C:", " ") + "\\tester.R " + ID + " " + Lastname + Firstname + " " + Vote;
        System.out.println(cmd);
        Runtime r = Runtime.getRuntime();
        Process pr = r.exec(cmd);

        BufferedReader stdInput = new BufferedReader(
                new InputStreamReader(pr.getInputStream()));

        String s;

        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
            if (s.equals("[1] 1")) {
                return 1;
            } else if (s.equals("[1] 0")) {
                return 0;
            }
        }
        return -1;
    }

}
