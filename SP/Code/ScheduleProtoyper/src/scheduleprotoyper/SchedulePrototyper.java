package scheduleprotoyper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author hhale 
 */
public class SchedulePrototyper extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        StackPane root = new StackPane();
        Course testCourse = new Course("placeholder string");
        root.getChildren().add(testCourse);
        
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
