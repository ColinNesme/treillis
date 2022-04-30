/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.nesme.projetarchitreillis;

/**
 *
 * @author emonier01
 */
public class Vecteur2D {
    private double vx;

    /**
     * Get the value of vx
     *
     * @return the value of vx
     */
    public double getVx() {
        return vx;
    }

    /**
     * Set the value of vx
     *
     * @param vx new value of vx
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    private double vy;

    /**
     * Get the value of vy
     *
     * @return the value of vy
     */
    public double getVy() {
        return vy;
    }

    /**
     * Set the value of vy
     *
     * @param vy new value of vy
     */
    public void setVy(double vy) {
        this.vy = vy;
    }

    @Override
    public String toString() {
        return "Vecteur2D{" + "vx=" + vx + ", vy=" + vy + '}';
    }

    public Vecteur2D(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }
}
