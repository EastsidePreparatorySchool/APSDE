/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulePrototyper;
//@TODO: fdjhafu

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Current Functionality only for core classes + programming.
 *
 * @author abedi
 */
public class Prerequisites extends Application {

    static ArrayList<String> classes = new ArrayList<String>();
    public User user;
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

        // grade array list
        ObservableList<String> gradeOptions
                = FXCollections.observableArrayList(
                        "9",
                        "10",
                        "11",
                        "12"
                );
        // add grade stuff to the field
        final ComboBox grade = new ComboBox(gradeOptions);
        Label gradeL = new Label();
        gradeL.setText("Please enter your grade");
        addClasses(grade, 1, gradeL, grid);

        // spanish array list
        ObservableList<String> spanishOptions
                = FXCollections.observableArrayList(
                        "Spanish 1",
                        "Spanish 2",
                        "Spanish 3",
                        "Spanish 4",
                        "Advanced Spanish: Language",
                        "Advanced Spanish: Literature",
                        "Advanced Spanish: Conversation Through Cinema",
                        "Advanced Spanish: Conversation Through Music",
                        "Advanced Spanish: Conversation Through Cinema"
                );
        // creating spanish label and combobox
        Label spanishL = new Label();
        spanishL.setText("Please enter your current Spanish Class");
        final ComboBox spanish = new ComboBox(spanishOptions);
        addClasses(spanish, 2, spanishL, grid);

        // math array list
        ObservableList<String> mathOptions
                = FXCollections.observableArrayList(
                        "Algebra 1",
                        "Geometry",
                        "Algebra 2",
                        "Pre-Calculus",
                        "Calculus",
                        "Advanced Calculus",
                        "Statistics"
                );
        // math Label
        Label mathL = new Label();
        mathL.setText("Please enter your current Math Class");
        // math combo
        final ComboBox math = new ComboBox(mathOptions);
        addClasses(math,3,mathL,grid);

        // programming array list
         ObservableList<String> programmingOptions
                = FXCollections.observableArrayList(
                        "Yes",
                        "No"
                );
        // programming Label
        Label programL = new Label();
        programL.setText("Have you taken a programming class");
        // programming combo
        final ComboBox programming = new ComboBox(programmingOptions);
        addClasses(programming,4,programL,grid);
        
        // music array list
        ObservableList<String> musicOptions 
                = FXCollections.observableArrayList(
                        "Beginner",
                        "Intermediate",
                        "Advanced"
                );
        // music label
        Label musicL = new Label();
        musicL.setText("What is your skill at playing an instrument");
        // music combo
        final ComboBox music = new ComboBox(musicOptions);
        addClasses(music,5,musicL,grid);
        
         // choir array list
        ObservableList<String> choirOptions 
                = FXCollections.observableArrayList(
                        "Yes",
                        "No"
                );
        // choir label
        Label choirL = new Label();
        choirL.setText("Are you in chamber choir");
        // choir combo
        final ComboBox choir = new ComboBox(choirOptions);
        addClasses(choir,6,choirL,grid);
        
