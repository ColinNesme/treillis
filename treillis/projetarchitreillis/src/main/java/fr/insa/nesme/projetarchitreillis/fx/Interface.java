/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.nesme.projetarchitreillis.fx;

//n et tab pour cré"er objet
import fr.insa.nesme.projetarchitreillis.treillis.Noeud;
import fr.insa.nesme.projetarchitreillis.treillis.Treillis;
import java.io.FileInputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author vpasiecznik01
 */
public class Interface extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            
        Treillis treillis = new Treillis();
        MainPane mainPane = new MainPane(treillis);
        
        Scene scene = new Scene(mainPane,800,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TREILLIS 2D");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
