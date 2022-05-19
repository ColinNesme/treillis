/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.nesme.projetarchitreillis.fx;

//n et tab pour cré"er objet
import fr.insa.nesme.projetarchitreillis.terrain.Terrain;
import fr.insa.nesme.projetarchitreillis.treillis.Treillis;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author vpasiecznik01
 */
public class Interface extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            
        Treillis treillis = new Treillis();
        Terrain terrain = new Terrain();
        MainPane mainPane = new MainPane(treillis,terrain);
        
        Scene scene = new Scene(mainPane,800,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TREILLIS 2D");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
