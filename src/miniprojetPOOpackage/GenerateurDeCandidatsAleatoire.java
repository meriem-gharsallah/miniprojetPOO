package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;
public class GenerateurDeCandidatsAleatoire implements GenerateurDeCandidats {
	public List<Couple> generer(List<Nom> liste1, List<Nom> liste2) {
		List<Couple> L2 = new ArrayList<>();
		for (Nom n:liste1) {
			for (Nom n1:liste2) {
				Couple c=new Couple(n,n1);
				L2.add(c);
			}
		}
	    
	    return L2;
	}
}

