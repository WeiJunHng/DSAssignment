/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hackingthefuture;

import java.net.URL;
import java.util.ResourceBundle;
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

    private Button currentBarBtn;
    private ObservableList navBarBtns;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        currentBarBtn = null;
        navBarBtns = navBar.getChildren();
        for (int i = 0; i < navBarBtns.size(); i++) {
            Button btn = (Button) navBarBtns.get(i);
            btn.setOnAction(event -> changeSelectedBtn(event));
        }
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

}