        // creative Writing array list
        ObservableList<String> creativeOptions 
                = FXCollections.observableArrayList(
                        "Yes",
                        "No"
                );
        // creative Writing label
        Label creativeL = new Label();
        creativeL.setText("have you taken a creative writing class before");
        // music combo
        final ComboBox creative = new ComboBox(creativeOptions);
        addClasses(creative,7,creativeL,grid);
        
        
        //submit button
        Button submit = new Button();
        submit.setText("Submit");
        submit.setPrefWidth(80);
        GridPane.setConstraints(submit, 1, 8);
        grid.getChildren().add(submit);
        submit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                spanishClass((String)spanish.getValue());
                scienceClass((String)grade.getValue());
                mathClass((String)math.getValue());
                programClass((String)programming.getValue());
                // printing of course list to console
                for (int i = 0; i < classes.size(); i++) {
                    System.out.println(classes.get(i));
                }
                user = new User(classes,name.getText());
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(grid);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Prerequisites");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void scienceClass(String grade) {
        if (grade.contains("12")) {
            classes.add("Advanced Physics");
            classes.add("Advanced Chemistry");
            classes.add("Advanced Biology");
            classes.add("Enviromental Science");
        } else if (grade.contains("11")) {
            classes.add("Physics");
            classes.add("Advanced Chemistry");
            classes.add("Advanced Biology");
            classes.add("Enviromental Science");
        } else if (grade.contains("10")) {
            classes.add("Chemistry");
            classes.add("Enviromental Science");
        } else {
            classes.add("Biology");
        }
    }

    // 
    public static void spanishClass(String course) {
        if(course.contains("Cinema")){
            classes.add("Advanced Spanish: Conversation Through Music");
            classes.add("Advanced Spanish: Conversation Through Current Events");
        }else if(course.contains("Music")){
            classes.add("Advanced Spanish: Conversation Through Cinema");
            classes.add("Advanced Spanish: Conversation Through Current Events");
        }else if (course.contains("Events")){
            classes.add("Advanced Spanish: Conversation Through Current Events");
        }else if(course.contains("Literature")){
            classes.add("Advanced Spanish: Conversation Through Cinema");
            classes.add("Advanced Spanish: Conversation Through Music");
            classes.add("Advanced Spanish: Conversation Through Current Events");
        } else if(course.contains("Language")){
            classes.add("Advanced Spanish: Literature");
            classes.add("Advanced Spanish: Conversation Through Cinema");
            classes.add("Advanced Spanish: Conversation Through Music");
            classes.add("Advanced Spanish: Conversation Through Current Events");
        }else if (course.contains("4")) {
            classes.add("Advanced Spanish: Language");
            classes.add("Advanced Spanish: Literature");
            classes.add("Advanced Spanish: Conversation Through Cinema");
            classes.add("Advanced Spanish: Conversation Through Music");
            classes.add("Advanced Spanish: Conversation Through Current Events");
        } else if (course.contains("3") || course.contains("three")) {
            classes.add("Spanish 4");
        } else if (course.contains("2") || course.contains("two")) {
            classes.add("Spanish 3");
        } else if (course.contains("1") || course.contains("one")) {
            classes.add("Spanish 2");
        } else {
            classes.add("Spanish 1");
        }
    }

    public static void mathClass(String course) {
        if (course.contains("2")) {
            classes.add("Pre-Calculus");
            classes.add("Statistics");
        } else if (course.contains("1")) {
            classes.add("Geometry");
        } else if (course.contains("Geometry")) {
            classes.add("Algebra 2");
        } else if (course.contains("pre")) {
            classes.add("Calculus");
            classes.add("Statistics");
        } else if (course.contains("Advanced")) {
            classes.add("Statistics");
        } else if (course.contains("Calculus")) {
            classes.add("Statistics");
            classes.add("Advanced Calculus");
        } else {
            classes.add("Algebra 1");
        }
    }

    public static void programClass(String course) {
        if (course.contains("Yes")) {
            classes.add("Programming 2");
        } else {
            classes.add("Programming 1");
        }

    }
    
    public static void musicClass(String course){
        if(course.contains("Advanced")){
            classes.add("Chamber Music Ensemble");
        } else if(course.contains("Intermediate")){
            classes.add("Instrumental Music Ensemble");
        }else {
            classes.add("Introduction to Instrumental Music");
        }
    }
    
    public static void choir(String course){
        if(course.contains("No")){
            classes.add("No");
        }
    }
    public static void creative(String course){
        if(course.contains("No")){
            classes.add("Creative Writing");
        }else {
            classes.add("Advanced Creative Writing: Fiction and Poetry");
        }
    }
// helper function  to add constrainsts and add to grid
    public static void addClasses(ComboBox combo, int occurence, Label label, GridPane grid) {
        // setting location
        GridPane.setConstraints(label, 0, occurence);
        GridPane.setConstraints(combo, 1, occurence);
        // adding to grid
        grid.getChildren().add(combo);
        grid.getChildren().add(label);
    }
}
