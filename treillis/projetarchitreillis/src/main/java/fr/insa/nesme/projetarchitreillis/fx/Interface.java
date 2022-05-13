/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.nesme.projetarchitreillis.fx;

//n et tab pour cré"er objet
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

        BorderPane mainPane = new BorderPane();
        HBox toolBar = new HBox();
        VBox LeftBox = new VBox();
        LeftBox.setBorder(new Border(new BorderStroke(new Color(0, 0, 0, 0.2), BorderStrokeStyle.SOLID, new CornerRadii(0.1), BorderWidths.DEFAULT)));
        Pane ZoneDessin = new Pane();

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton bNoeudSimple = new RadioButton("NoeudSimple");
        bNoeudSimple.setToggleGroup(toggleGroup);
        RadioButton bAppuiSimple = new RadioButton("AppuiSimple");
        bAppuiSimple.setToggleGroup(toggleGroup);
        RadioButton bAppuiDouble = new RadioButton("AppuiDouble");
        bAppuiDouble.setToggleGroup(toggleGroup);
        RadioButton bBarre = new RadioButton("Barre");
        bBarre.setToggleGroup(toggleGroup);

        RadioButton bPointTerrain = new RadioButton("PointTerrain");
        bPointTerrain.setToggleGroup(toggleGroup);

        VBox vBoxTreillis = new VBox(bNoeudSimple, bAppuiSimple, bAppuiDouble, bBarre);
        VBox vBoxTerrain = new VBox(bPointTerrain);

        TitledPane treillisPane = new TitledPane("Treillis", vBoxTreillis);
        TitledPane terrainPane = new TitledPane("Terrain", vBoxTerrain);
        LeftBox.getChildren().addAll(treillisPane, terrainPane);

        Menu menuFichier = new Menu("Fichier");
        Menu menuAff = new Menu("Affichage");
        MenuBar menuBar = new MenuBar(menuFichier, menuAff);

        MenuItem menuItemNew = new MenuItem("New");
        MenuItem menuItemOpen = new MenuItem("Open");
        MenuItem menuItemSave = new MenuItem("Save");
        MenuItem menuItemSaveAs = new MenuItem("Save As");
        MenuItem menuItemClose = new MenuItem("Close");

        CheckMenuItem menuItemAffId = new CheckMenuItem("Afficher les identifiants");
        CheckMenuItem menuItemAffN = new CheckMenuItem("Afficher les noeuds");
        CheckMenuItem menuItemAffB = new CheckMenuItem("Afficher les barres");
        CheckMenuItem menuItemAffF = new CheckMenuItem("Afficher les forces");
        CheckMenuItem menuItemAffG = new CheckMenuItem("Afficher la grille");
        menuItemAffId.setSelected(true); //on met par défaut coché
        menuItemAffN.setSelected(true);
        menuItemAffB.setSelected(true);
        menuItemAffF.setSelected(true);
        menuItemAffG.setSelected(true);

        Image imageSave = new Image(new FileInputStream("C:/Users/Elève/Downloads/save.png"));
        Image imageNew = new Image(new FileInputStream("C:/Users/Elève/Downloads/new.png"));
        Image imageClose = new Image(new FileInputStream("C:/Users/Elève/Downloads/close.png"));
        Image imageSaveAs = new Image(new FileInputStream("C:/Users/Elève/Downloads/save_as.png"));
        Image imageOpen = new Image(new FileInputStream("C:/Users/Elève/Downloads/open.png"));

        ImageView imageViewSave = new ImageView(imageSave);
        ImageView imageViewNew = new ImageView(imageNew);
        ImageView imageViewClose = new ImageView(imageClose);
        ImageView imageViewSaveAs = new ImageView(imageSaveAs);
        ImageView imageViewOpen = new ImageView(imageOpen);
        menuItemSave.setGraphic(imageViewSave);
        menuItemNew.setGraphic(imageViewNew);
        menuItemClose.setGraphic(imageViewClose);
        menuItemSaveAs.setGraphic(imageViewSaveAs);
        menuItemOpen.setGraphic(imageViewOpen);

        menuFichier.getItems().addAll(menuItemNew, menuItemOpen, new SeparatorMenuItem(), menuItemSave, menuItemSaveAs, new SeparatorMenuItem(), menuItemClose);
        menuAff.getItems().addAll(menuItemAffId, menuItemAffN, menuItemAffB, menuItemAffF, menuItemAffG);

        HBox hBox = new HBox();
        Button button1 = new Button("Selection");
        Button button2 = new Button("Supprimer");
        Button button3 = new Button("Relierpoint");
        Button button4 = new Button("Test");
        hBox.getChildren().addAll(button1, button2, button3, button4); //addAll pour plusieurs elems
        //pane.getChildren() c'est une liste avec les avec les trucs qu'il y a dedans
        toolBar.getChildren().addAll(menuBar, hBox);

        mainPane.setTop(toolBar);
        mainPane.setLeft(LeftBox);
        mainPane.setCenter(ZoneDessin);
        
        

        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TREILLIS 2D");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
