package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ComparateurNomDecompose implements ComparateurNom {
	private List<String> listeDesNomsComparateursDeChaines;
	public ComparateurNomDecompose(List<String> listeDesNomsComparateursDeChaines) {
		this.listeDesNomsComparateursDeChaines = listeDesNomsComparateursDeChaines;
	}
	public double comparer(Nom n1, Nom n2) {
	    /*List<String> motsModifiables = new ArrayList<>(n1.genererListeDeChaines(n1).getElements());
	    List<String> L = n1.genererToutesLesConcatenations(motsModifiables);*/
	    
	    List<String> motsModifiables1 = new ArrayList<>(n2.genererListeDeChaines(n2));
	    List<String> L1 = n2.genererToutesLesConcatenations(motsModifiables1);

	    List<Double> L2 = new ArrayList<>();
	    List<Double> L3 = new ArrayList<>();
	    
	    int taille = listeDesNomsComparateursDeChaines.size();
	    double ponderation = 1.0 / taille;

	    for (String s : L1) {
	        /*for (String s1 : L1) {*/
	            double moyenne = 0.0;
	            for (String s2 : listeDesNomsComparateursDeChaines) {
	                ComparateurApproximatifdeChaine c = Main.getComparateurDeChaine(s2);
	                double score = c.comparer(s, n1.getNom());
	                L2.add(score);
	            }
	            for (double d : L2) {
	                moyenne += ponderation * d;
	            }

	            L3.add(moyenne);
	            L2.clear(); 
	        
	    }

	    return Collections.max(L3); 
	}

	
	public List<String> getListeDesNomsComparateursDeChaines() {
		return listeDesNomsComparateursDeChaines;
	}
	public void setListeDesNomsComparateursDeChaines(List<String> listeDesNomsComparateursDeChaines) {
		this.listeDesNomsComparateursDeChaines = listeDesNomsComparateursDeChaines;
	}}

