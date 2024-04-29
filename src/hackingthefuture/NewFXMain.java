/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package hackingthefuture;

import com.sun.tools.javac.Main;
import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Asus
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Login Page");
            stage.setScene(scene);
            stage.setMaximized(true);
            //stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(hackingthefuture.NewFXMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }
    
    public void test(Stage stage) {
        Image image = new Image("file:////C:/Users/Asus/Downloads/wallpaper.png/");

        // Create an ImageView
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(image.getWidth());
        imageView.setFitHeight(image.getHeight());
        imageView.setClip(new Rectangle(500,500));

        // Define the initial viewport (full image)
        Rectangle2D initialViewport = new Rectangle2D(0, 0, image.getWidth(), image.getHeight());
        imageView.setViewport(initialViewport);

        // Define the final viewport (cropped area)
        Rectangle2D finalViewport = new Rectangle2D(1000, 1000, 200, 200);

        // Calculate translation distances
        // Define the change in viewport position
        double deltaX = 100; // Change in X position
        double deltaY = 0;   // Change in Y position

        // Update the viewport's position
        imageView.setOnMouseClicked(event -> {
            imageView.setViewport(new Rectangle2D(
                    imageView.getViewport().getMinX() + deltaX,
                    imageView.getViewport().getMinY() + deltaY,
                    image.getWidth(),
                    image.getHeight()
            ));
        });


        // Create a StackPane and add the ImageView
        HBox root = new HBox(imageView);
        root.setMaxSize(500, 500);
        root.setStyle("-fx-background-color: white;");

        // Create a Scene
        Scene scene = new Scene(root);

        // Set the Scene to the Stage
        stage.setScene(scene);
        stage.setTitle("Cropping Animation");
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
