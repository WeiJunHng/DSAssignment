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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class LoginPageController implements Initializable {

    @FXML
    private ToggleGroup roleToggleGroup;

    @FXML
    private VBox registerPage, kinRegisterPage;

    @FXML
    private HBox loginPage, imageAndRegisterFormContainer, registerCombinedPage;

    @FXML
    private ImageView moving_image, roleImageView, kinRoleImageView;

    @FXML
    private AnchorPane moving_pane;

    @FXML
    private Hyperlink switch_register_link, switch_login_link, switch_kin_link,
            kin_switch_register_link, kin_switch_login_link;

    @FXML
    private VBox registerForm, kinInformationBox, kinRegisterKinInformationBox;

    @FXML
    private TextField loginEmailTF, loginPasswordTF,
            registerEmailTF, registerUsernameTF, registerPasswordTF, registerConfirmPasswordTF, registerKinEmailTF;

    @FXML
    private TextField kinRegisterEmailTF, kinRegisterUsernameTF, kinRegisterPasswordTF, kinRegisterConfirmPasswordTF, kinRegisterKinInfoTF;

    @FXML
    private PasswordField loginPasswordPF,
            registerPasswordPF, registerConfirmPasswordPF,
            kinRegisterPasswordPF, kinRegisterConfirmPasswordPF;

    @FXML
    private CheckBox loginShowPwCB, registerShowPwCB, kinRegisterShowPwCB;

    @FXML
    private Button registerBtn, kinRegisterBtn;

    @FXML
    private Label kinRegisterPromptLabel;

    private Pane separatePane;

    private Image[] roleImages = {new Image("/Resources/Images/student_img.png"), new Image("/Resources/Images/parent_img.png"), new Image("/Resources/Images/educator_img.png")};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        moving_pane.setClip(new Rectangle(700, 800));
        moving_pane.setVisible(true);
        loginPage.setVisible(true);
        registerPage.setVisible(false);
        kinRegisterPage.setVisible(false);
        registerCombinedPage.setVisible(true);

        ((StackPane) registerPage.getParent()).getChildren().removeAll(registerPage, kinRegisterPage);
        registerCombinedPage.getChildren().addAll(registerPage, kinRegisterPage);
        System.out.println(registerCombinedPage.getWidth());
        System.out.println(registerCombinedPage.getHeight());

        separatePane = (Pane) registerForm.getChildren().get(registerForm.getChildren().size() - 2);

        resetLoginPage();
        resetRegisterPage();
        registerForm.getChildren().remove(kinInformationBox);

        // Bind text property for PasswordField and TextField
        loginPasswordPF.textProperty().bindBidirectional(loginPasswordTF.textProperty());
        registerPasswordPF.textProperty().bindBidirectional(registerPasswordTF.textProperty());
        registerConfirmPasswordPF.textProperty().bindBidirectional(registerConfirmPasswordTF.textProperty());
        kinRegisterPasswordPF.textProperty().bindBidirectional(kinRegisterPasswordTF.textProperty());
        kinRegisterConfirmPasswordPF.textProperty().bindBidirectional(kinRegisterConfirmPasswordTF.textProperty());
        
        kinRegisterKinInfoTF.textProperty().bind(registerEmailTF.textProperty());

        // Show password
        loginShowPwCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            loginPasswordPF.setVisible(!newValue);
            loginPasswordTF.setVisible(newValue);
        });

        registerShowPwCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            registerPasswordPF.setVisible(!newValue);
            registerPasswordTF.setVisible(newValue);
            registerConfirmPasswordPF.setVisible(!newValue);
            registerConfirmPasswordTF.setVisible(newValue);
        });
        
        kinRegisterShowPwCB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            kinRegisterPasswordPF.setVisible(!newValue);
            kinRegisterPasswordTF.setVisible(newValue);
            kinRegisterConfirmPasswordPF.setVisible(!newValue);
            kinRegisterConfirmPasswordTF.setVisible(newValue);
        });

        // Role chosen
        roleToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                registerForm.getChildren().remove(kinInformationBox);
                return;
            }
            RadioButton selectedRadioButton = (RadioButton) newValue;
            ObservableList<Node> list = ((HBox) selectedRadioButton.getParent()).getChildren();
            int choiceIndex = list.indexOf(selectedRadioButton);
            roleImageView.setImage(roleImages[choiceIndex]);

            // Determine to show kin's information box or not
            if (choiceIndex < 2) {
                ((Label) kinInformationBox.getChildren().get(1)).setText("Email Address of Your " + (choiceIndex == 0 ? "Parent" : "Child"));
                ((Label) kinRegisterKinInformationBox.getChildren().get(1)).setText("Email Address of " + (choiceIndex == 1 ? "Parent" : "Child"));
                kinRegisterPromptLabel.setText("Register " +  (choiceIndex == 0 ? "Parent" : "Child") + " to Proceed");
                kinRoleImageView.setImage(roleImages[(choiceIndex + 1) % 2]);
                if (oldValue == null || list.indexOf(oldValue) == 2) {
                    separatePane.setPrefHeight(0);
                    registerForm.getChildren().add(registerForm.getChildren().size() - 1, kinInformationBox);
                }
                clearKinRegisterPage();
            } else {
                separatePane.setPrefHeight(35);
                registerForm.getChildren().remove(kinInformationBox);
            }

            // Show role image with animation
            if (oldValue == null) {
                roleImageView.setVisible(true);
                Timeline switchAnimation = new Timeline(
                        new KeyFrame(Duration.seconds(0.5),
                                new KeyValue(imageAndRegisterFormContainer.translateXProperty(), 0),
                                new KeyValue(roleImageView.translateXProperty(), 0)
                        )
                );
                switchAnimation.play();
            }
        });

        // Switch to Register Page
        switch_register_link.setOnAction(event -> {
            switch_register_link.setDisable(true);

            moving_image.setImage(new Image("/Resources/Images/login_bg.png"));

            Timeline switchAnimation = new Timeline(
                    new KeyFrame(Duration.seconds(0.3),
                            new KeyValue(loginPage.visibleProperty(), false)
                    ),
                    new KeyFrame(Duration.seconds(0.4),
                            new KeyValue(registerPage.visibleProperty(), true)
                    ),
                    new KeyFrame(Duration.seconds(0.5),
                            new KeyValue(moving_image.imageProperty(), new Image("/Resources/Images/register_bg.png"))
                    ),
                    new KeyFrame(Duration.seconds(0.6),
                            new KeyValue(loginPage.translateXProperty(), -400)
                    ),
                    new KeyFrame(Duration.seconds(0.8),
                            new KeyValue(registerPage.translateXProperty(), 0)
                    ),
                    new KeyFrame(Duration.seconds(1),
                            new KeyValue(moving_pane.translateXProperty(), 1030 - moving_pane.getTranslateX()),
                            new KeyValue(moving_image.translateXProperty(), -1030 - moving_image.getTranslateX())
                    )
            );

            switchAnimation.setOnFinished(e -> {
                switch_register_link.setDisable(false);
                resetLoginPage();
            });

            switchAnimation.play();
        });

        // Switch to Login Page
        switch_login_link.setOnAction(event -> {
            switch_login_link.setDisable(true);

            moving_image.setImage(new Image("/Resources/Images/register_bg.png"));

            Timeline switchAnimation = new Timeline(
                    new KeyFrame(Duration.seconds(0.47),
                            new KeyValue(loginPage.visibleProperty(), true)
                    ),
                    new KeyFrame(Duration.seconds(0.5),
                            new KeyValue(moving_image.imageProperty(), new Image("/Resources/Images/login_bg.png")),
                            new KeyValue(registerPage.visibleProperty(), false)
                    ),
                    new KeyFrame(Duration.seconds(0.55),
                            new KeyValue(registerPage.translateXProperty(), 400)
                    ),
                    new KeyFrame(Duration.seconds(0.8),
                            new KeyValue(loginPage.translateXProperty(), 0)
                    ),
                    new KeyFrame(Duration.seconds(1),
                            new KeyValue(moving_pane.translateXProperty(), 1030 - moving_pane.getTranslateX()),
                            new KeyValue(moving_image.translateXProperty(), -1030 - moving_image.getTranslateX())
                    )
            );

            switchAnimation.setOnFinished(e -> {
                switch_login_link.setDisable(false);
                resetRegisterPage();
            });

            switchAnimation.play();
        });

        switch_kin_link.setOnAction(event -> switchKinRegisterAnimation(true));

        kin_switch_register_link.setOnAction(event -> switchKinRegisterAnimation(false));
        
        kin_switch_login_link.setOnAction(event -> {
            kin_switch_login_link.setDisable(true);

            moving_image.setImage(new Image("/Resources/Images/register_bg.png"));

            Timeline switchAnimation = new Timeline(
                    new KeyFrame(Duration.seconds(0.47),
                            new KeyValue(loginPage.visibleProperty(), true)
                    ),
                    new KeyFrame(Duration.seconds(0.5),
                            new KeyValue(moving_image.imageProperty(), new Image("/Resources/Images/login_bg.png")),
                            new KeyValue(kinRegisterPage.visibleProperty(), false)
                    ),
                    new KeyFrame(Duration.seconds(0.55),
                            new KeyValue(kinRegisterPage.translateXProperty(), 400)
                    ),
                    new KeyFrame(Duration.seconds(0.8),
                            new KeyValue(loginPage.translateXProperty(), 0)
                    ),
                    new KeyFrame(Duration.seconds(1),
                            new KeyValue(moving_pane.translateXProperty(), 1030 - moving_pane.getTranslateX()),
                            new KeyValue(moving_image.translateXProperty(), -1030 - moving_image.getTranslateX())
                    )
            );

            switchAnimation.setOnFinished(e -> {
                kin_switch_login_link.setDisable(false);
                resetRegisterPage();
            });

            switchAnimation.play();
        });
    }

    private void switchKinRegisterAnimation(boolean left) {
        switch_login_link.setDisable(true);
        switch_kin_link.setDisable(true);
        kin_switch_register_link.setDisable(true);
        kin_switch_login_link.setDisable(true);

        kinRegisterPage.setVisible(left);
        registerPage.setVisible(!left);

        Timeline switchAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.9),
                        new KeyValue(registerCombinedPage.translateXProperty(), -registerPage.getWidth() - registerCombinedPage.getTranslateX())
                )
        );

        switchAnimation.setOnFinished(e -> {
            switch_login_link.setDisable(false);
            switch_kin_link.setDisable(false);
            kin_switch_register_link.setDisable(false);
            kin_switch_login_link.setDisable(false);
        });

        switchAnimation.play();

    }

    private void resetLoginPage() {
        loginEmailTF.clear();
        loginPasswordPF.clear();
        loginShowPwCB.setSelected(false);
    }

    private void resetRegisterPage() {
        registerEmailTF.clear();
        registerUsernameTF.clear();
        registerPasswordPF.clear();
        registerConfirmPasswordPF.clear();
        registerKinEmailTF.clear();
        registerShowPwCB.setSelected(false);
        roleToggleGroup.selectToggle(null);
        
        clearKinRegisterPage();

        separatePane.setPrefHeight(35);
        registerPage.setTranslateX(400);
        kinRegisterPage.setTranslateX(0);
        imageAndRegisterFormContainer.setTranslateX(-350);
        registerCombinedPage.setTranslateX(0);
        roleImageView.setTranslateX(-40);
        roleImageView.setVisible(false);
    }
    
    private void clearKinRegisterPage(){
        kinRegisterEmailTF.clear();
        kinRegisterUsernameTF.clear();
        kinRegisterPasswordPF.clear();
        kinRegisterConfirmPasswordPF.clear();
        kinRegisterShowPwCB.setSelected(false);
    }
}
