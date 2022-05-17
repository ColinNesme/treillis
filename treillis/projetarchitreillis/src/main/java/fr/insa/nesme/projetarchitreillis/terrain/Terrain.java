/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.nesme.projetarchitreillis.terrain;

import java.util.ArrayList;
import java.util.List;

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
}
