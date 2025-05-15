package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
public class GenerateurDeCandidatsAvecIndex implements GenerateurDeCandidats {
	public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
		List<CoupleDeNoms> L2 = new ArrayList<>();
		Map<Integer, List<Nom>> mapTaille = new HashMap<>();

		
		for (Nom n : liste1) {
		
			
		    int taille = n.getNom().length();
		    
		    mapTaille.computeIfAbsent(taille, k -> new ArrayList<>()).add(n);
		    
		}
		
		

	    for (Nom n2 : liste2) {
	            int taille2 = n2.getNom().length();
	            for (int i = (taille2 - 8); i <= (taille2 + 8); i++) {
	                if (mapTaille.containsKey(i)) {
	                    for (Nom n1 : mapTaille.get(i)) {
	                        L2.add(new CoupleDeNoms(n1, n2));
	                    }
	                }
	            }
	           
	        }
	   
	    return L2;
	}
}
