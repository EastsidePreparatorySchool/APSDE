/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prereqs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author abedi
 */
public class Prereqs extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
         // creating a gridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(5);
        grid.setHgap(5);
        
        // name  textfield
        TextField name = new TextField();
        name.setPromptText("Enter your name");
        name.setPrefColumnCount(10);
        GridPane.setConstraints(grid, 0, 0);
        grid.getChildren().add(name);
        // grade textfield
        TextField grade = new TextField();
        grade.setPromptText("Enter your grade");
        GridPane.setConstraints(grid, 0, 1);
        grid.getChildren().add(grade);
        // spanish textfield
        TextField spanish = new TextField();
        spanish.setPromptText("Please enter your current Spanish Class");
        spanish.setPrefColumnCount(15);
        GridPane.setConstraints(grid, 0, 2);
        grid.getChildren().add(spanish);
        // science textfield
//        TextField science = new TextField();
//        science.setPromptText("Please enter your current Science Class");
//        GridPane.setConstraints(grid,0,3);
//        grid.getChildren().add(science);
        // TODO: add Programming, Web Design, Art classes, 
        //Defining the Submit button
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 0);
        grid.getChildren().add(submit);
//Defining the Clear button
        Button clear = new Button("Clear");
        GridPane.setConstraints(clear, 1, 1);
        grid.getChildren().add(clear);
        
       
        //
        
        
       
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
        
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
