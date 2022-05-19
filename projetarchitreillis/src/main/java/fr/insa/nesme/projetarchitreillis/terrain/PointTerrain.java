/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis.terrain;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Elève
 */
public class PointTerrain{

    private double px;
    private double py;
    private int id;
    private SegmentTerrain SegDepart;
    private SegmentTerrain SegArrivee;
    private Circle haloSelection;

    public PointTerrain(double px, double py, int id, SegmentTerrain SegDepart, SegmentTerrain SegArrivee, Circle haloSelection) {
        this.px = px;
        this.py = py;
        this.id = id;
        this.SegDepart = SegDepart;
        this.SegArrivee = SegArrivee;
        this.haloSelection = haloSelection;
        this.haloSelection.setFill(Color.BLUE);
        this.haloSelection.setOpacity(0);
        this.haloSelection.setOnMouseEntered((t) -> {
            if (this.SegArrivee==null) {
                this.haloSelection.setOpacity(0.4);
            }
        });
        this.haloSelection.setOnMouseExited((t) -> {
            this.haloSelection.setOpacity(0);
        });
    }

    public PointTerrain(double px, double py, int id) {
        this(px, py, id, null, null, new Circle(12));
    }

    public PointTerrain(double px, double py) {
        this(px, py, -1, null, null, new Circle(12));
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

    public Circle getHaloSelection() {
        return haloSelection;
    }

    public void setHaloSelection(Circle haloSelection) {
        this.haloSelection = haloSelection;
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

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillOval(this.getPx() - 4, this.getPy() - 4, 8, 8);
    }

    @Override
    public String toString() {
        return "PointTerrain{" + "px=" + px + ", py=" + py + ", id=" + id+ '}';
    }
    
    

}
