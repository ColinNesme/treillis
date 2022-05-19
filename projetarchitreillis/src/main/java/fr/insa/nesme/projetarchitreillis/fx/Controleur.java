/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis.fx;

import fr.insa.nesme.projetarchitreillis.terrain.PointTerrain;
import fr.insa.nesme.projetarchitreillis.terrain.SegmentTerrain;
import fr.insa.nesme.projetarchitreillis.terrain.Terrain;
import fr.insa.nesme.projetarchitreillis.treillis.Barre;
import fr.insa.nesme.projetarchitreillis.treillis.NoeudAppuiDouble;
import fr.insa.nesme.projetarchitreillis.treillis.NoeudAppuiSimple;
import fr.insa.nesme.projetarchitreillis.treillis.NoeudSimple;
import fr.insa.nesme.projetarchitreillis.treillis.Treillis;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 *
 * @author Elève
 */
public class Controleur {

    private Treillis treillis;
    private Terrain terrain;
    private MainPane mainPane;
    private ZoneDessin zoneDessin;
    private int etat;

    private Barre barreEnCours;
    private SegmentTerrain segmentTerrainEnCours;

    public Controleur(MainPane mainPane) {
        this.mainPane = mainPane;
        this.zoneDessin = mainPane.getZoneDessin();
        this.treillis = mainPane.getTreillis();
        this.terrain = mainPane.getTerrain();

    }

    public void chgtEtat(int nouvelEtat) {
        switch (nouvelEtat) {
            case 0://noeudsimple
                this.mainPane.getbNoeudSimple().setSelected(true);
                this.mainPane.redraw();//redessine le canvas
                this.barreEnCours = null;//
                this.segmentTerrainEnCours = null;
                break;
            case 1://barre1
                this.mainPane.getbBarre().setSelected(true);
                this.mainPane.redraw();
                this.barreEnCours = null; //annule la barre qui a commencé à être crée si on était avatn dans barre et qu'on avait cliqué une fois
                this.segmentTerrainEnCours = null;
                break;
            case 2://barre2

                break;
            case 3://terrain1
                this.mainPane.getbPointTerrain().setSelected(true);
                this.mainPane.redraw();
                this.barreEnCours = null;
                this.segmentTerrainEnCours = null;
                break;
            case 4://terrain2

                break;
            case 5://appuidouble
                this.mainPane.getbAppuiDouble().setSelected(true);
                this.mainPane.redraw();
                this.barreEnCours = null;
                this.segmentTerrainEnCours = null;
                break;
            case 6://appuisimple
                this.mainPane.getbAppuiSimple().setSelected(true);
                this.mainPane.redraw();
                this.barreEnCours = null;
                this.segmentTerrainEnCours = null;
                break;
            case 7://suppression
                this.mainPane.getbSupp().setDisable(false);
                this.mainPane.redraw();
                this.barreEnCours = null;
                this.segmentTerrainEnCours = null;
            default:
                throw new AssertionError();
        }
        this.etat = nouvelEtat;
    }

