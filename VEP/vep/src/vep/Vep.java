/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author HSamuelson
 */
public class Vep extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //launch(args);
        // ===== HENRYS CODE DO NOT TOUCH (for now)=====
        System.out.println("hellow world");
        //Fartingaroundmain.path;
        System.out.println(System.getProperty("user.dir").toLowerCase());
        //String cmd = (System.getProperty("user.dir").toLowerCase()).replace("C:/", " ")+"\\hsamuelsonRstandalone\\R-3.3.0\\bin\\rscript.exe" + (System.getProperty("user.dir").toLowerCase()).replace("C:/", " ");
        String cmd = (System.getProperty("user.dir") ).replace("C:", " ")+"\\hsamuelsonRstandalone\\R-3.3.0\\bin\\rscript.exe " + (System.getProperty("user.dir")).replace("C:", " ") + "\\tester.R";

        System.out.println(cmd);
        Runtime r = Runtime.getRuntime();
        Process pr = r.exec(cmd);

        BufferedReader stdInput = new BufferedReader(
            new InputStreamReader( pr.getInputStream() ));

        String s ;
        
        
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }
    }
    
}
