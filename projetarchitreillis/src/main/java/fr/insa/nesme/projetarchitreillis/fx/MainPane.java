/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis.fx;

import fr.insa.nesme.projetarchitreillis.terrain.Terrain;
import fr.insa.nesme.projetarchitreillis.treillis.Treillis;
import java.io.FileNotFoundException;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Elève
 */
public class MainPane extends BorderPane {

    
    //Tout ce qu'il y a dans le MainPane (parents et nodes par exemple) sont des attributs : on peut les recuperer dans d'autres classes
    private Controleur controleur;
    private Treillis treillis;
    private Terrain terrain;

    private ZoneDessin zoneDessin;
    private HBox topBar;
    private VBox leftBox;

    //CONTENU DE LA VBOX LEFTBOX:
    private TitledPane treillisPane;//TitledPane: Sous-Classe de Pane, c'est un panneau minimisable
    private TitledPane terrainPane;
    private VBox vBoxTreillis;
    private VBox vBoxTerrain;
    private RadioButton bNoeudSimple;//RadioButton: Sous-classe de ToggleButton avec des cercles cochables
    private RadioButton bAppuiSimple;
    private RadioButton bAppuiDouble;
    private RadioButton bBarre;
    private RadioButton bPointTerrain;

    //CONTENU DE LA HBOX TOPBAR:
    private MenuBar menuBar;//contient les deux menus suivants
    private Menu menuFichier;
    private Menu menuAff;
    private MenuItem menuItemNew;
    private MenuItem menuItemOpen;
    private MenuItem menuItemSave;
    private MenuItem menuItemSaveAs;
    private MenuItem menuItemClose;
    private CheckMenuItem menuItemAffId;//menuItem decochables
    private CheckMenuItem menuItemAffN;
    private CheckMenuItem menuItemAffB;
    private CheckMenuItem menuItemAffF;
    private CheckMenuItem menuItemAffG;
    
    private HBox toolBar;//contient les boutons suivants
    private Button bSelec;
    private Button bSupp;
    private Button bTest;

    public MainPane(Treillis treillis,Terrain terrain) {
        
        Color mainColor = Color.rgb(147, 111, 217, 1.0); //green : rgb(95,160,113) purple :rgb(147, 111, 217) peche: rgb(255,159,66) blue : rgb(98,119,255)

        this.controleur = new Controleur(this);
        this.treillis = treillis;
        this.terrain = terrain;

        leftBox = new VBox();
        topBar = new HBox();
        zoneDessin = new ZoneDessin(this);

        bNoeudSimple = new RadioButton("Noeud Simple");
        bAppuiSimple = new RadioButton("Appui Simple");
        bAppuiDouble = new RadioButton("Appui Double");
        bBarre = new RadioButton("Barre");
        bBarre.setOnAction((t)
                -> {
            this.controleur.bBarre(t);
        });
        bNoeudSimple.setOnAction((t)
                -> {
            this.controleur.bNoeudSimple(t);
        });
        bAppuiSimple.setOnAction((t) -> {
            this.controleur.bAppuiSimple(t);
        });
        bAppuiDouble.setOnAction((t) -> {
            this.controleur.bAppuiDouble(t);
        });

        ToggleGroup toggleGroup = new ToggleGroup();//ToggleGroup: On ne peut selectionner qu'un RadioButton parmis un ToggleGroup
        bNoeudSimple.setToggleGroup(toggleGroup);
        bAppuiSimple.setToggleGroup(toggleGroup);
        bAppuiDouble.setToggleGroup(toggleGroup);
        bBarre.setToggleGroup(toggleGroup);

        this.bPointTerrain = new RadioButton("Point Terrain");
        bPointTerrain.setToggleGroup(toggleGroup);
        bPointTerrain.setOnAction((t) -> {
            this.controleur.bPointTerrain(t);
        });

        //ce qui est dans les menus ouvrables
        this.vBoxTreillis = new VBox(bNoeudSimple, bAppuiSimple, bAppuiDouble, bBarre);
        this.vBoxTerrain = new VBox(bPointTerrain);

        this.treillisPane = new TitledPane("TREILLIS", vBoxTreillis);
        this.terrainPane = new TitledPane("TERRAIN", vBoxTerrain);
        leftBox.getChildren().addAll(treillisPane, terrainPane);

        this.menuFichier = new Menu("Fichier");
        this.menuAff = new Menu("Affichage");
        this.menuBar = new MenuBar(menuFichier, menuAff);

        this.menuItemNew = new MenuItem("Nouveau");
        this.menuItemOpen = new MenuItem("Ouvrir");
        this.menuItemSave = new MenuItem("Enregistrer");
        this.menuItemSaveAs = new MenuItem("Enregistrer sous");
        this.menuItemClose = new MenuItem("Fermer");

        this.menuItemAffId = new CheckMenuItem("Afficher les identifiants");
        this.menuItemAffN = new CheckMenuItem("Afficher les noeuds");
        this.menuItemAffB = new CheckMenuItem("Afficher les barres");
        this.menuItemAffF = new CheckMenuItem("Afficher les forces");
        this.menuItemAffG = new CheckMenuItem("Afficher la grille");
        menuItemAffId.setSelected(true); //on met par défaut coché
        menuItemAffN.setSelected(true);
        menuItemAffB.setSelected(true);
        menuItemAffF.setSelected(true);
        menuItemAffG.setSelected(true);
        
        
        menuFichier.getItems().addAll(menuItemNew, menuItemOpen, new SeparatorMenuItem(), menuItemSave, menuItemSaveAs, new SeparatorMenuItem(), menuItemClose);
        menuAff.getItems().addAll(menuItemAffId, menuItemAffN, menuItemAffB, menuItemAffF, menuItemAffG);

        this.toolBar = new HBox();
        this.bSelec = new Button("Selection");
        this.bSupp = new Button("Supprimer");
        bSupp.setOnAction((t) -> {
            this.controleur.bSupp(t);
        });
        this.bTest = new Button("Test");

        final Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);