    public void clicZoneDessin(MouseEvent t) {
        //récupération de la position de l'endroit ou on a cliqué
        double x = t.getX();
        double y = t.getY();
        Treillis treillis = this.mainPane.getTreillis();
        Terrain terrain = this.mainPane.getTerrain();
        ZoneDessin zoneDessin = this.mainPane.getZoneDessin();
        /*
        treillis.ajouteNoeud(new Noeud(x, y));
        this.mainPane.redraw();*/

        switch (this.etat) {
            case 0://noeudsimple
                NoeudSimple noeud = new NoeudSimple(x, y);
                Circle haloNoeud=noeud.getHaloSelection();
                zoneDessin.getChildren().add(haloNoeud);
                haloNoeud.setCenterX(x);
                haloNoeud.setCenterY(y);
                haloNoeud.setOnMouseClicked((e) -> {
                    switch (getEtat()) {
                        case 1:
                            this.barreEnCours = new Barre(noeud, noeud);
                            this.chgtEtat(2);
                            this.mainPane.redraw();
                            break;
                        case 2:
                            Barre barre = new Barre(this.barreEnCours.getDebut(), noeud);
                            treillis.ajouteBarre(barre);
                            this.barreEnCours = null;
                            this.mainPane.redraw();
                            if (t.isShiftDown()) {
                                this.barreEnCours = new Barre(noeud, noeud);
                            } else {
                                this.chgtEtat(1);
                            }
                            break;
                        case 7:
                            treillis.getListNoeuds().remove(treillis.getListNoeuds().get(noeud.getId()));
                            for (Barre barresupp:noeud.barresIncidentes()) {
                                treillis.getListBarres().remove(treillis.getListBarres().get(barresupp.getId()));
                            }
                            this.mainPane.redraw();
                            break;
                        default:
                            throw new AssertionError();
                    }
                });
                treillis.ajouteNoeud(noeud);
                this.mainPane.redraw();
                break;
            case 1://barre1
                NoeudSimple ndebut = new NoeudSimple(x, y);
                //fait le halo de selection
                Circle haloNoeudB1=ndebut.getHaloSelection();
                zoneDessin.getChildren().add(haloNoeudB1);
                haloNoeudB1.setCenterX(x);
                haloNoeudB1.setCenterY(y);
                haloNoeudB1.setOnMouseClicked((e) -> {
                    switch (getEtat()) {
                        case 1:
                            this.barreEnCours = new Barre(ndebut, ndebut);
                            this.chgtEtat(2);
                            this.mainPane.redraw();
                            break;
                        case 2:
                            Barre barre = new Barre(this.barreEnCours.getDebut(), ndebut);
                            treillis.ajouteBarre(barre);
                            this.barreEnCours = null;
                            this.mainPane.redraw();
                            if (t.isShiftDown()) {
                                this.barreEnCours = new Barre(ndebut, ndebut);
                            } else {
                                this.chgtEtat(1);
                            }
                            break;
                        default:
                            throw new AssertionError();
                    }
                });
                //
                this.barreEnCours = new Barre(ndebut, ndebut);
                this.chgtEtat(2);
                this.mainPane.redraw();
                break;
            case 2://barre2
                NoeudSimple nfin = new NoeudSimple(x, y);
                Circle haloNoeudB2=nfin.getHaloSelection();
                zoneDessin.getChildren().add(haloNoeudB2);
                haloNoeudB2.setCenterX(x);
                haloNoeudB2.setCenterY(y);
                haloNoeudB2.setOnMouseClicked((e) -> {
                    switch (getEtat()) {
                        case 1:
                            this.barreEnCours = new Barre(nfin, nfin);
                            this.chgtEtat(2);
                            this.mainPane.redraw();
                            break;
                        case 2:
                            Barre barre = new Barre(this.barreEnCours.getDebut(), nfin);
                            treillis.ajouteBarre(barre);
                            this.barreEnCours = null;
                            this.mainPane.redraw();
                            if (t.isShiftDown()) {
                                this.barreEnCours = new Barre(nfin, nfin);
                            } else {
                                this.chgtEtat(1);
                            }
                            break;
                        default:
                            throw new AssertionError();
                    }
                });
                Barre barre = new Barre(this.barreEnCours.getDebut(), nfin);
                treillis.ajouteBarre(barre);
                this.barreEnCours = null;
                this.mainPane.redraw();
                if (t.isShiftDown()) {
                    this.barreEnCours = new Barre(nfin, nfin);
                } else {
                    this.chgtEtat(1);
                }
                break;
            case 3://terrain1
                PointTerrain pdebut = new PointTerrain(x, y);
                Circle haloTerrain = pdebut.getHaloSelection();
                zoneDessin.getChildren().add(haloTerrain);
                haloTerrain.setCenterX(x);
                haloTerrain.setCenterY(y);
                haloTerrain.setOnMouseClicked((e) -> {
                    if (this.getEtat()==4) {
                        SegmentTerrain seg = new SegmentTerrain(this.segmentTerrainEnCours.getDebut(), pdebut);
                        terrain.ajouteSegment(seg);
                        this.barreEnCours = null;
                        this.mainPane.redraw();
                        this.segmentTerrainEnCours = null;
                        this.chgtEtat(3);
                    }
                });
                this.segmentTerrainEnCours = new SegmentTerrain(pdebut, pdebut);
                pdebut.setSegArrivee(null);
                this.chgtEtat(4);
                this.mainPane.redraw();
                break;

            case 4://terrain2
                PointTerrain pfin = new PointTerrain(x, y);
                SegmentTerrain seg = new SegmentTerrain(this.segmentTerrainEnCours.getDebut(), pfin);
                terrain.ajouteSegment(seg);
                this.barreEnCours = null;
                this.mainPane.redraw();
                this.segmentTerrainEnCours = new SegmentTerrain(pfin, pfin);
                break;
            case 5://AppuiDouble
                double[] projDouble = trouverAppui(t);
                NoeudAppuiDouble appuiDouble=new NoeudAppuiDouble(projDouble[0], projDouble[1]);
                Circle haloAppuiDouble=appuiDouble.getHaloSelection();
                zoneDessin.getChildren().add(haloAppuiDouble);
                haloAppuiDouble.setCenterX(projDouble[0]);
                haloAppuiDouble.setCenterY(projDouble[1]);
                haloAppuiDouble.setOnMouseClicked((e) -> {
                    switch (getEtat()) {
                        case 1:
                            this.barreEnCours = new Barre(appuiDouble, appuiDouble);
                            this.chgtEtat(2);
                            this.mainPane.redraw();
                            break;
                        case 2:
                            Barre barreAppui = new Barre(this.barreEnCours.getDebut(), appuiDouble);
                            treillis.ajouteBarre(barreAppui);
                            this.barreEnCours = null;
                            this.mainPane.redraw();
                            if (t.isShiftDown()) {
                                this.barreEnCours = new Barre(appuiDouble, appuiDouble);//barre de passage
                            } else {
                                this.chgtEtat(1);
                            }
                            break;
                        default:
                            throw new AssertionError();
                    }
                });
                treillis.ajouteNoeud(appuiDouble);
                this.mainPane.redraw();
                break;
            case 6://AppuiSimple
                double[] projSimple = trouverAppui(t);
                NoeudAppuiSimple appuiSimple=new NoeudAppuiSimple(projSimple[0], projSimple[1]);
                Circle haloAppuiSimple=appuiSimple.getHaloSelection();
                zoneDessin.getChildren().add(haloAppuiSimple);
                haloAppuiSimple.setCenterX(projSimple[0]);
                haloAppuiSimple.setCenterY(projSimple[1]);
                haloAppuiSimple.setOnMouseClicked((e) -> {
                    switch (getEtat()) {
                        case 1:
                            this.barreEnCours = new Barre(appuiSimple, appuiSimple);
                            this.chgtEtat(2);
                            this.mainPane.redraw();
                            break;
                        case 2:
                            Barre barreAppui = new Barre(this.barreEnCours.getDebut(), appuiSimple);
                            treillis.ajouteBarre(barreAppui);
                            this.barreEnCours = null;
                            this.mainPane.redraw();
                            if (t.isShiftDown()) {
                                this.barreEnCours = new Barre(appuiSimple, appuiSimple);//barre de passage
                            } else {
                                this.chgtEtat(1);
                            }
                            break;
                        default:
                            throw new AssertionError();
                    }
                });
                treillis.ajouteNoeud(appuiSimple);
                this.mainPane.redraw();
                break;
            default:
                throw new AssertionError();
        }

    }

