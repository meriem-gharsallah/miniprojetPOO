package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;
public class GenerateurDeCandidatsAleatoire implements GenerateurDeCandidats {
	public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
		List<CoupleDeNoms> L2 = new ArrayList<>();
		for (Nom n:liste1) {
			for (Nom n1:liste2) {
				CoupleDeNoms c=new CoupleDeNoms(n,n1);
				L2.add(c);
			}
		}
	    
	    return L2;
	}
}

