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
public class Terrain {
    private List<PointTerrain> listPoints = new ArrayList<>();
    private List<SegmentTerrain> listSegments = new ArrayList<>();

    public Terrain(List<PointTerrain> listPoints, List<SegmentTerrain> listSegments) {
        this.listPoints = listPoints;
        this.listSegments = listSegments;
    }

    public Terrain() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    /**
     * @return the listPoints
     */
    public List<PointTerrain> getListPoints() {
        return listPoints;
    }

    /**
     * @param listPoints the listPoints to set
     */
    public void setListPoints(List<PointTerrain> listPoints) {
        this.listPoints = listPoints;
    }

    /**
     * @return the listBarres
     */
    public List<SegmentTerrain> getListSegments() {
        return listSegments;
    }

    /**
     * @param listSegments the listBarres to set
     */
    public void setListSegments(List<SegmentTerrain> listSegments) {
        this.listSegments = listSegments;
    }

    @Override
    public String toString() {
        return "Terrain{" + "listPoints=" + listPoints + ", listSegments=" + listSegments + '}';
    }

    
    public int maxIdPoints() {
        int max = 0;
        for (int i = 0; i < this.listPoints.size(); i++)
        {
            int temp = this.listPoints.get(i).getId();
            if (temp > max)
            {
                max = temp;
            }
        }
        return max;
    }

   
    public int maxIdSegments() {
        int max = 0;
        for (int i = 0; i < this.listSegments.size(); i++)
        {
            int temp = this.listSegments.get(i).getId();
            if (temp > max)
            {
                max = temp;
            }
        }
        return max;
    }

    public void ajoutePoint(PointTerrain n) {
        if (listPoints.contains(n))
        {
            throw new IllegalStateException();
        } else
        {
            n.setId(this.maxIdPoints() + 1);
            listPoints.add(n);
        }
    }

    public void ajouteSegment(SegmentTerrain b) {
        if (listSegments.contains(b))
        {
            throw new IllegalStateException();
        } else
        {
            PointTerrain debut = b.getDebut();
            PointTerrain fin = b.getFin();
            if (!listPoints.contains(debut))
            {
                listPoints.add(debut);
            }
            if (!listPoints.contains(fin))
            {
                listPoints.add(fin);
            }
            b.setId(this.maxIdSegments() + 1);
            listSegments.add(b);
        }
    }

    public PointTerrain choisiPoint() {
        System.out.println("liste des points disponibles : ");
        for (int i = 0; i < this.listPoints.size(); i++)
        {
            System.out.println((i+1) + ") " + this.listPoints.get(i));
        }
        if (this.listPoints.isEmpty())
        {
            System.out.println("Aucun point disponible");
            return null;
        } else
        {
            int rep = -1;
            while (rep < 0 || rep > this.listPoints.size())
            {
                System.out.println("votre choix (0 pour annuler) : ");
                rep = Lire.i();
            }
            if (rep == 0)
            {
                return null;
            } else
            {
                return listPoints.get(rep - 1);
            }
        }
    }

    public SegmentTerrain choisiSegment() {
        System.out.println("liste des segments disponibles : ");
        for (int i = 0; i < this.listSegments.size(); i++)
        {
            System.out.println((i+1) + ") " + this.listSegments.get(i));
        }
        if (this.listSegments.isEmpty())
        {
            System.out.println("Aucune segment disponible");
            return null;
        } else
        {
            int rep = -1;
            while (rep < 0 || rep > this.listSegments.size())
            {
                System.out.println("votre choix (0 pour annuler) : ");
                rep = Lire.i();
            }
            if (rep == 0)
            {
                return null;
            } else
            {
                return listSegments.get(rep - 1);
            }
        }
    }

    public void menuTexte() {
        int rep = -1;
        while (rep != 0)
        {
            System.out.println("Gestion textuelle du terrain");
            System.out.println("---------------------------------------");
            System.out.println("1) afficher le terrain");
            System.out.println("2) créer un point");
            System.out.println("3) créer un segment entre deux points existants");
            System.out.println("4) supprimer un segment");
            System.out.println("5) supprimer un point");
            System.out.println("0) quitter");
            System.out.println("votre choix : ");
            rep = Lire.i();
            if (rep == 1)
            {
                System.out.println(this);
            } else if (rep == 2)
            {
                ajoutePoint(PointTerrain.entreePoint());
            } else if (rep == 3 && listPoints.size()>1)
            {
                System.out.println("choisissez le point de départ du segment :");
                PointTerrain debut = this.choisiPoint();
                System.out.println("choisissez le point de fin du segment :");
                PointTerrain fin = this.choisiPoint();
                while (debut.equals(fin)){
                    System.out.println("choisissez un point de fin different du point de départ du segment :");
                    fin = this.choisiPoint();
                }
                this.ajouteSegment(new SegmentTerrain(debut, fin));
            } else if (rep == 4)
            {
                SegmentTerrain select = this.choisiSegment();
                this.listSegments.remove(select);
            } else if (rep == 5)
            {
                PointTerrain select = this.choisiPoint();
                if (!select.segmentIncidentes().isEmpty())
                {
                    System.out.println("Attention : ce point contient des segments adajacentes, le supprimer les supprimera également (1 pour continuer , 0 pour annuler) :");
                    int auxrep = Lire.i();
                    if (auxrep == 1)
                    {
                        this.listPoints.remove(select);
                        select.segmentIncidentes().removeAll(listPoints);
                        
                    } else
                    {
                    }
                } else
                {
                    this.listPoints.remove(select);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        
        new Terrain().menuTexte();
    }
}


