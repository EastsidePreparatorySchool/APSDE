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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author shinson
 */
public class VotingUserInterface extends BorderPane {

    Vep vep;

    VotingUserInterface(Vep v) {
        super();
        this.vep = v;
        HBox candidates = new HBox(); //will hold all candidates
        HBox logoutButtonHolder = new HBox();
        HBox votingButtonsHolder = new HBox();
        //will hold candidates and text
        VBox picTextOne = buildCandidateVBox("asdfd", "Ethan Netz and Drew Medway");
        VBox picTextTwo = buildCandidateVBox("asdf", "Maja Johnson and Ayush Sharma");

        //add to hbox and format
        candidates.getChildren().addAll(picTextOne, picTextTwo);
        candidates.setAlignment(Pos.CENTER);
        candidates.setSpacing(200);

        //create logoutButton
        Button logoutButton = new Button("Log Out");
        logoutButton.setFont(new Font(20));
        logoutButton.setOnMouseClicked((e) -> {
            //this is where you put your code
           this.vep.openLoginUserInterface();
        });
        
        //add logout button to HBox and format
        logoutButtonHolder.getChildren().add(logoutButton);
        logoutButtonHolder.setAlignment(Pos.TOP_RIGHT); 
        logoutButtonHolder.setMargin(logoutButton, new Insets(20,20,20,20));
        
        
        //build other buttons
        Button explainSpoilingButton = new Button("What is Spoiling");
        explainSpoilingButton.setFont(new Font(20));
        Button spoilVoteButton = new Button("Spoil my Vote");
        spoilVoteButton.setFont(new Font(20));
        Button castVoteButton = new Button("Cast my Vote");
        castVoteButton.setFont(new Font(20));
        
        //add other buttons to their HBox
        votingButtonsHolder.getChildren().addAll(explainSpoilingButton, castVoteButton, spoilVoteButton);
        votingButtonsHolder.setAlignment(Pos.CENTER);
        votingButtonsHolder.setSpacing(50);
        
        //add relevant items to borderPane
        //since I can only add one node to the center I need a vbox to hold the candidate and buttons holders
        VBox centerHolder = new VBox();
        centerHolder.getChildren().addAll(candidates, votingButtonsHolder);
        centerHolder.setSpacing(50);
        this.setCenter(centerHolder);
        this.setTop(logoutButton);
        this.setAlignment(this.getTop(), Pos.TOP_RIGHT);
        

    }

    public VBox buildCandidateVBox(String pic, String names) {
        VBox vb = new VBox();
        //Currently I have images saved to a file and I am painting them onto rectangles which can be displayed
        //This is for two reasons: this way we can have onclick options for the images and we aren't loading from a URL like in a regular image object
        //Image pairingOne = new Image("C:\\Users\\shinson\\Documents\\GitHub\\APSDE\\VEP\\vep\\netzPlaceHolder.jpg");
        Rectangle display = new Rectangle(500, 500);
        //ImagePattern ip1 = new ImagePattern(pic);
        display.setFill(Color.ALICEBLUE);

        //build text
        Text infoBox = new Text(20, 40, names);
        infoBox.setWrappingWidth(500);
        infoBox.setTextAlignment(TextAlignment.CENTER);
        infoBox.setFont(new Font(25));

        //combine elementes
        vb.getChildren().addAll(display, infoBox);

        return vb;
    }

}
