/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.nesme.projetarchitreillis.treillis;

/**
 *
 * @author emonier01
 */
public class NoeudAppuiDouble extends NoeudAppui{
    public NoeudAppuiDouble(int id, double px, double py, Vecteur2D F) {
        super(id, px, py, F);
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
}
