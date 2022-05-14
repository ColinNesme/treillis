/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis.fx;

import fr.insa.nesme.projetarchitreillis.treillis.Treillis;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author Elève
 */
public class ZoneDessin extends Pane{
    
    private MainPane mainPane;
    private Canvas canvas;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    

    public ZoneDessin(MainPane mainPane) {
        this.mainPane =mainPane;
        this.canvas =new Canvas(this.getWidth(),this.getHeight());
        canvas.setFocusTraversable(true);
        this.getChildren().add(this.canvas);
        this.canvas.heightProperty().bind(this.heightProperty());
        this.canvas.heightProperty().addListener((o) ->
        {this.redraw();
        });
        this.canvas.widthProperty().bind(this.widthProperty());
        this.canvas.widthProperty().addListener((o) ->
        {this.redraw();
        });
        
        this.canvas.setOnMouseClicked((t) -> {
            Controleur controleur = this.mainPane.getControleur();
            controleur.clicZoneDessin(t);
            this.canvas.requestFocus();
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
    }
    
    public void redraw() {
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.ALICEBLUE);
        graphicsContext.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        Treillis treillis = this.mainPane.getTreillis();
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
