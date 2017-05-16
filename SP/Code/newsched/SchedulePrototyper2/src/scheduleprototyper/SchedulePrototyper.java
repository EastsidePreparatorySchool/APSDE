package scheduleprototyper;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author hhale
 */
public class SchedulePrototyper extends Application {

    public static GraphicsContext MasterContext;
    //courseX and courseY are the position of the cursor in the table of courses.
    int courseX = 0;
    int courseY = 0;
    SplitPane coursesShown;
    FlowPane coursesShown2;
    //the current 8 courses being shown go in coursesShown

    @Override
    public void start(Stage primaryStage) {
        //this block gets the data from the csv and the user's info 
        //and turns that into a filtered list of legal courses.
        FileReader fr = new FileReader();
        ArrayList<Course> courses = fr.readFile("rshaw.csv");
        //filteredCourses will need to be written to as some function of courses, Aman and Theo.
        //ArrayList<ArrayList<Course>> filteredCourses = new ArrayList<>();
        ArrayList<Course> filteredCourses = new ArrayList<>();
        filteredCourses = courses; //AMAN AND THEO CAN CHANGE THIS ONCE THEY WRITE THIS METHOD

       //this block instantiates graphics with coursesShown at column 0
        coursesShown = new SplitPane();
        coursesShown.setMinHeight(0);
        
        ArrayList<Course> periodA = new ArrayList<>();
        ArrayList<Course> periodB = new ArrayList<>();
        ArrayList<Course> periodC = new ArrayList<>();
        ArrayList<Course> periodD = new ArrayList<>();
        ArrayList<Course> periodE = new ArrayList<>();
        ArrayList<Course> periodF = new ArrayList<>();
        ArrayList<Course> periodG = new ArrayList<>();
        ArrayList<Course> periodH = new ArrayList<>();
        for(int l =0; l < filteredCourses.size(); l++) {
            Course currentcourse = filteredCourses.get(l);
            char period = currentcourse.getPeriod();
            switch(period) {
                case 'A':
                    periodA.add(currentcourse);
                case 'B':
                    periodB.add(currentcourse);
                case 'C':
                    periodC.add(currentcourse);
                case 'D':
                    periodD.add(currentcourse);
                case 'E':
                    periodE.add(currentcourse);
                case 'F':
                    periodF.add(currentcourse);
                case 'G':
                    periodG.add(currentcourse);
                case 'H':
                    periodH.add(currentcourse);
                
            }
        }
        ScrollPane period1 = new ScrollPane();
        period1.minHeight(26);
        period1.minWidth(400);
        period1.setContent(getLabelPane(periodA));
        System.out.println(periodA);
        System.out.println(periodB);
        StackPane period2 = new StackPane(getLabelPane(periodB));
        StackPane period3 = new StackPane(getLabelPane(periodC));
        StackPane period4 = new StackPane(getLabelPane(periodD));
        StackPane period5 = new StackPane(getLabelPane(periodE));
        StackPane period6 = new StackPane(getLabelPane(periodF));
        StackPane period7 = new StackPane(getLabelPane(periodG));
        StackPane period8 = new StackPane(getLabelPane(periodH));
//        ScrollPane period2 = new ScrollPane(getLabelPane(periodB));
//        period2.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        ScrollPane period3 = new ScrollPane(getLabelPane(periodC));
//        period3.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        ScrollPane period4 = new ScrollPane(getLabelPane(periodD));
//        period4.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        ScrollPane period5 = new ScrollPane(getLabelPane(periodE));
//        period5.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        ScrollPane period6 = new ScrollPane(getLabelPane(periodF));
//        period6.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        ScrollPane period7 = new ScrollPane(getLabelPane(periodG));
//        period7.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        ScrollPane period8 = new ScrollPane(getLabelPane(periodH));
//        period8.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        coursesShown.getItems().addAll(period1, period2, period3, period4, period5, period6, period7, period8);
        this.updatePrimaryStage(primaryStage);
        this.updateCoursesShown();
    }
    public FlowPane getLabelPane(ArrayList<Course> period) {
        FlowPane f = new FlowPane();
        for(int j = 0; j < period.size(); j++) {
          Course tmp = period.get(j);
          Label r = new Label(tmp.getName());
          r.setStyle("-fx-background-color: orange; "
                  + "-fx-padding: 5px; "
                  + "-fx-border-width: 2px; "
                  + "-fx-border-color: #4d4d4d;");
          StackPane stack = new StackPane();
          stack.getChildren().add(r);
          f.getChildren().add(stack);
        }
        f.setHgap(5);
        f.setVgap(5);
        return f;
    }
 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void updateCoursesShown() {
        //update coursesShown to reflect the keypress (get new data from courseTable)
        SplitPane newCoursesShown = new SplitPane();
        
        for (int i = 0; i < 8; i++) {
            //if i == the row that was just modified 
            if (i == this.courseY) {
                //get the new data
                newCoursesShown.getItems().add(new StackPane(new Label("(" + this.courseX + ", " + i + ")")));
            } else {
                //else just use the old data
                StackPane oldPane = (StackPane) coursesShown.getItems().get(i);
                newCoursesShown.getItems().add(oldPane);
            }
        }
        coursesShown = newCoursesShown;
    }
    
    public void updatePrimaryStage(Stage primaryStage) {
        StackPane leftPane = new StackPane(new Label("Left"));
        StackPane rightPane = new StackPane(coursesShown); //spacing = 8
        coursesShown.setOrientation(Orientation.VERTICAL);
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(leftPane, rightPane);
        splitPane.setDividerPositions(0.25);
        //Constrain max size of left component, creates limits to class schedule
        leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.25)); //0.25 = 25% of screen
        Scene scene = new Scene(new BorderPane(splitPane), 800, 600);
        primaryStage.setScene(scene);
        this.assignControls(primaryStage);
        primaryStage.show();
    }
        
    public void assignControls(Stage primaryStage) {
        primaryStage.getScene().setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                    this.courseX--;
                    break;
                case RIGHT:
                    this.courseX++;
                    break;
                case DOWN:
                    this.courseY++;
                    break;
                case UP:
                    this.courseY--;
                    break;
            }
            System.out.println(courseX +", " + courseY);
            this.updateCoursesShown();
            this.updatePrimaryStage(primaryStage);
        });
    }
}