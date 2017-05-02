/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vep;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author shinson
 */
public class LoginUserInterface extends BorderPane {

    Vep vep;
    public String FirstName = null;
    public String LastName = null;
    public String ID = null;

    LoginUserInterface(Vep v) {
        super(); //build border pane
        this.vep = v;//this is so that we will have access to the main scene rather than needing to open another one

        //build the vboxes and hbox which will hold all relevant fields
        VBox instructions = buildInstructions();
        VBox textFields = buildTextFields();
        HBox buttonHolder = buildLoginButton();    

        //add vboxes to relevant locations
        this.setTop(instructions);
        this.setCenter(textFields);
        this.setMargin(this.getCenter(), new Insets(0, 200, 0, 200));
        this.setBottom(buttonHolder);
    }

    public VBox buildInstructions() {
        
        VBox instructions = new VBox();
        //build explanation of instructions at the top of the page and add it into a vbox
        Text infoBox = new Text(20, 40, "Welcome to the Verifiable Election Booth! Please note that in this election process we value your anonymity. This voting process is designed to ensure that nobody can discern who you voted for. It achieves this through encryption, compartmentalization and your ability to spoil the vote, which will be explained later. For now, to begin voting please enter your first name, last name and student id below. If you do not recall your student id please contact your advisor or consult your student id card.");
        infoBox.setFont(new Font(20));
        infoBox.setWrappingWidth(600);
        infoBox.setTextAlignment(TextAlignment.CENTER);

        //format and place top explanation
        instructions.getChildren().add(infoBox);
        instructions.setAlignment(Pos.CENTER);
        instructions.setMargin(infoBox, new Insets(75, 0, 0, 0));
        
        return instructions;
    }
    
    public VBox buildTextFields() {
        VBox textFields = new VBox();
        
        //build the text fields, define their lengths and add them into a vbox
        TextField firstName = new TextField("First Name");
        firstName.setMaxWidth(300);
        firstName.setFont(new Font(20));
        FirstName = firstName.getText();
        
        TextField lastName = new TextField("Last Name");
        lastName.setMaxWidth(300);
        lastName.setFont(new Font(20));        
        LastName = lastName.getText();
        
        TextField studentId = new TextField("Student Id");
        studentId.setMaxWidth(300);
        studentId.setFont(new Font(20));
        ID = studentId.getText();
        
        
        //add to vbox and do a little formatting
        textFields.getChildren().addAll(firstName, lastName, studentId);
        textFields.setAlignment(Pos.CENTER);
        textFields.setSpacing(20);
        
        return textFields;
    }
    
    public HBox buildLoginButton() {
        HBox buttonHolder = new HBox();
        //create button for login and add to HBox. FK this is the button who's lambda you need to write to call samuelson's code
        Button loginButton = new Button("Log In");
        loginButton.setFont(new Font(20));
        loginButton.setOnMouseClicked((e) -> {
            System.out.println("name: "+ FirstName + " " + LastName);
            System.out.println("ID: " + ID);
            ArrayList data = new ArrayList();
            Vep.henrycode(ID, LastName, FirstName);
            
            this.vep.openVotingUserInterface();
           
        });
        
        //add to hbox and format
        buttonHolder.getChildren().add(loginButton);
        buttonHolder.setAlignment(Pos.CENTER);
        buttonHolder.setMargin(loginButton, new Insets(0, 0, 100, 0));
        
        return buttonHolder;
    }
}
