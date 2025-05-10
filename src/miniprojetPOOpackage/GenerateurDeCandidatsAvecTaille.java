package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;
public class GenerateurDeCandidatsAvecTaille implements GenerateurDeCandidats {
	public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
		List<CoupleDeNoms> L2 = new ArrayList<>();
		for (Nom n:liste1) {
			for (Nom n1:liste2) {

				/*List<String> motsModifiables = n.genererListeDeChaines(n).getElements();
				List<String> motsModifiables1 = n1.genererListeDeChaines(n1).getElements();*/

				if(n.getNom().length() >= n1.getNom().length() - 5 &&
						n.getNom().length() <= n1.getNom().length() + 5) {
					CoupleDeNoms c=new CoupleDeNoms(n,n1);
					L2.add(c);
				}
				
			}
		}
	    
	    return L2;
	}
}