/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis.terrain;

/**
 *
 * @author Elève
 */
public class SegmentTerrain extends ElementTerrain{
    private int id;
    private PointTerrain debut;
    private PointTerrain fin;

    public SegmentTerrain(int id, PointTerrain debut, PointTerrain fin) {
        this.id = id;
        this.debut = debut;
        this.fin = fin;
        debut.setSegDepart(this);
        fin.setSegArrivee(this);
    }
    
    public SegmentTerrain(int id,PointTerrain debut){
        this(id,debut,null);
    }
    
    public SegmentTerrain(int id){
        this(id,null,null);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PointTerrain getDebut() {
        return debut;
    }

    public void setDebut(PointTerrain debut) {
        this.debut = debut;
        debut.setSegDepart(this);
    }

    public PointTerrain getFin() {
        return fin;
    }

    public void setFin(PointTerrain fin) {
        this.fin = fin;
        debut.setSegArrivee(this);
    }
    
    
}
