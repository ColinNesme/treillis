/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis.fx;

import fr.insa.nesme.projetarchitreillis.terrain.Terrain;
import fr.insa.nesme.projetarchitreillis.treillis.Treillis;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author Elève
 */
public class ZoneDessin extends Pane{
    private MainPane mainPane;
    private Canvas canvas;//l'endroit où on dessine des trucs
    

    public ZoneDessin(MainPane mainPane) {
        this.mainPane =mainPane;
        this.canvas =new Canvas(this.getWidth(),this.getHeight());//même taille que le MainPane
        canvas.setFocusTraversable(true);//l canvas ne detecte pas les touches du clavier pc comme des input si il n'est pas dans le focus (comme initialement)
        this.getChildren().add(this.canvas);
        /**
         * On associe les propriétés de taille du MainPane et du Canvas: qd le premier augment, le second aussi
         */
        this.canvas.heightProperty().bind(this.heightProperty());
        this.canvas.heightProperty().addListener((o) -> //à chaque fois que la taille change on redessine le canvas
        {this.redraw();
        });
        this.canvas.widthProperty().bind(this.widthProperty());
        this.canvas.widthProperty().addListener((o) ->
        {this.redraw();
        });
        
        this.canvas.setOnMouseClicked((t) -> {
            Controleur controleur = this.mainPane.getControleur();
            controleur.clicZoneDessin(t);
            this.canvas.requestFocus();//qd click, le canvas devient est mis en focus et tous 
        });
        
        this.canvas.setOnMouseMoved((t) -> {
            Controleur controleur = this.mainPane.getControleur();
            controleur.mouseMovedDansZoneDessin(t);
        });
        
        
        this.canvas.setOnKeyReleased((t) -> {
            if (t.getCode() ==KeyCode.SHIFT)
            {
                if (mainPane.getControleur().getEtat()==2)
                {
                    mainPane.getControleur().chgtEtat(1);
                }
            }
        });
        
        this.canvas.setOnKeyPressed((t) -> {
            if (t.getCode() ==KeyCode.ESCAPE)
            {
                if (mainPane.getControleur().getEtat()==4)
                {
                    mainPane.getControleur().chgtEtat(3);
                }
            }
        });
    }
    
    public void redraw() {
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.rgb(255,255,255));
        graphicsContext.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        Terrain terrain = this.mainPane.getTerrain();
        Treillis treillis = this.mainPane.getTreillis();
        terrain.draw(graphicsContext);
        treillis.draw(graphicsContext);
    }
    
    public MainPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(MainPane mainPane) {
        this.mainPane = mainPane;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    
    
    
}
