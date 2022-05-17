/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.nesme.projetarchitreillis.treillis;

import java.util.List;

/**
 *
 * @author emonier01
 */
public class Barre {
    private int id;
    private Noeud debut;
    private Noeud fin;
    private double tractmax;
    private double maxcomp;
    private double cout;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the debut
     */
    public Noeud getDebut() {
        return debut;
    }

    /**
     * @param debut the debut to set
     */
    public void setDebut(Noeud debut) {
        this.debut = debut;
        List<Barre> barresDepart=debut.getBarresDepart();
        barresDepart.add(this);
        debut.setBarresDepart(barresDepart);
    }

    /**
     * @return the fin
     */
    public Noeud getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(Noeud fin) {
        this.fin = fin;
        List<Barre> barresArrivee=fin.getBarresArrivee();
        barresArrivee.add(this);
        fin.setBarresArrivee(barresArrivee);
    }

    /**
     * @return the tractmax
     */
    public double getTractmax() {
        return tractmax;
    }

    /**
     * @param tractmax the tractmax to set
     */
    public void setTractmax(double tractmax) {
        this.tractmax = tractmax;
    }

    /**
     * @return the mincomp
     */
    public double getMaxcomp() {
        return maxcomp;
    }

    /**
     * @param mincomp the mincomp to set
     */
    public void setMaxcomp(double mincomp) {
        this.maxcomp = mincomp;
    }

    /**
     * @return the cout
     */
    public double getCout() {
        return cout;
    }

    /**
     * @param cout the cout to set
     */
    public void setCout(double cout) {
        this.cout = cout;
    }

    public Barre(int id, Noeud debut, Noeud fin, double tractmax, double maxcomp, double cout) {
        this.id = id;
        this.debut = debut;
        this.fin = fin;
        this.tractmax = tractmax;
        this.maxcomp = maxcomp;
        this.cout = cout;
        List<Barre> barresDepart=debut.getBarresDepart();
        barresDepart.add(this);
        debut.setBarresDepart(barresDepart);
        List<Barre> barresArrivee=fin.getBarresArrivee();
        barresArrivee.add(this);
        fin.setBarresArrivee(barresArrivee);
    }

    public Barre(Noeud debut, Noeud fin, double maxtract, double maxcomp, double cout) {
        this(-1, debut, fin, maxtract, maxcomp, cout);
    }

    public Barre(Noeud debut, Noeud fin, double maxtract, double maxcomp) {
        this(debut, fin, maxtract, maxcomp, 0);
    }

    public Barre(Noeud debut, Noeud fin, double cout) {
        this(debut, fin, 0, 0, cout);
    }

    public Barre(Noeud debut, Noeud fin) {
        this(debut, fin, 0);
    }

    @Override
    public String toString() {
        return "Barre " + id + "{" + ", nœud de départ=" + debut + ", nœud d’arrivée=" + fin + ", traction maximale=" + tractmax + ", compression maximale=" + maxcomp + ", coût au mètre=" + cout + '}';
    }

    public Noeud noeudOppose(Noeud n) {
        if (n == this.debut)
        {
            return this.fin;
        }
        if (n == this.fin)
        {
            return this.debut;
        } else
        {
            throw new IllegalStateException();
        }
    }

    public double angle() {
        
        return Math.atan(
                Math.hypot(
                        this.debut.getPx() - this.noeudOppose(debut).getPx(),
                        this.debut.getPy() - this.noeudOppose(debut).getPy()
                )
        );
    }
}
