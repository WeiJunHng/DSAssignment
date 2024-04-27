/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hackingthefuture;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class LoginPageController implements Initializable {

    @FXML
    private VBox leftBox, rightBox;

    @FXML
    private Button btn, btn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn.setOnAction(event -> {
            
            System.out.println(btn.getTranslateX());

            Timeline switchAnimation = new Timeline(
                    new KeyFrame(Duration.seconds(0.25),
                            new KeyValue(leftBox.styleProperty(), "-fx-background-color: #b99c7e;"),
                            new KeyValue(rightBox.styleProperty(), "-fx-background-color: linear-gradient(to right, #4962a3, #9bbdff);")
                    ),
                    new KeyFrame(Duration.seconds(0.5),
                            new KeyValue(leftBox.translateXProperty(), 940 - leftBox.translateXProperty().get()),
                            new KeyValue(rightBox.translateXProperty(), -600 - rightBox.translateXProperty().get()),
                            new KeyValue(btn1.translateXProperty(), 600 - btn1.translateXProperty().get())
                    )
            );
            
            
            switchAnimation.setOnFinished(e -> {
                System.out.println(btn.translateXProperty().get());
                btn1.setVisible(!btn1.visibleProperty().get());
                System.out.println(leftBox.viewOrderProperty());
                System.out.println(rightBox.viewOrderProperty());
            });

            switchAnimation.play();
        });
    }
}
