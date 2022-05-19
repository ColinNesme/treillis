/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis.terrain;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Elève
 */
public class Terrain {
    
    private List<PointTerrain> listPoint;
    private List<SegmentTerrain> listSegment;

    public Terrain(List<PointTerrain> listPoint, List<SegmentTerrain> listSegment) {
        this.listPoint = listPoint;
        this.listSegment = listSegment;
    }
    
    public Terrain(){
        this(new ArrayList<>(),new ArrayList<>());
    }

    public List<PointTerrain> getListPoint() {
        return listPoint;
    }

    public void setListPoint(List<PointTerrain> listPoint) {
        this.listPoint = listPoint;
    }

    public List<SegmentTerrain> getListSegment() {
        return listSegment;
    }

    public void setListSegment(List<SegmentTerrain> listSegment) {
        this.listSegment = listSegment;
    }
    
    public int maxIdPoint() {
        int max = 0;
        for (int i = 0; i < this.listPoint.size(); i++)
        {
            int temp = this.listPoint.get(i).getId();
            if (temp > max)
            {
                max = temp;
            }
        }
        return max;
    }

    public int maxIdSegment() {
        int max = 0;
        for (int i = 0; i < this.listSegment.size(); i++)
        {
            int temp = this.listSegment.get(i).getId();
            if (temp > max)
            {
                max = temp;
            }
        }
        return max;
    }
    
    public void ajoutePoint(PointTerrain n) {
        if (listPoint.contains(n))
        {
            throw new IllegalStateException();
        } else
        {
            n.setId(this.maxIdPoint()+ 1);
            listPoint.add(n);
        }
    }

    public void ajouteSegment(SegmentTerrain b) {
        if (listSegment.contains(b))
        {
            throw new IllegalStateException();
        } else
        {
            PointTerrain debut = b.getDebut();
            PointTerrain fin = b.getFin();
            if (!listPoint.contains(debut))
            {
                listPoint.add(debut);
            }
            if (!listPoint.contains(fin))
            {
                listPoint.add(fin);
            }
            b.setId(this.maxIdSegment()+ 1);
            listSegment.add(b);
        }
    }
    
    public void draw(GraphicsContext graphicsContext) {
        for (SegmentTerrain s : this.listSegment)
        {
            s.draw(graphicsContext);
        }
        for (PointTerrain p : this.listPoint)
        {
            p.draw(graphicsContext);
        }
    }
    
}
