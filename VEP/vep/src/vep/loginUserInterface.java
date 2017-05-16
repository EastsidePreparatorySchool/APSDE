/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author shinson
 */
public class LoginUserInterface extends BorderPane {

    Vep vep;

    LoginUserInterface(Vep v) {
        super(); // build border pane

        this.vep = v;//this is so that we will have access to the main scene rather than needing to open another one
        vep.email = null;
        vep.ID = null;
        //build the vboxes and hbox which will hold all relevant fields
        VBox instructions = buildInstructions();
        VBox textFields = buildTextFields();
        StackPane s = new StackPane();//this is going to allow for some creative coloring
        StackPane s2 = new StackPane();//same as above

        //css for these fields 
        this.getStyleClass().add("pane");
        s.getStyleClass().addAll("spoilingExplanation", "box");
        s2.getStyleClass().add("box");

        //combine instructions and its holder stackpane
        s.getChildren().add(instructions);
        s.setMargin(instructions, new Insets(75, 0, 0, 0));

        //combine textfields withh holder
        s2.getChildren().add(textFields);

        //add vboxes to relevant locations
        this.setTop(s);
        this.setAlignment(this.getTop(), Pos.CENTER);
        this.setCenter(s2);
        this.setMargin(this.getCenter(), new Insets(0, 200, 0, 200));

    }

    public VBox buildInstructions() {

        VBox instructions = new VBox();
        //build explanation of instructions at the top of the page and add it into a vbox
        Text infoBox = new Text(20, 40, "Welcome to the Verifiable Election Booth! Please note that in this election process we value your anonymity. This voting process is designed to ensure that nobody can discern who you voted for. It achieves this through encryption, compartmentalization and your ability to spoil the vote, which will be explained later. For now, to begin voting please enter your email and password. This password should have been sent to your email earlier today.");
        infoBox.setFont(new Font(20));
        infoBox.setWrappingWidth(800);
        infoBox.setTextAlignment(TextAlignment.CENTER);
        infoBox.getStyleClass().add("text");

        //format and place top explanation may need to reformat later
        instructions.getChildren().add(infoBox);
        instructions.setAlignment(Pos.CENTER);
        instructions.getStyleClass().add("contentBox");
        instructions.setMaxWidth(1000);
        instructions.setMargin(instructions, new Insets(100, 0, 100, 0));

        return instructions;
    }

    public VBox buildTextFields() {
        VBox textFields = new VBox();

        //build the text fields, define their lengths and add them into a vbox
        TextField email = new TextField("Username");
        Text add = new Text("@eastsideprep.org");
        add.setFont(new Font(20));
        add.getStyleClass().add("text");
        add.lineSpacingProperty();

        email.setMaxWidth(300);
        email.setFont(new Font(20));

        
        TextField password = new TextField("Password");
        password.setMaxWidth(300);
        password.setFont(new Font(20));
        
        GridPane grid = new GridPane();
        grid.setVgap(20);
        grid.setHgap(4);
        grid.setPadding(new Insets(5, 0, 0, 0));
        grid.add(email, 0, 0);
        grid.add(add, 1, 0);
        grid.add(password,0,1);
        grid.setAlignment(Pos.CENTER);


        //add to vbox and do a little formatting
        textFields.getChildren().add(grid);
        //textFields.setAlignment(Pos.CENTER);
        textFields.setSpacing(60);
        Button loginButton = new Button("Log In");
        loginButton.setFont(new Font(20));

        loginButton.setOnMouseClicked((e) -> {
            vep.email = email.getText();
            vep.ID = password.getText();

            int isgood = -1;
            try {
                isgood = Vep.IDChecker(vep.ID, vep.email, "0");
            } catch (IOException ex) {
                Logger.getLogger(LoginUserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (isgood == 1) {

                this.vep.openVotingUserInterface();

            } else if (isgood == 0) {
                Text Error = new Text(5, 5, "ERROR! Name does not match your number.");
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

            } else if (isgood == 666) {
                String winner = "Meme Lord Henry";
                vep.openFinalResultsUserInterface(winner);
                System.out.println("Henry Wins!!!!");
            }

        }
        );

        //add to vbox and format
        textFields.getChildren().add(loginButton);
        textFields.setAlignment(Pos.CENTER);
        textFields.getStyleClass().add("contentBox");
        textFields.setMaxWidth(1000);

        return textFields;
    }
}
