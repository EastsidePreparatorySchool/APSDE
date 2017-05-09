package scheduleprototyper;

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
        StackPane a = new StackPane(new Label("A"));
        StackPane b = new StackPane(new Label("B"));
        StackPane c = new StackPane(new Label("C"));
        StackPane d = new StackPane(new Label("D"));
        StackPane e = new StackPane(new Label("E"));
        StackPane f = new StackPane(new Label("F"));
        StackPane g = new StackPane(new Label("G"));
        StackPane h = new StackPane(new Label("H"));
        
        a.setMaxHeight(50); //adjusting height of rows
        
        rows.getItems().addAll(a,b,c,d,e,f,g,h);
        
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