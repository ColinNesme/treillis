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
public class NoeudAppuiDouble extends NoeudAppui{
    public static double LARGEUR_NOEUD=14;

    public NoeudAppuiDouble(int id, double px, double py, Vecteur2D F, Circle haloSelection) {
        super(id, px, py, F, haloSelection);
    }
    
    public NoeudAppuiDouble(double px, double py, Vecteur2D F) {
        super(px, py, F);
    }

    public NoeudAppuiDouble(double px, double py) {
        super(px, py);
    }

    @Override
    public String toString() {
        return "NoeudAppuiDouble "+ getId() + "{px=" + getPx() + ", py=" + getPy() + ", F=" + getF() + '}';
    }
    
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(this.getPx()-LARGEUR_NOEUD/2, this.getPy()-LARGEUR_NOEUD/2, LARGEUR_NOEUD, LARGEUR_NOEUD);
    }
}
