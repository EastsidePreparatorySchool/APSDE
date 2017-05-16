package scheduleprototyper;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
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
    //the current 8 courses being shown go in coursesShown

    @Override
    public void start(Stage primaryStage) {
        //this block gets the data from the csv and the user's info 
        //and turns that into a filtered list of legal courses.
        FileReader fr = new FileReader();
        ArrayList<Course> courses = fr.readFile("rshaw.csv");
        //filteredCourses will need to be written to as some function of courses, Aman and Theo.
        ArrayList<ArrayList<Course>> filteredCourses = new ArrayList<>();
        filteredCourses.add(courses);

       //this block instantiates graphics with coursesShown at column 0
        coursesShown = new SplitPane();
        coursesShown.setMinHeight(0);
        for (int i = 0; i < 8; i++) {
            coursesShown.getItems().add(new StackPane(new Label("(0, " + i + ")")));
        }
        this.updatePrimaryStage(primaryStage);
        this.updateCoursesShown();
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
