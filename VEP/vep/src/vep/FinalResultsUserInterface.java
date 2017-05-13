/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vep;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author shinson
 */
public class FinalResultsUserInterface extends BorderPane {

    String victor;
    Vep vep;

    FinalResultsUserInterface(String victor, Vep v) {
        super();
        this.victor = victor;
        this.vep = v;

        //call functions to get header, graphs and button
        HBox header = this.buildHeader();
        VBox middle = this.buildMidRegionVBox();
        VBox endProgramButtonHolder = this.buildEndProgramButton();

        //add items to correct regions
        this.setTop(header);
        this.setCenter(middle);
        this.setBottom(endProgramButtonHolder);

    }

    private VBox buildMidRegionVBox() {
        VBox middle = new VBox();

        //build text explanation and format
        Text endExplanation = new Text(20, 40, "The graphs below summarize the results of the election along with statistics which the administration may find interesting. When you have finished viewing the graphs please hit the end program button to close down the program. These graphs and results will remain accessbile to you via a PDF which was generated upon the completion of the voting process.");
        endExplanation.setFont(new Font(30));
        endExplanation.setWrappingWidth(1000);
        endExplanation.setTextAlignment(TextAlignment.CENTER);

        //grab formatted images
        HBox images = this.buildImagesHBox();

        //add all of them to a VBox, format and return
        middle.getChildren().addAll(endExplanation, images);
        middle.setAlignment(Pos.CENTER);
        middle.setSpacing(50);

        return middle;
    }

    private HBox buildImagesHBox() {
        HBox imagesHBox = new HBox();

        //build images. NOTE: FK you will need to put in the proper file locations. I cannot access them right now because of github
        Rectangle image1 = this.buildImage("../../");
        Rectangle image2 = this.buildImage("../../");
        Rectangle image3 = this.buildImage("../../");

        imagesHBox.getChildren().addAll(image1, image2, image3);
        imagesHBox.setSpacing(50);
        imagesHBox.setAlignment(Pos.CENTER);

        return imagesHBox;
    }

    private static Rectangle buildImage(String location) {
        Rectangle display = new Rectangle(500, 400);
//        Add this code back in once locations are correct and delete my set fill
//        Image image = new Image(location);
//        ImagePattern ip = new ImagePattern(image);
//        display.setFill(ip);
        display.setFill(Color.GREEN);
        display.setStroke(Color.BLACK);
        display.setStrokeWidth(5);

        return display;
    }

    private HBox buildHeader() {
        //build a header to explain the winner
        HBox header = new HBox();
        Text infoBox = new Text("The Winners are " + this.victor + "!");
        infoBox.setFont(new Font(75));
        infoBox.setWrappingWidth(1200);
        infoBox.setTextAlignment(TextAlignment.CENTER);

        //format
        header.getChildren().add(infoBox);
        header.setAlignment(Pos.CENTER);

        return header;
    }

    private VBox buildEndProgramButton() {
        //build a button and format it before adding to a VBox and returning
        VBox buttonHolder = new VBox();

        //FK this is where your lambda will go
        Button endProgramButton = new Button("End Program");
        endProgramButton.setFont(new Font(20));
        
        buttonHolder.getChildren().add(endProgramButton);
        buttonHolder.setAlignment(Pos.CENTER);
        buttonHolder.setMargin(endProgramButton, new Insets(0,0,50,0));
        
        return buttonHolder;

    }

}
