package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;

public class MoteurDeRecherche {
	private Configuration configuration;
	public MoteurDeRecherche(Configuration configuration) {
		this.configuration = configuration;}
	public List<NomAvecScore> rechercher(List<Nom> L,Nom n) {
		 List<Nom> pretraiteListe = configuration.getPretraiteur().pretraiter(L);
		 List<NomAvecScore> L2 = new ArrayList<NomAvecScore>();
		 List<Nom> nomPretraiteListe = configuration.getPretraiteur().pretraiter(List.of(n));
		 Nom nomPretraite = nomPretraiteListe.get(0);
		 List<Couple> couples = configuration.getGenerateur().generer(pretraiteListe, List.of(nomPretraite));
		 List<NomAvecScore> resultats = new ArrayList<NomAvecScore>();
		    for (Couple c : couples) {
		    	Nom n1=new Nom(5,"kk");
		    	NomAvecScore m =new NomAvecScore(n1,5);
		    	double r=configuration.getComparateur().comparer(c.getNom1(), c.getNom2());
		    	m.setNom(c.getNom1());
		    	m.setScore(r);
		        resultats.add(m);
		    	
		        
		    }
		    
		    L2=configuration.getSelectionneur().selectionner(resultats);

		
		
		
		
		
		return L2;
	}
	public List<Nom> dedupliquer(List<Nom> L) {
	    List<Nom> L1 = new ArrayList<Nom>();
	    

	    for (int i = 0; i < L.size(); i++) {
	        boolean estDoublon = false;

	        for (int j = 0; j < L1.size(); j++) {
	            if (configuration.getComparateur().comparer(L.get(i), L1.get(j)) >= configuration.getSeuil()) {
	                estDoublon = true;
	                break;
	            }
	        }

	        if (!estDoublon) {
	            L1.add(L.get(i));
	        }
	    }

	    return L1;
	}

	public List<NomAvecScore> comparer(List<Nom> L,List<Nom> L1) {
		List<NomAvecScore> L2 = new ArrayList<>();
		return L2;
	}
	
}
