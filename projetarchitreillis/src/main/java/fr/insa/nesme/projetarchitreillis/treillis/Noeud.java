/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.nesme.projetarchitreillis.treillis;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author vpasiecznik01
 */
public abstract class Noeud {
    private int id;
    private double px;
    private double py;
    private Vecteur2D F; //chaque noeud a une force associée
    private List<Barre> barresDepart;
    private List<Barre> barresArrivee;
    private Circle haloSelection;

    public  static double RAYON_HALO=12;
    
    public Noeud(int id, double px, double py, Vecteur2D F,Circle haloSelection) {
        this.barresDepart = new ArrayList<>();
        this.barresArrivee = new ArrayList<>();
        this.id = id;
        this.px = px;
        this.py = py;
        this.F = F;
        this.haloSelection=haloSelection;
        this.haloSelection.setFill(Color.BLUE);
        this.haloSelection.setOpacity(0);
        this.haloSelection.setOnMouseEntered((t) -> {
            this.haloSelection.setOpacity(0.4);
        });
        this.haloSelection.setOnMouseExited((t) -> {
            this.haloSelection.setOpacity(0);
        });
        
    }
    public Noeud( double px, double py, Vecteur2D F) {
        this(-1,px,py,F,new Circle(RAYON_HALO));
        this.barresDepart = new ArrayList<>();
        this.barresArrivee = new ArrayList<>();
    }
    public Noeud( double px, double py) {
        this(-1,px,py, new Vecteur2D(0, 0),new Circle(RAYON_HALO));
        this.barresDepart = new ArrayList<>();
        this.barresArrivee = new ArrayList<>();
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

    /**
     * @return the F
     */
    public Vecteur2D getF() {
        return F;
    }

    /**
     * @param F the F to set
     */
    public void setF(Vecteur2D F) {
        this.F = F;
    }
    
    /**
     * @return the barresDepart
     */
    public List<Barre> getBarresDepart() {
        return barresDepart;
    }

    /**
     * @param barresDepart the barresDepart to set
     */
    public void setBarresDepart(List<Barre> barresDepart) {
        this.barresDepart = barresDepart;
    }

    /**
     * @return the barresArrivee
     */
    public List<Barre> getBarresArrivee() {
        return barresArrivee;
    }

    /**
     * @param barresArrivee the barresArrivee to set
     */
    public void setBarresArrivee(List<Barre> barresArrivee) {
        this.barresArrivee = barresArrivee;
    }

    
    
    
    @Override
    public String toString() {
        return "Noeud " + getId() + "{px=" + getPx() + ", py=" + getPy() + ", F=" + getF() + '}';
    }

    public static Noeud entreeNoeud() {
        String c="";
        do
        {
            System.out.println("Entrez le type de noeud (NoeudSimple, NoeudAppuiSimple, NoeudAppuiDouble):");
            c=Lire.S();
        } while (!c.equals("NoeudSimple") && !c.equals("NoeudAppuiSimple") && !c.equals("NoeudAppuiDouble"));
        System.out.println("Entrez la coordonnée en abscisse du noeud :");
        double px=Lire.d();
        System.out.println("Entrez la coordonnée en ordonnée du noeud :");
        double py=Lire.d();
        System.out.println("Entrez la valeur en abscisse du vecteur appliqué au noeud :");
        double vx=Lire.d();
        System.out.println("Entrez la valeur en ordonnée du vecteur appliqué au noeud :");
        double vy=Lire.d();
        Vecteur2D F=new Vecteur2D(vx, vy);
        if (c.equals("NoeudSimple")){
            return new NoeudSimple(px, py, F);
        }if (c.equals("NoeudAppuiSimple")){
            return new NoeudAppuiSimple(px, py, F);
        }else{
            return new NoeudAppuiDouble(px, py, F);
        }
       
    }
    
    public int nbrInconnues() {
        Object c=this.getClass();
        if (c==NoeudSimple.class){
            return 0;
        }if (c==NoeudAppuiSimple.class){
            return 1;
        }else{
            return 2;
        }
    }
    
    public List<Barre> barresIncidentes() {
        List l=new ArrayList<>(getBarresDepart());
        l.addAll(getBarresArrivee());
        return  l;
        
    }
    
    /**
     *
     * @param graphicsContext
     */
    public abstract void draw(GraphicsContext graphicsContext);

    /**
     * @return the haloSelection
     */
    public Circle getHaloSelection() {
        return haloSelection;
    }

    /**
     * @param haloSelection the haloSelection to set
     */
    public void setHaloSelection(Circle haloSelection) {
        this.haloSelection = haloSelection;
    }
}
