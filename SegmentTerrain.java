/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis;

import java.util.List;

/**
 *
 * @author lolom
 */
public class SegmentTerrain {

    private int id;
    private PointTerrain debut;
    private PointTerrain fin;
    
    public int getId() {
        return id;
    }

    public PointTerrain getDebut() {
        return debut;
    }

    public PointTerrain getFin() {
        return fin;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SegmentTerrain{" + "id=" + id + ", debut=" + debut + ", fin=" + fin + '}';
    }

    public void setDebut(PointTerrain debut) {
        this.debut = debut;
        List<SegmentTerrain> segmentDepart = debut.getSegmentDepart();
        segmentDepart.add(this);
        debut.setSegmentDepart(segmentDepart);
    }

    public void setFin(PointTerrain fin) {
        this.fin = fin;
        List<SegmentTerrain> segmentArrivee = fin.getSegmentArrivee();
        segmentArrivee.add(this);
        fin.setSegmentArrivee(segmentArrivee);
    }


    public SegmentTerrain (int id, PointTerrain debut, PointTerrain fin) {
        this.id = id;
        this.debut = debut;
        this.fin = fin;
        List<SegmentTerrain> segmentDepart = debut.getSegmentDepart();
        segmentDepart.add(this);
        debut.setSegmentDepart(segmentDepart);
        List<SegmentTerrain> segmentArrivee = fin.getSegmentArrivee();
        segmentArrivee.add(this);
        fin.setSegmentArrivee(segmentArrivee);
    }

    /**
     *
     * @param id
     */
    public SegmentTerrain (int id) {
        this.id = id;
    }

    public SegmentTerrain (PointTerrain debut, PointTerrain fin) {
        this.debut = debut;
        this.fin = fin;
    }
    
    public PointTerrain pointOppose(PointTerrain n) {
        if (n == this.debut) {
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
                        this.debut.getPx() - this.pointOppose(debut).getPx(),
                        this.debut.getPy() - this.pointOppose(debut).getPy()
                )
        );
    }
}

    
    
    

    /*SegmentTerrain(PointTerrain deb, PointTerrain fin) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setId(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    PointTerrain getDebut() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    PointTerrain getFin() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}*/
