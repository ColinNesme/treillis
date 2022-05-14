/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis.terrain;

/**
 *
 * @author Elève
 */
public class PointTerrain extends ElementTerrain{
    
    private double px;
    private double py;
    private int id;
    private SegmentTerrain SegDepart;
    private SegmentTerrain SegArrivee;

    
    public PointTerrain(double px, double py, int id, SegmentTerrain SegDepart, SegmentTerrain SegArrivee) {
        this.px = px;
        this.py = py;
        this.id = id;
        this.SegDepart = SegDepart;
        this.SegArrivee = SegArrivee;
    }
    
    public PointTerrain(double px, double py, int id){
        this(px,py,id,null,null);    
    }
    
    public double getPx() {
        return px;
    }

    

    public void setPx(double px) {
        this.px = px;
    }

    public double getPy() {
        return py;
    }

    public void setPy(double py) {
        this.py = py;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SegmentTerrain getSegDepart() {
        return SegDepart;
    }

    public void setSegDepart(SegmentTerrain SegDepart) {
        this.SegDepart = SegDepart;
    }

    public SegmentTerrain getSegArrivee() {
        return SegArrivee;
    }

    public void setSegArrivee(SegmentTerrain SegArrivee) {
        this.SegArrivee = SegArrivee;
    }
    
    
}
