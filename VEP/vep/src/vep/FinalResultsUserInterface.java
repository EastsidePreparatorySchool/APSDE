/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vep;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        HBox imagesHBox = this.buildImageHBox();
        HBox logoutButtonHolder = buildLogoutButton();
        
        
        //add items to correct regions
        this.setTop(header);
        this.setCenter(imagesHBox);

    }

    private HBox buildImageHBox() {
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
        Text infoBox = new Text("The Winners are " + this.victor + " !");
        infoBox.setFont(new Font(75));
        infoBox.setWrappingWidth(1200);
        infoBox.setTextAlignment(TextAlignment.CENTER);

        //format
        header.getChildren().add(infoBox);
        header.setAlignment(Pos.CENTER);

        return header;
    }
    
    private VBox buildLogoutButton() {
        VBox buttonHolder = new VBox();
        
        
        
        return buttonHolder;
        
    }

}
