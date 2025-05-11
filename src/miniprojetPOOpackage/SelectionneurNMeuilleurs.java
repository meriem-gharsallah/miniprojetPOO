package miniprojetPOOpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SelectionneurNMeuilleurs implements SelectionneurDeResultats {
	private  Configuration config;
    public SelectionneurNMeuilleurs(Configuration config) {
    	this.config=config;
        
    }
    public List<CoupleDeNomsAvecScore> selectionner(List<CoupleDeNomsAvecScore> noms) {
    	int N = config.getNombreMax();
    	List<CoupleDeNomsAvecScore> copie = new ArrayList<>(noms);
        Comparator<CoupleDeNomsAvecScore> comparateurCroissant = new Comparator<CoupleDeNomsAvecScore>() {
            public int compare(CoupleDeNomsAvecScore a, CoupleDeNomsAvecScore b) {
                return Double.compare(a.getScore(), b.getScore());
            }
        };
        Comparator<CoupleDeNomsAvecScore> comparateurDecroissant = comparateurCroissant.reversed();
        Collections.sort(copie, comparateurDecroissant);
        int taille = Math.min(N, copie.size());
        return new ArrayList<>(copie.subList(0, taille));
    }
}

