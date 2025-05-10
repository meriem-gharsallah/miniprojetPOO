package miniprojetPOOpackage;

import java.util.ArrayList;
import java.util.List;

public class MainGenerateurAvecTaille {
    public static void main(String[] args) {
  
    	List<Nom> l=new ArrayList<Nom>();
    	/*Nom n=new Nom("1","meriem gharsallah");
    	Nom n1=new Nom("2","hamza soussi");
    	l.add(n);
    	l.add(n1);*/
        RecuperateurCSV r = new RecuperateurCSV("fichier.csv");
        l= r.recuperer();
        System.out.println("Répertoire courant : " + System.getProperty("user.dir"));
        /*for (Nom n : l) {
            System.out.println(n.getId() +" " + n.getNom());
            
        }*/
        List<Nom> l2=new ArrayList<Nom>();
        r.setCheminFichier("fichier2.csv");
        l2 = r.recuperer();
        /*Nom n2=new Nom("3","meriem gharsallah");
    	Nom n3=new Nom("4","hamza dskl dl slk jbj  dcd delm soussi");
    	l2.add(n2);
    	l2.add(n3);*/
        ComparateurEgaliteExacte c =new ComparateurEgaliteExacte();
    	SelectionneurAleatoire s=new SelectionneurAleatoire();
    	//RecuperateurListe r=new RecuperateurListe(l);
    	GenerateurDeCandidatsAvecTaille g=new GenerateurDeCandidatsAvecTaille();
        System.out.println(g.generer(l, l2));
        }
    }


