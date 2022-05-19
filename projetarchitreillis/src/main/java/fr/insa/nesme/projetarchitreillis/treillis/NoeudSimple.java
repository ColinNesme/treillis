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
public class NoeudSimple extends Noeud{
    
    public static double RAYON_NOEUD=5;

    public NoeudSimple(int id, double px, double py, Vecteur2D F, Circle haloSelection) {
        super(id, px, py, F, haloSelection);
    }

    public NoeudSimple(double px, double py, Vecteur2D F) {
        super(px, py, F);
    }

    public NoeudSimple(double px, double py) {
        super(px, py);
    }
    
    

    @Override
    public String toString() {
        return "NoeudSimple " + getId() + "{px=" + getPx() + ", py=" + getPy() + ", F=" + getF() + '}';
    }

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.DODGERBLUE);
        graphicsContext.fillOval(this.getPx()-RAYON_NOEUD, this.getPy()-RAYON_NOEUD, RAYON_NOEUD*2, RAYON_NOEUD*2);
    }  


}
