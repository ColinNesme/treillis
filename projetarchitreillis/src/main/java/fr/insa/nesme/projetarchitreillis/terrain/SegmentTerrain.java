/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis.terrain;

import fr.insa.nesme.projetarchitreillis.treillis.NoeudAppui;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Elève
 */
public class SegmentTerrain{

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

    public SegmentTerrain(int id, PointTerrain debut) {
        this(id, debut, null);
    }

    public SegmentTerrain(int id) {
        this(id, null, null);
    }

    public SegmentTerrain(PointTerrain debut, PointTerrain fin) {
        this(-1, debut, fin);
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

    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(Color.DARKGREEN);
        graphicsContext.setLineWidth(4);
        graphicsContext.strokeLine(this.debut.getPx(), this.debut.getPy(), this.fin.getPx(), this.fin.getPy());

    }

    @Override
    public String toString() {
        return "SegmentTerrain{" + "id=" + id + ", debut=" + debut + ", fin=" + fin + '}';
    }

    public double distanceMouse(double x, double y) {
        double x1 = this.debut.getPx();
        double y1 = this.debut.getPy();
        double x2 = this.fin.getPx();
        double y2 = this.fin.getPy();
        double up = ((x - x1) * (x2 - x1) + (y - y1) * (y2 - y1))
                / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (up < 0) {
            return Math.hypot(x1 - x, y1 - y);
        } else if (up > 1) {
            return Math.hypot(x2 - x, y2 - y);
        } else {
            double x3 = x1 + up * (x2 - x1);
            double y3 = y1 + up * (y2 - y1);
            return Math.hypot(x3 - x, y3 - y);
        }
    }

    public double[] Projection(double x, double y) {
        double[] rep = new double[2];
        PointTerrain v2 = this.getFin();
        PointTerrain v1 = this.getDebut();
        double e1x = v2.getPx() - v1.getPx();
        double e1y = v2.getPy() - v1.getPy();
        double e2x = x - v1.getPx();
        double e2y = y - v1.getPy();
        double scal = e1x * e2x + e1y * e2y;
        double long2 = e1x * e1x + e1y * e1y;
        rep[0] = v1.getPx() + (scal * e1x) / long2;//x du proj
        rep[1] = v1.getPy() + (scal * e1y) / long2;//y du proj
        /*double a=(v2.getPy()-v1.getPy())/(v2.getPx()-v1.getPx());
        double b=-a*v1.getPx()+v1.getPy();*/
        double maxX = 0;
        double maxY = 0;
        double minX = 0;
        double minY = 0;
        if (v2.getPx() < v1.getPx()) {
            maxX = v1.getPx();
            minX = v2.getPx();
        } else {
            maxX = v2.getPx();
            minX = v1.getPx();
        }
        if (v2.getPy() < v1.getPy()) {
            maxY = v1.getPy();
            minY = v2.getPy();
        } else {
            maxY = v2.getPy();
            minY = v1.getPy();
        }
        if (minX < rep[0] && rep[0] < maxX && minY < rep[1] && rep[1] < maxY) {
            return rep;
        } else {
            if (Math.hypot(v1.getPx() - rep[0], v1.getPy() - rep[1]) <= Math.hypot(v2.getPx() - rep[0], v2.getPy() - rep[1])) {
                rep[0] = v1.getPx();
                rep[1] = v1.getPy();
                return rep;
            } else {
                rep[0] = v2.getPx();
                rep[1] = v2.getPy();
                return rep;
            }
        }

    }

}
