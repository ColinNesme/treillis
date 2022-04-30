/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.insa.nesme.projetarchitreillis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emonier01
 */
public class Treillis {
    private List<Noeud> listNoeuds = new ArrayList<>();
    private List<Barre> listBarres = new ArrayList<>();

    public Treillis(List<Noeud> listNoeuds, List<Barre> listBarres) {
        this.listNoeuds = listNoeuds;
        this.listBarres = listBarres;
    }

    public Treillis() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    /**
     * @return the listNoeuds
     */
    public List<Noeud> getListNoeuds() {
        return listNoeuds;
    }

    /**
     * @param listNoeuds the listNoeuds to set
     */
    public void setListNoeuds(List<Noeud> listNoeuds) {
        this.listNoeuds = listNoeuds;
    }

    /**
     * @return the listBarres
     */
    public List<Barre> getListBarres() {
        return listBarres;
    }

    /**
     * @param listBarres the listBarres to set
     */
    public void setListBarres(List<Barre> listBarres) {
        this.listBarres = listBarres;
    }

    @Override
    public String toString() {
        return "Treillis{" + "listNoeuds=" + listNoeuds + ", listBarres=" + listBarres + '}';
    }

    public int maxIdNoeud() {
        int max = 0;
        for (int i = 0; i < this.listNoeuds.size(); i++)
        {
            int temp = this.listNoeuds.get(i).getId();
            if (temp > max)
            {
                max = temp;
            }
        }
        return max;
    }

    public int maxIdBarre() {
        int max = 0;
        for (int i = 0; i < this.listBarres.size(); i++)
        {
            int temp = this.listBarres.get(i).getId();
            if (temp > max)
            {
                max = temp;
            }
        }
        return max;
    }

    public void ajouteNoeud(Noeud n) {
        if (listNoeuds.contains(n))
        {
            throw new IllegalStateException();
        } else
        {
            n.setId(this.maxIdNoeud() + 1);
            listNoeuds.add(n);
        }
    }

    public void ajouteBarre(Barre b) {
        if (listBarres.contains(b))
        {
            throw new IllegalStateException();
        } else
        {
            Noeud debut = b.getDebut();
            Noeud fin = b.getFin();
            if (!listNoeuds.contains(debut))
            {
                listNoeuds.add(debut);
            }
            if (!listNoeuds.contains(fin))
            {
                listNoeuds.add(fin);
            }
            b.setId(this.maxIdBarre() + 1);
            listBarres.add(b);
        }
    }

    public Noeud choisiNoeud() {
        System.out.println("liste des noeuds disponibles : ");
        for (int i = 0; i < this.listNoeuds.size(); i++)
        {
            System.out.println((i+1) + ") " + this.listNoeuds.get(i));
        }
        if (this.listNoeuds.isEmpty())
        {
            System.out.println("Aucun noeud disponible");
            return null;
        } else
        {
            int rep = -1;
            while (rep < 0 || rep > this.listNoeuds.size())
            {
                System.out.println("votre choix (0 pour annuler) : ");
                rep = Lire.i();
            }
            if (rep == 0)
            {
                return null;
            } else
            {
                return listNoeuds.get(rep - 1);
            }
        }
    }

    public Barre choisiBarre() {
        System.out.println("liste des barres disponibles : ");
        for (int i = 0; i < this.listBarres.size(); i++)
        {
            System.out.println((i+1) + ") " + this.listBarres.get(i));
        }
        if (this.listBarres.isEmpty())
        {
            System.out.println("Aucune barre disponible");
            return null;
        } else
        {
            int rep = -1;
            while (rep < 0 || rep > this.listBarres.size())
            {
                System.out.println("votre choix (0 pour annuler) : ");
                rep = Lire.i();
            }
            if (rep == 0)
            {
                return null;
            } else
            {
                return listBarres.get(rep - 1);
            }
        }
    }

    public void menuTexte() {
        int rep = -1;
        while (rep != 0)
        {
            System.out.println("Gestion textuelle du treilli");
            System.out.println("---------------------------------------");
            System.out.println("1) afficher le treilli");
            System.out.println("2) créer un noeud");
            System.out.println("3) créer une barre entre deux noeuds existants");
            System.out.println("4) supprimer une barre");
            System.out.println("5) supprimer un noeud");
            System.out.println("0) quitter");
            System.out.println("votre choix : ");
            rep = Lire.i();
            if (rep == 1)
            {
                System.out.println(this);
            } else if (rep == 2)
            {
                ajouteNoeud(Noeud.entreeNoeud());
            } else if (rep == 3 && listNoeuds.size()>1)
            {
                System.out.println("choisissez le noeud de départ de la barre :");
                Noeud deb = this.choisiNoeud();
                System.out.println("choisissez le noeud de fin de la barre :");
                Noeud fin = this.choisiNoeud();
                while (deb.equals(fin)){
                    System.out.println("choisissez un noeud de fin different de noeud de départ de la barre :");
                    fin = this.choisiNoeud();
                }
                this.ajouteBarre(new Barre(deb, fin));
            } else if (rep == 4)
            {
                Barre select = this.choisiBarre();
                this.listBarres.remove(select);
            } else if (rep == 5)
            {
                Noeud select = this.choisiNoeud();
                if (!select.barresIncidentes().isEmpty())
                {
                    System.out.println("Attention : ce noeud contient des barres adajacentes, le supprimer les supprimera également (1 pour continuer , 0 pour annuler) :");
                    int auxrep = Lire.i();
                    if (auxrep == 1)
                    {
                        this.listNoeuds.remove(select);
                        select.barresIncidentes().removeAll(listNoeuds);
                        
                    } else
                    {
                    }
                } else
                {
                    this.listNoeuds.remove(select);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        
        new Treillis().menuTexte();
    }
}
