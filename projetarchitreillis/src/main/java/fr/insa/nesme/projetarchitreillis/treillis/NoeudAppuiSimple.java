/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.nesme.projetarchitreillis.treillis;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author emonier01
 */
public class NoeudAppuiSimple extends NoeudAppui{
    public static double RAYON_APPUI=7;

    public NoeudAppuiSimple(int id, double px, double py, Vecteur2D F, Circle haloSelection) {
        super(id, px, py, F, haloSelection);
    }

    public NoeudAppuiSimple(double px, double py, Vecteur2D F) {
        super(px, py, F);
    }

    public NoeudAppuiSimple(double px, double py) {
        super(px, py);
    }

    @Override
    public String toString() {
        return "NoeudAppuiSimple " +getId() + "{px=" + getPx() + ", py=" + getPy() + ", F=" + getF() + '}';
    }
    
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(this.getPx()-RAYON_APPUI, this.getPy()-RAYON_APPUI, RAYON_APPUI*2, RAYON_APPUI*2);
        
    }
    
}