        toolBar.getChildren().addAll(bSelec,  bSupp, separator, bTest); //addAll pour ajouter plusieurs elems dans la HBox
        //Pane.getChildren() c'est une liste avec les avec les trucs qu'il y a dedans
        topBar.getChildren().addAll(menuBar, toolBar);

        this.setTop(topBar);
        this.setLeft(leftBox);
        this.setCenter(zoneDessin);

        
        
        ///////////////////////////////TAILLE DES BOUTONS
        
        bNoeudSimple.setPrefSize(144, 25);
        bAppuiSimple.setPrefSize(144, 25);
        bAppuiDouble.setPrefSize(144, 25);
        bBarre.setPrefSize(144, 25);

        bSelec.setPrefSize(100, 35);
        bSupp.setPrefSize(100, 35);
        bTest.setPrefSize(100, 35);

        ///////////////////////////// COULEURS DE FOND
        
         //TOPBAR
         
        topBar.setBackground(new Background(new BackgroundFill(mainColor.deriveColor(1, 1, 0.7, 1), null, null)));
        
        //menuBar.setStyle("-fx-background-color: rebeccapurple ");
        //menuBar.setStyle("-fx-invertedcolor");

        bSelec.setStyle("-fx-base:rgb(147, 111, 217);-fx-font-size:15;-fx-focus-color:blue;-fx-text-fill:white");
        bSupp.setStyle("-fx-base:rgb(147, 111, 217);-fx-font-size:15;-fx-focus-color:blue;-fx-text-fill:white");
        bTest.setStyle("-fx-base:rgb(147, 111, 217);-fx-font-size:15;-fx-focus-color:blue;-fx-text-fill:white");
        
        //LEFTBOX
        
        leftBox.setBackground(new Background(new BackgroundFill(mainColor.deriveColor(1, 1, 0.7, 1), null, null)));
        
        vBoxTreillis.setBackground(new Background(new BackgroundFill(mainColor.deriveColor(1, 1, 1, 1), null, null)));
        vBoxTerrain.setBackground(new Background(new BackgroundFill(mainColor.deriveColor(1, 1, 1, 1), null, null)));
       
        
        
        /////////////////////////////////////////// POLICE D'ECRITURE BOUTONS 
        
        
        
       // menuBar.setStyle("-fx-textfill-color: blue;");
        treillisPane.setStyle("-fx-background-color: softpurple;");
        bNoeudSimple.setStyle("-fx-font-size:15;-fx-text-fill:white");
        bAppuiDouble.setStyle("-fx-font-size:15;-fx-text-fill:white");
        bAppuiSimple.setStyle("-fx-font-size:15;-fx-text-fill:white");
        bBarre.setStyle("-fx-font-size:15;-fx-text-fill:white");
        bPointTerrain.setStyle("-fx-font-size:15;-fx-text-fill:white");