    public void mouseMovedDansZoneDessin(MouseEvent t) {
        Treillis treillis = this.mainPane.getTreillis();
        Terrain terrain = this.mainPane.getTerrain();
        double x = t.getX();
        double y = t.getY();
        if (this.etat == 2) {
            // attente deuxieme point segment
            this.barreEnCours.setFin(new NoeudSimple(x, y));
            this.mainPane.redraw();
        }

    }

    public double[] trouverAppui(MouseEvent t) {
        double x = t.getX();
        double y = t.getY();
        Terrain terrain = this.mainPane.getTerrain();
        double min = Double.MAX_VALUE;
        SegmentTerrain segmentProche = null;
        for (SegmentTerrain segment : terrain.getListSegment()) {
            double res = segment.distanceMouse(x, y);
            if (res < min) {
                min = res;
                segmentProche = segment;
            }
        }
        return segmentProche.Projection(x, y);
    }

    public void bNoeudSimple(ActionEvent t) {
        this.chgtEtat(0);

    }

    public void bBarre(ActionEvent t) {
        this.chgtEtat(1);
    }

    public void bPointTerrain(ActionEvent t) {
        this.chgtEtat(3);
    }

    public void bAppuiDouble(ActionEvent t) {
        this.chgtEtat(5);
    }

    public void bAppuiSimple(ActionEvent t) {
        this.chgtEtat(6);
    }
    
    public void bSupp(ActionEvent t) {
        this.chgtEtat(7);
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

    /**
     * @return the terrain
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * @param terrain the terrain to set
     */
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

}
