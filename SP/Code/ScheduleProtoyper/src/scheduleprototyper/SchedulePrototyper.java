package scheduleprototyper;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author hhale 
 */
public class SchedulePrototyper extends Application {
    
    public static GraphicsContext MasterContext;
    
    @Override
    public void start(Stage primaryStage) {
        
       ArrayList<ArrayList<Course>> filteredCourses = new ArrayList<>();        
        FileReader fr = new FileReader();
        ArrayList<Course> courses = fr.readFile("rshaw.csv");
        SplitPane courseArea = new SplitPane();
        //renamed courseArea from "rows"
        courseArea.setMinHeight(0);
        StackPane leftPane = new StackPane(new Label("Left"));
        StackPane rightPane = new StackPane(courseArea); //spacing = 8
        SplitPane splitPane = new SplitPane();
        courseArea.setOrientation(Orientation.VERTICAL); // sets courseArea inside right splitpane vertical

        ArrayList<ArrayList<Course>> courseCoords = new ArrayList<>();
        for (ArrayList<Course> ca : filteredCourses) {
            courseCoords.add(ca);
        }
        for (int i=1; i <= 8; i++) {
            courseArea.getItems().add(new StackPane(new Label(Integer.toString(i))));
        }
        
        splitPane.getItems().addAll(leftPane, rightPane);
        splitPane.setDividerPositions(0.25);

        //Constrain max size of left component, creates limits to class schedule
        leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.25)); //0.25 = 25% of screen

        primaryStage.setScene(new Scene(new BorderPane(splitPane), 800, 600));
        primaryStage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void arrowLeft() {
        //make updates to CourseArea from CourseCoords
    }
    
}
