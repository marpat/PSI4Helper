/*
 * PsiHelper.java, part of the PsiHelper project
 * http://chemgplus.blogspot.com/
 */
package psihelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Marcel Patek <chemgplus at gmail.com>
 */
public class PsiHelper extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Psihelper.css").toExternalForm());
        // icon change here
        stage.getIcons().add(new Image("img/resources/psi4icoY.png"));
        stage.setTitle("PSI4 Helper");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
