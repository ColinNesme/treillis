/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lolom
 */
public class PointTerrain {
    
    private int id;
    private double px;
    private double py;
    private List<SegmentTerrain> segmentDepart;
    private List<SegmentTerrain> segmentArrivee;

    public void setSegmentDepart(List<SegmentTerrain> segmentDepart) {
        this.segmentDepart = segmentDepart;
    }

    public void setSegmentArrivee(List<SegmentTerrain> segmentArrivee) {
        this.segmentArrivee = segmentArrivee;
    }

    public List<SegmentTerrain> getSegmentDepart() {
        return segmentDepart;
    }

    public List<SegmentTerrain> getSegmentArrivee() {
        return segmentArrivee;
    }

    
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
     * @return the px
     */
    public double getPx() {
        return px;
    }

    /**
     * @param px the px to set
     */
    public void setPx(double px) {
        this.px = px;
    }

    /**
     * @return the py
     */
    public double getPy() {
        return py;
    }

    /**
     * @param py the py to set
     */
    public void setPy(double py) {
        this.py = py;
        
    }

    public PointTerrain(int id,double px, double py) {
        this.id = id;
        this.px = px;
        this.py = py;
        this.segmentDepart = new ArrayList<>();
        this.segmentArrivee = new ArrayList<>();
    }
    
      public PointTerrain(double px, double py) {
        this.px = px;
        this.py = py;
        this.segmentDepart = new ArrayList<>();
        this.segmentArrivee = new ArrayList<>();
    }

    public PointTerrain(int id) {
        this.id = id;
        
    }
    
    public static PointTerrain entreePoint() {
        System.out.println("Entrez la coordonnée en abscisse du point :");
        double px=Lire.d();
        System.out.println("Entrez la coordonnée en ordonnée du point :");
        double py=Lire.d();
        
        return new PointTerrain (px, py);
    }

    @Override
    public String toString() {
        return "PointTerrain{" + "id=" + id + ", px=" + px + ", py=" + py + '}';
    }
    
    /**
     *
     * @return
     */
    public List segmentIncidentes() {
        List l=new ArrayList<>(getSegmentDepart());
        l.addAll(getSegmentArrivee());
        return  l;
        
    }
    
    
}

  
