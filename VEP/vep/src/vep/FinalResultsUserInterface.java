/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vep;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author shinson
 */
public class FinalResultsUserInterface extends BorderPane{
     
    FinalResultsUserInterface(String victor) {
        super();
        
        HBox imagesHBox = this.buildImageHBox();
        
    }
    
    private static HBox buildImageHBox() {
        HBox imagesHBox = new HBox();
        
        return imagesHBox;
    }
    
}
