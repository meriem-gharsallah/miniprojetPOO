package miniprojetPOOpackage;

import java.util.ArrayList;
import java.util.List;

public class Normalisation implements Pretraiteur {

    @Override
    public List<Nom> pretraiter(List<Nom> noms) {
        List<Nom> resultat = new ArrayList<>();

        for (Nom nom : noms) {
        	// 1. On transforme les caractères accentus en combinaison lettre + accent
            String decompose = java.text.Normalizer.normalize(nom.getNom(), java.text.Normalizer.Form.NFD);
            // 2. On enlève les signes d'accent (diacritiques)
        	String texteNormalise = decompose.replaceAll("[\\p{M}]", "")
        									 .toLowerCase()
                                             .trim()
                                             .replaceAll("\\s+", " ") // remplacer plusieurs espaces par un seul
        	                                 .replaceAll("\\d", "")
        									 .replaceAll("[^\\p{L}\\p{Z}]", "");
        	/*p{L}	Toutes les lettres Unicode
        	\\p{M}	Les marques diacritiques (accents combinés)
        	\\p{Z}	Les espaces Unicode (y compris  tab)*/
        	
            Nom normalise = new Nom(nom.getId(), texteNormalise);
            resultat.add(normalise);
        }

        return resultat;
    }

    @Override
    public String toString() {
        return "Normalisation";
    }
}
