package scheduleprotoyper;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
        
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(400, 400);
        root.getChildren().add(canvas);
        MasterContext = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Schedule Prototyper");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        FileReader fr = new FileReader();
        ArrayList<Course> courses = fr.readFile();
        //display prereq ui
        //generate course set
        //filter course set using prereq data
        //display the courses remaining in Justice's UI
        //use prereq data to get current credit years
        //
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
