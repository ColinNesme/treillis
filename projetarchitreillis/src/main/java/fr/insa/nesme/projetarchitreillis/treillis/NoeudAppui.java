/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.nesme.projetarchitreillis.treillis;

import javafx.scene.shape.Circle;

/**
 *
 * @author emonier01
 */
public abstract class NoeudAppui extends Noeud{
    public NoeudAppui(int id, double px, double py, Vecteur2D F,Circle haloSelection) {
        super(id, px, py, F,haloSelection);
    }

    public NoeudAppui(double px, double py, Vecteur2D F) {
        super(px, py, F);
    }

    public NoeudAppui(double px, double py) {
        super(px, py);
    }
}
