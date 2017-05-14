/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PreRequisistes;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Current Functionality only for core classes + programming. 
 * @author abedi
 */
public class PreRequisites extends Application {

   static ArrayList<String> classes = new ArrayList<String>();

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // name  textfield
        TextField name = new TextField();
        name.setPromptText("Enter your name");
        name.setPrefColumnCount(10);
        GridPane.setConstraints(name, 0, 0);
        grid.getChildren().add(name);
        
        // grade textfield
        TextField grade = new TextField();
        grade.setPromptText("Enter your grade");
        GridPane.setConstraints(grade, 0, 1);
        grid.getChildren().add(grade);
        
        // spanish textfield
        TextField spanish = new TextField();
        spanish.setPromptText("Please enter your current Spanish Class");
        spanish.setPrefColumnCount(25);
        GridPane.setConstraints(spanish, 0, 2);
        grid.getChildren().add(spanish);
        
        // math textfield
        TextField math = new TextField();
        math.setPromptText("Please enter your current Math Class");
        math.setPrefColumnCount(25);
        GridPane.setConstraints(math, 0, 3);
        grid.getChildren().add(math);
        
         // programming textfield
        TextField program = new TextField();
        program.setPromptText("Please enter the most recent programming class you have taken");
        program.setPrefColumnCount(25);
        GridPane.setConstraints(program, 0, 4);
        grid.getChildren().add(program);
        
        // submit button
        Button submit = new Button();
        submit.setText("Submit");
        submit.setPrefWidth(80);
        GridPane.setConstraints(submit, 1, 4);
        grid.getChildren().add(submit);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                spanishClass(spanish.getText().toLowerCase());
                scienceClass(grade.getText());
                
                mathClass(math.getText().toLowerCase());
                programClass(program.getText().toLowerCase());
                // priniting of course list to console
                for(int i = 0; i < classes.size(); i++){
                    System.out.println(classes.get(i));
                }
            }
        });

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

    public static void scienceClass(String grade){
        if(grade.contains("12")){
            classes.add("Advanced Physics");
            classes.add("Advanced Chemistry");
            classes.add("Advanced Biology");
            classes.add("Enviromental Science");
        }else if(grade.contains("11")){
            classes.add("Physics");
            classes.add("Advanced Chemistry");
            classes.add("Advanced Biology");
            classes.add("Enviromental Science");
        } else if(grade.contains("10")){
            classes.add("Chemistry");
            classes.add("Enviromental Science");
        }else {
            classes.add("Biology");
        }
    }

    // all of the following functions require the string to be passed through to be converted to lower case
    public static void spanishClass(String course) {
        if (course.contains("5") || course.contains("five") || course.contains("Advanced") || course.contains("four")||course.contains("4")) {
            classes.add("Advanced Spanish:Language");
            classes.add("Advanced Spanish:Literature");
            classes.add("Advanced Spanish:Conversation Through Cinema");
            classes.add("Advanced Spanish:Conversation Through Music");
            classes.add("Advanced Spanish:Conversation Through Current Events");           
        } else if(course.contains("3")|| course.contains("three")){
            classes.add("Spanish 4");
        }else if(course.contains("2")|| course.contains("two")){
            classes.add("Spanish 3");
        }else if(course.contains("1")|| course.contains("one")){
            classes.add("Spanish two");
        }else{
            classes.add("Spanish 1");
        }
    }
    public static void mathClass(String course){
        if(course.contains("algebra") && (course.contains("2") || course.contains("two"))){
            classes.add("Pre-Calculus");
            classes.add("Statistics");
        } else if(course.contains("algebra")){
            classes.add("Geometry");
        } else if (course.contains("geometry")){
            classes.add("Algebra 2");
        } else if (course.contains("calculus") && (course.contains("pre"))){
            classes.add("Calculus 1");
            classes.add("Statistics");
            classes.add("Math Beyond the Numbers");
        } else if (course.contains("topics")){
            // last math class in curicculum so no classes
        } else if (course.contains("advanced")){
            classes.add("Math Beyond the Numbers");
            classes.add("Statistics");
        } else if (course.contains("calculus")){
            classes.add("Math Beyond the Numbers");
            classes.add("Statistics");
            classes.add("Advanced Calculus");
        } else if (course.contains("math")){
            classes.add("Calculus 1");
            classes.add("Statistics");
        } else {
            classes.add("Algebra 1");
        }
    }
    public static void programClass(String course){
        if(course.contains("two") || course.contains("2")){
           classes.add("Advanced Programming: Software and Data Structures");
           classes.add("Advanced Programming: Web Applications");
           classes.add("Advanced Programming: Software Design Engineering");
        } else if (course.contains("one") || course.contains("1")){
           classes.add("Programming 2");
        } else {
            classes.add("Programming 1");
        }
    
    }
    
}
