package miniprojetPOOpackage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SelectionneurPourcentage implements SelectionneurDeResultats {
	private  double p;
    public SelectionneurPourcentage(double p) {
    	this.p=p;
    }
public List<CoupleDeNomsAvecScore> selectionner(List<CoupleDeNomsAvecScore> noms) {
	
    if (noms == null || noms.isEmpty() || p <= 0) {
        return new ArrayList<CoupleDeNomsAvecScore>();
    }

    List<CoupleDeNomsAvecScore> copie = new ArrayList<>(noms);
    
    Comparator<CoupleDeNomsAvecScore> comparateur = new Comparator<CoupleDeNomsAvecScore>() {
        @Override
        public int compare(CoupleDeNomsAvecScore a, CoupleDeNomsAvecScore b) {
            return Double.compare(b.getScore(), a.getScore()); // tri décroissant
        }
    };

    
    copie.sort(comparateur);


    int nombreAGarder = (int) Math.ceil(p / 100.0 * copie.size());
    return new ArrayList<>(copie.subList(0, Math.min(nombreAGarder, copie.size())));
}}
