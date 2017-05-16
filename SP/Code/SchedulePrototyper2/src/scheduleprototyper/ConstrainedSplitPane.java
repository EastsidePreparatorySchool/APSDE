package scheduleprototyper;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ConstrainedSplitPane extends Application {

    @Override
    public void start(Stage primaryStage) {
        SplitPane rows = new SplitPane();
        rows.setMinHeight(0);
        StackPane leftPane = new StackPane(new Label("Left"));
        StackPane rightPane = new StackPane(rows); //spacing = 8
        SplitPane splitPane = new SplitPane();
        rows.setOrientation(Orientation.VERTICAL); // sets rows inside right splitpane verticle      
        
        //why is this not a loop?
        StackPane period1 = new StackPane(new Label("A"));
        StackPane period2 = new StackPane(new Label("B"));
        StackPane period3 = new StackPane(new Label("C"));
        StackPane period4 = new StackPane(new Label("D"));
        StackPane period5 = new StackPane(new Label("E"));
        StackPane period6 = new StackPane(new Label("F"));
        StackPane period7 = new StackPane(new Label("G"));
        StackPane period8 = new StackPane(new Label("H"));
        
        period1.setMaxHeight(50);
        period2.setMaxHeight(50);
        period3.setMaxHeight(50);
        period4.setMaxHeight(50);
        period5.setMaxHeight(50);
        period6.setMaxHeight(50);
        period7.setMaxHeight(50);
        period8.setMaxHeight(50);
        
        rows.getItems().addAll(period1,period2,period3,period4,period5,period6,period7,period8);
        
        splitPane.getItems().addAll(leftPane, rightPane);
        splitPane.setDividerPositions(0.25);

        //Constrain max size of left component, creates limits to class schedule
        leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.25)); //0.25 = 25% of screen

        primaryStage.setScene(new Scene(new BorderPane(splitPane), 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}