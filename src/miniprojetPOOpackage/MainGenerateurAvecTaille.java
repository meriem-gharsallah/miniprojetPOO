package miniprojetPOOpackage;

import java.util.ArrayList;
import java.util.List;

public class MainGenerateurAvecTaille {
    public static void main(String[] args) {
  
    	List<Nom> l=new ArrayList<Nom>();
        RecuperateurCSV r = new RecuperateurCSV("fichier.csv");
        l= r.recuperer();
        System.out.println("Répertoire courant : " + System.getProperty("user.dir"));
        List<Nom> l2=new ArrayList<Nom>();
        r.setCheminFichier("fichier2.csv");
        l2 = r.recuperer();
        ComparateurEgaliteExacte c =new ComparateurEgaliteExacte();
    	SelectionneurAleatoire s=new SelectionneurAleatoire();
    	//RecuperateurListe r=new RecuperateurListe(l);
    	GenerateurDeCandidatsAvecTaille g=new GenerateurDeCandidatsAvecTaille();
        //System.out.println(g.generer(l, l2));
        }
    }


