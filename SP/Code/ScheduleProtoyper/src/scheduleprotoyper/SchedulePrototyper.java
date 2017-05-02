package scheduleprotoyper;

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
        Course testCourse = new Course("placeholder string");
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Schedule Prototyper");
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