        menuFichier.setStyle("-fx-font-size:15");
        menuAff.setStyle("-fx-font-size:15");
    }

    public void redraw() {
        this.zoneDessin.redraw();
    }
    
    
    
    
    
    
    
    /////////////////////////////////////////////////////GETTERS & SETTERS

    public Controleur getControleur() {
        return controleur;
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public HBox getTopBar() {
        return topBar;
    }

    public void setTopBar(HBox topBar) {
        this.topBar = topBar;
    }

    public VBox getLeftBox() {
        return leftBox;
    }

    public void setLeftBox(VBox leftBox) {
        this.leftBox = leftBox;
    }

    public TitledPane getTreillisPane() {
        return treillisPane;
    }

    public void setTreillisPane(TitledPane treillisPane) {
        this.treillisPane = treillisPane;
    }

    public TitledPane getTerrainPane() {
        return terrainPane;
    }

    public void setTerrainPane(TitledPane terrainPane) {
        this.terrainPane = terrainPane;
    }

    public VBox getvBoxTreillis() {
        return vBoxTreillis;
    }

    public void setvBoxTreillis(VBox vBoxTreillis) {
        this.vBoxTreillis = vBoxTreillis;
    }

    public VBox getvBoxTerrain() {
        return vBoxTerrain;
    }

    public void setvBoxTerrain(VBox vBoxTerrain) {
        this.vBoxTerrain = vBoxTerrain;
    }

    public RadioButton getbNoeudSimple() {
        return bNoeudSimple;
    }

    public void setbNoeudSimple(RadioButton bNoeudSimple) {
        this.bNoeudSimple = bNoeudSimple;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }



    public RadioButton getbAppuiSimple() {
        return bAppuiSimple;
    }

    public void setbAppuiSimple(RadioButton bAppuiSimple) {
        this.bAppuiSimple = bAppuiSimple;
    }

    public RadioButton getbAppuiDouble() {
        return bAppuiDouble;
    }

    public void setbAppuiDouble(RadioButton bAppuiDouble) {
        this.bAppuiDouble = bAppuiDouble;
    }

    public RadioButton getbBarre() {
        return bBarre;
    }

    public void setbBarre(RadioButton bBarre) {
        this.bBarre = bBarre;
    }

    public RadioButton getbPointTerrain() {
        return bPointTerrain;
    }

    public void setbPointTerrain(RadioButton bPointTerrain) {
        this.bPointTerrain = bPointTerrain;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public Menu getMenuFichier() {
        return menuFichier;
    }

    public void setMenuFichier(Menu menuFichier) {
        this.menuFichier = menuFichier;
    }

    public Menu getMenuAff() {
        return menuAff;
    }

    public void setMenuAff(Menu menuAff) {
        this.menuAff = menuAff;
    }

    public MenuItem getMenuItemNew() {
        return menuItemNew;
    }

    public void setMenuItemNew(MenuItem menuItemNew) {
        this.menuItemNew = menuItemNew;
    }

    public MenuItem getMenuItemOpen() {
        return menuItemOpen;
    }

    public void setMenuItemOpen(MenuItem menuItemOpen) {
        this.menuItemOpen = menuItemOpen;
    }

    public MenuItem getMenuItemSave() {
        return menuItemSave;
    }

    public void setMenuItemSave(MenuItem menuItemSave) {
        this.menuItemSave = menuItemSave;
    }

    public MenuItem getMenuItemSaveAs() {
        return menuItemSaveAs;
    }

    public void setMenuItemSaveAs(MenuItem menuItemSaveAs) {
        this.menuItemSaveAs = menuItemSaveAs;
    }

    public MenuItem getMenuItemClose() {
        return menuItemClose;
    }

    public void setMenuItemClose(MenuItem menuItemClose) {
        this.menuItemClose = menuItemClose;
    }

    public CheckMenuItem getMenuItemAffId() {
        return menuItemAffId;
    }

    public void setMenuItemAffId(CheckMenuItem menuItemAffId) {
        this.menuItemAffId = menuItemAffId;
    }

    public CheckMenuItem getMenuItemAffN() {
        return menuItemAffN;
    }

    public void setMenuItemAffN(CheckMenuItem menuItemAffN) {
        this.menuItemAffN = menuItemAffN;
    }

    public CheckMenuItem getMenuItemAffB() {
        return menuItemAffB;
    }

    public void setMenuItemAffB(CheckMenuItem menuItemAffB) {
        this.menuItemAffB = menuItemAffB;
    }

    public CheckMenuItem getMenuItemAffF() {
        return menuItemAffF;
    }

    public void setMenuItemAffF(CheckMenuItem menuItemAffF) {
        this.menuItemAffF = menuItemAffF;
    }

    public CheckMenuItem getMenuItemAffG() {
        return menuItemAffG;
    }

    public void setMenuItemAffG(CheckMenuItem menuItemAffG) {
        this.menuItemAffG = menuItemAffG;
    }

    public HBox getToolBar() {
        return toolBar;
    }

    public void setToolBar(HBox toolBar) {
        this.toolBar = toolBar;
    }

    public Button getbSelec() {
        return bSelec;
    }

    public void setbSelec(Button bSelec) {
        this.bSelec = bSelec;
    }

    public Button getbSupp() {
        return bSupp;
    }

    public void setbSupp(Button bSupp) {
        this.bSupp = bSupp;
    }

    public Button getbTest() {
        return bTest;
    }

    public void setbTest(Button bTest) {
        this.bTest = bTest;
    }

    public ZoneDessin getZoneDessin() {
        return zoneDessin;
    }

    public void setZoneDessin(ZoneDessin zoneDessin) {
        this.zoneDessin = zoneDessin;
    }

    public Treillis getTreillis() {
        return treillis;
    }

    public void setTreillis(Treillis treillis) {
        this.treillis = treillis;
    }

}
