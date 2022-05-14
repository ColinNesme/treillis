/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis.fx;

import fr.insa.nesme.projetarchitreillis.treillis.Treillis;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Elève
 */
public class MainPane extends BorderPane {

    private Controleur controleur;
    private Treillis treillis;
    
    private ZoneDessin zoneDessin;
    private HBox topBar;
    private VBox leftBox;

    private TitledPane treillisPane;
    private TitledPane terrainPane;
    private VBox vBoxTreillis;
    private VBox vBoxTerrain;
    private RadioButton bNoeudSimple;
    private RadioButton bAppuiSimple;
    private RadioButton bAppuiDouble;
    private RadioButton bBarre;
    private RadioButton bPointTerrain;

    private MenuBar menuBar;
    private Menu menuFichier;
    private Menu menuAff;
    private MenuItem menuItemNew;
    private MenuItem menuItemOpen;
    private MenuItem menuItemSave;
    private MenuItem menuItemSaveAs;
    private MenuItem menuItemClose;
    private CheckMenuItem menuItemAffId;
    private CheckMenuItem menuItemAffN;
    private CheckMenuItem menuItemAffB;
    private CheckMenuItem menuItemAffF;
    private CheckMenuItem menuItemAffG;

    private HBox toolBar;
    private Button bSelec;
    private Button bSupp;
    private Button bReiler;
    private Button bTest;

    public MainPane(Treillis treillis) throws FileNotFoundException {
        
        this.controleur =new Controleur(this);
        this.treillis = treillis;
        
        leftBox = new VBox();
        topBar = new HBox();
        zoneDessin =new ZoneDessin(this);
        //leftBox.setBackground(new Background(new BackgroundFill(Color.gray(0.2),null, null)));
        
        bNoeudSimple = new RadioButton("Noeud Simple");
        bAppuiSimple = new RadioButton("bAppuiSimple");
        bAppuiDouble = new RadioButton("bAppuiDouble");
        bBarre = new RadioButton("bBarre");
        bBarre.setOnAction((t) ->
        {this.controleur.bBarre(t);
        });
        
        ToggleGroup toggleGroup = new ToggleGroup();
        bNoeudSimple.setToggleGroup(toggleGroup);
        bAppuiSimple.setToggleGroup(toggleGroup);
        bAppuiDouble.setToggleGroup(toggleGroup);
        bBarre.setToggleGroup(toggleGroup);

        
        this.bPointTerrain = new RadioButton("PointTerrain");
        bPointTerrain.setToggleGroup(toggleGroup);

        this.vBoxTreillis = new VBox(bNoeudSimple, bAppuiSimple, bAppuiDouble, bBarre);
        this.vBoxTerrain = new VBox(bPointTerrain);

        this.treillisPane = new TitledPane("Treillis", vBoxTreillis);
        this.terrainPane = new TitledPane("Terrain", vBoxTerrain);
        leftBox.getChildren().addAll(treillisPane, terrainPane);

        this.menuFichier = new Menu("Fichier");
        this.menuAff = new Menu("Affichage");
        this.menuBar = new MenuBar(menuFichier, menuAff);

        this.menuItemNew = new MenuItem("New");
        this.menuItemOpen = new MenuItem("Open");
        this.menuItemSave = new MenuItem("Save");
        this.menuItemSaveAs = new MenuItem("Save As");
        this.menuItemClose = new MenuItem("Close");

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

        this.toolBar = new HBox();
        this.bSelec = new Button("Selection");
        this.bSupp = new Button("Supprimer");
        this.bReiler = new Button("Relierpoint");
        this.bTest = new Button("Test");
        toolBar.getChildren().addAll(bSelec, bReiler, bSupp, bTest); //addAll pour plusieurs elems
        //pane.getChildren() c'est une liste avec les avec les trucs qu'il y a dedans
        topBar.getChildren().addAll(menuBar, toolBar);

        this.setTop(topBar);
        this.setLeft(leftBox);
        this.setCenter(zoneDessin);
    }

    public void redraw() {
        this.zoneDessin.redraw();
    }
    
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

    public Button getbReiler() {
        return bReiler;
    }

    public void setbReiler(Button bReiler) {
        this.bReiler = bReiler;
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
