/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vep;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
    Boolean explainedSpoiling = false;
    String Vote = "-1";
    int Tally1;//Temporary Ethan

    int Tally2;//Temporary Maja

    VotingUserInterface(Vep v) {
        super();
        this.vep = v;
        HBox candidates = buildCandidatesHBox();
        HBox logoutButtonHolder = buildLogoutButton();
        HBox votingButtonsHolder = buildVotingButtons();
        StackPane s = new StackPane();//used for coloring format

        //add relevant items to borderPane
        //since I can only add one node to the center I need a vbox to hold the candidate and buttons holders
        VBox centerHolder = new VBox();
        centerHolder.getChildren().addAll(candidates, votingButtonsHolder);
        centerHolder.setSpacing(50);

        //this is to style the borderPane with css
        this.getStyleClass().add("pane");
        s.getStyleClass().add("box");
        centerHolder.getStyleClass().add("contentBox");

        //place items where we want them and format top button to be on the right
        s.getChildren().add(centerHolder);
        this.setCenter(s);
        this.setMargin(this.getCenter(), new Insets(50, 0, 100, 0));
        this.setTop(logoutButtonHolder);
        this.setAlignment(this.getTop(), Pos.TOP_RIGHT);
    }

    public HBox buildCandidatesHBox() {
        HBox candidates = new HBox();

        //build checkBox
        //these checkboxes are here for now however may be swapped out later for an alternative form of selection
        CheckBox vote1 = new CheckBox("Select this candidate");

        CheckBox vote2 = new CheckBox("Select this candidate");

        //will hold candidates and text
        VBox picTextOne = buildCandidateVBox("netzPlaceHolder.jpg", "Ethan Netz and Drew Medway", vote1);//since netz has yet to give me a campaign photo this is a placeholder. The actual photo he gives me should fit these dimensions better

        vote1.setFont(new Font(25));
        vote1.setOnMouseClicked((e) -> {
            vote2.setSelected(false);
            if (Vote.equals("1")) {
                Vote = "-1";
            } else {
                Vote = "1";
            }
        });

        VBox picTextTwo = buildCandidateVBox("majaAndAyush.jpg", "Maja Johnson and Ayush Sharma", vote2);

        vote2.setFont(new Font(25));
        vote2.setOnMouseClicked((e) -> {
            vote1.setSelected(false);
            if (Vote.equals("2")) {
                Vote = "-1";
            } else {
                Vote = "2";
            }
        });

        //add to hbox and format
        candidates.getChildren().addAll(picTextOne, picTextTwo);
        candidates.setAlignment(Pos.CENTER);
        candidates.setSpacing(200);

        return candidates;
    }

    public VBox buildCandidateVBox(String pic, String name, CheckBox vote) {
        VBox vb = new VBox();
        //Currently I have images saved to a file and I am painting them onto rectangles which can be displayed
        //This is for two reasons: this way we can have onclick options for the images and we aren't loading from a URL like in a regular image object
        Image image = new Image(pic);
        Rectangle display = new Rectangle(500, 400);
        ImagePattern ip = new ImagePattern(image);
        display.setFill(ip);
        display.setStroke(Color.BLACK);
        display.setStrokeWidth(5);

        //build text
        Text infoBox = new Text(20, 40, name);
        infoBox.setWrappingWidth(500);
        infoBox.setTextAlignment(TextAlignment.CENTER);
        infoBox.setFont(new Font(25));

        //combine elements
        vb.getChildren().addAll(display, infoBox, vote);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(20);
        return vb;
    }

    public HBox buildVotingButtons() {
        HBox votingButtonsHolder = new HBox();
        //build other buttons and set font size
        Button explainSpoilingButton = new Button("Spoiling Explanation");
        explainSpoilingButton.setFont(new Font(20));
        explainSpoilingButton.setOnMouseClicked((e) -> {
            this.toggleExplainSpoiling();
        });

        Button spoilVoteButton = new Button("Spoil my Vote");
        spoilVoteButton.setFont(new Font(20));
        Button castVoteButton = new Button("Cast my Vote");
        castVoteButton.setFont(new Font(20));

        castVoteButton.setOnMouseClicked((e) -> {
            int isgood = -1;
            try {
                System.out.println(vep.ID);
                System.out.println(vep.email);
                isgood = Vep.IDChecker(vep.ID, vep.email, Vote);

            } catch (IOException ex) {
                Logger.getLogger(VotingUserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (isgood == 1) {
                if (Vote.equals("1")) {
                    Tally1++;
                    System.out.println(Tally1);
                } else if (Vote.equals("2")) {
                    Tally2++;
                    System.out.println(Tally2);
                } else {
                    Text Error = new Text(5, 5, "ERROR! You have not selected a candidate");
                    Error.setFont(new Font(20));
                    Error.setTextAlignment(TextAlignment.CENTER);
                    Error.setFill(Color.RED);
                    VBox vb = new VBox();
                    vb.getChildren().add(Error);
                    vb.setAlignment(Pos.CENTER);
                    vb.setMargin(Error, new Insets(0, 0, 100, 0));

                    //add to borderPane and center
                    this.setBottom(vb);
                    this.setAlignment(this.getBottom(), Pos.CENTER);
                }
                System.out.println(Tally1);
                System.out.println(Tally2);
            } else if (isgood == 0) {
                Text Error = new Text(5, 5, "ERROR! You have already voted!");
                Error.setFont(new Font(20));
                Error.setTextAlignment(TextAlignment.CENTER);
                Error.setFill(Color.RED);
                VBox vb = new VBox();
                vb.getChildren().add(Error);
                vb.setAlignment(Pos.CENTER);
                vb.setMargin(Error, new Insets(0, 0, 100, 0));

                //add to borderPane and center
                this.setBottom(vb);
                this.setAlignment(this.getBottom(), Pos.CENTER);
            }
        });

        //add other buttons to their HBox and format
        votingButtonsHolder.getChildren().addAll(explainSpoilingButton, castVoteButton, spoilVoteButton);
        votingButtonsHolder.setAlignment(Pos.CENTER);
        votingButtonsHolder.setSpacing(50);

        return votingButtonsHolder;
    }

    public HBox buildLogoutButton() {
        HBox logoutButtonHolder = new HBox();

        //create logoutButton
        Button logoutButton = new Button("Log Out");
        logoutButton.setFont(new Font(20));
        logoutButton.setOnMouseClicked((e) -> {
            //FK please insert code here
            this.vep.openLoginUserInterface();
        });

        //add logout button to HBox and format
        logoutButtonHolder.getChildren().add(logoutButton);
        logoutButtonHolder.setAlignment(Pos.TOP_RIGHT);
        logoutButtonHolder.setMargin(logoutButton, new Insets(20, 20, 20, 20));

        return logoutButtonHolder;
    }

    public void toggleExplainSpoiling() {
        if (this.explainedSpoiling == false) {
            //Build a paragraph explanation, format it, put it in a VBox for margins, then add it to the bottom of the borderPane .
            Text whatIsSpoiling = new Text(20, 40, "What makes this election process verifiable is your ability to spoil your vote. When you spoil your vote it will no longer be counted by the computer. However, you will be shown how the computer interpreted your vote. This is meant as a safeguard against potential interference with the voting process. Spoiling the vote allows you to remain confident that your ballot is remaining untampered. To spoil your vote simple press the spoil my vote button. However, you will need to reselect a candidate and vote again if you spoil your vote since it will no longer be counted. To close this message please click the what is spoiling explanation button again");
            whatIsSpoiling.setFont(new Font(20));
            whatIsSpoiling.setWrappingWidth(1000);
            whatIsSpoiling.setTextAlignment(TextAlignment.CENTER);

            //put in a vbox for centering
            VBox vb = new VBox();
            vb.getChildren().add(whatIsSpoiling);
            vb.setAlignment(Pos.CENTER);
            vb.getStyleClass().addAll("spoilingExplanation");

            ((VBox) ((StackPane) this.getCenter()).getChildren().get(0)).getChildren().add(vb);

            this.explainedSpoiling = true;
        } else if (this.explainedSpoiling == true) {
            ((VBox) ((StackPane) this.getCenter()).getChildren().get(0)).getChildren().remove(2);
            this.explainedSpoiling = false;
        }
    }

}
