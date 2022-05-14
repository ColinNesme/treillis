/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis.fx;

import fr.insa.nesme.projetarchitreillis.treillis.Barre;
import fr.insa.nesme.projetarchitreillis.treillis.Noeud;
import fr.insa.nesme.projetarchitreillis.treillis.Treillis;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Elève
 */
public class Controleur {
    
    private Treillis treillis;
    private MainPane mainPane;
    private ZoneDessin zoneDessin;
    private int etat;
    
    private Barre barreEnCours;

    public Controleur(MainPane mainPane) {
        this.mainPane=mainPane;
        this.zoneDessin=mainPane.getZoneDessin();
        this.treillis =mainPane.getTreillis();

    }
    
    public void chgtEtat(int nouvelEtat) {
        switch (nouvelEtat)
        {
            case 1://barre1
                this.mainPane.getbBarre().setSelected(true);
                this.mainPane.redraw();
                this.barreEnCours=null;
                break;
            case 2://barre2
                
                break;
            default:
                throw new AssertionError();
        }
        this.etat=nouvelEtat;
        System.out.println(this.etat);
    }
    
    
    public void clicZoneDessin(MouseEvent t){
        
        double x =t.getX();
        double y =t.getY();
        Treillis treillis = this.mainPane.getTreillis();
        /*
        treillis.ajouteNoeud(new Noeud(x, y));
        this.mainPane.redraw();*/
        
        switch (this.etat)
        {
            case 1:
                Noeud ndebut = new Noeud(x,y);
                this.barreEnCours = new Barre(ndebut, ndebut);
                this.chgtEtat(2);
                this.mainPane.redraw();
                break;
            case 2:
                Noeud nfin = new Noeud(x,y);
                Barre barre =new Barre(this.barreEnCours.getDebut(), nfin);
                treillis.ajouteBarre(barre);
                this.barreEnCours=null;
                this.mainPane.redraw();
                if (t.isShiftDown())
                {
                    this.barreEnCours = new Barre(nfin, nfin);
                }else{
                this.chgtEtat(1);
                }
                break;
            default:
                throw new AssertionError();
        }
        
    }
    
    void mouseMovedDansZoneDessin(MouseEvent t) {
        Treillis treillis = this.mainPane.getTreillis();
        double x =t.getX();
        double y =t.getY();
        if (this.etat == 2) {
            // attente deuxieme point segment
            this.barreEnCours.setFin(new Noeud(x, y));                                                         
            this.mainPane.redraw();
        }
        
    }
    
    public void bNoeudSimple(ActionEvent t){
        
    }
    
    public void bBarre(ActionEvent t){
        this.chgtEtat(1);
    }

    public Treillis getTreillis() {
        return treillis;
    }

    public void setTreillis(Treillis treillis) {
        this.treillis = treillis;
    }

    public MainPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(MainPane mainPane) {
        this.mainPane = mainPane;
    }

    public ZoneDessin getZoneDessin() {
        return zoneDessin;
    }

    public void setZoneDessin(ZoneDessin zoneDessin) {
        this.zoneDessin = zoneDessin;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Barre getBarreEnCours() {
        return barreEnCours;
    }

    public void setBarreEnCours(Barre barreEnCours) {
        this.barreEnCours = barreEnCours;
    }
    
    
    
}
