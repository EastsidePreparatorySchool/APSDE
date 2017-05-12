package scheduleprototyper;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
    int courseX;
    int courseY;
    
    @Override
    public void start(Stage primaryStage) {
        
        FileReader fr = new FileReader();
        ArrayList<Course> courses = fr.readFile("rshaw.csv");
        //this will need to be written to as some function of courses
        ArrayList<ArrayList<Course>> filteredCourses = new ArrayList<>();

        SplitPane coursesShown = new SplitPane();
        //renamed coursesShown from "rows"
        coursesShown.setMinHeight(0);
        StackPane leftPane = new StackPane(new Label("Left"));
        StackPane rightPane = new StackPane(coursesShown); //spacing = 8
        SplitPane splitPane = new SplitPane();
        coursesShown.setOrientation(Orientation.VERTICAL); // sets coursesShown inside right splitpane vertical

        ArrayList<ArrayList<Course>> courseTable = new ArrayList<>();
        for (ArrayList<Course> ca : filteredCourses) {
            courseTable.add(ca);
        }
        for (int i=1; i <= 8; i++) {
            coursesShown.getItems().add(new StackPane(new Label(Integer.toString(i))));
        }
        
        splitPane.getItems().addAll(leftPane, rightPane);
        splitPane.setDividerPositions(0.25);

        //Constrain max size of left component, creates limits to class schedule
        leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.25)); //0.25 = 25% of screen

        Scene scene = new Scene(new BorderPane(splitPane), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        scene.setOnKeyPressed((KeyEvent event) -> {
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
                    this.courseX--;
                    break;
            }
            this.updateCoursesShown();
        });
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
    
    public void updateCoursesShown() {
        //update coursesShown to reflect the keypress (get new data from courseTable)
    }
}
