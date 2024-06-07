/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hackingthefuture;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ProfileController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private VBox navBar;

    @FXML
    private Button menuBtn;

    private Button currentBarBtn;
    private ObservableList navBarBtns;
    private String[] barBtnContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        currentBarBtn = null;
        navBarBtns = navBar.getChildren();
        barBtnContent = new String[navBarBtns.size()];
        for (int i = 0; i < navBarBtns.size(); i++) {
            Button btn = (Button) navBarBtns.get(i);
            barBtnContent[i] = btn.getText();
            btn.setOnAction(event -> changeSelectedBtn(event));
            btn.setMaxWidth(65);
            btn.setPrefHeight(50);
        }
        menuBtn.setOnAction(e -> expand(e));
        navBar.setMaxWidth(65);

        expand(null);

        System.out.println(menuBtn.getMaxWidth());
        System.out.println(menuBtn.prefWidthProperty());
        System.out.println(navBar.getMaxWidth());
    }

    private void changeSelectedBtn(ActionEvent event) {
        Button btnClicked = (Button) event.getSource();
        if (!btnClicked.equals(currentBarBtn)) {
            if (currentBarBtn != null) {
                currentBarBtn.setId("");
            }
            currentBarBtn = btnClicked;
            currentBarBtn.setId("navBarBtnSelected");
        }
    }

    private void expand(ActionEvent event) {
        menuBtn.setDisable(true);
        Timeline switchAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(navBar.maxWidthProperty(), 350 - navBar.maxWidthProperty().get())
                )
        );

        navBarBtns.forEach(item -> {
            Button btn = (Button) item;
            switchAnimation.getKeyFrames().addAll(
                    new KeyFrame(Duration.seconds(0.25),
                            new KeyValue(btn.textProperty(), btn.getMaxWidth() > 100 ? "" : barBtnContent[navBarBtns.indexOf(item)])
                    ),
                    new KeyFrame(Duration.seconds(0.5),
                            new KeyValue(btn.maxWidthProperty(), 350 - btn.maxWidthProperty().get())
                    ));
        });
        
        switchAnimation.setOnFinished(eh->menuBtn.setDisable(false));

        switchAnimation.play();
    }

}
