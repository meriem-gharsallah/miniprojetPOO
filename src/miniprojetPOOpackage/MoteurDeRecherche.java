package miniprojetPOOpackage;
import java.util.ArrayList;
import java.util.List;

public class MoteurDeRecherche {
	private ComparateurNom comparateurnom;
	private NomAvecScore m;
	private Configuration configuration;
	private GenerateurDeCandidats candidats;
	private RecuperateurDeNoms nomRecupere;
	private Pretraiteur pretraitement;
	private SelectionneurDeResultats selectionne;
	public void MoteurDeRecherche(ComparateurNom comparateurnom,Configuration configuration,GenerateurDeCandidats candidats,RecuperateurDeNoms nomRecupere,Pretraiteur pretraitement,SelectionneurDeResultats selectionne) {
		this.comparateurnom=comparateurnom;
		this.configuration=configuration;
		this.candidats=candidats;
		this.nomRecupere=nomRecupere;
		this.pretraitement=pretraitement;
		this.selectionne=selectionne;
	}
	public List<Nom> rechercher(List<Nom> L,Nom n) {
		 List<Nom> pretraiteListe = pretraitement.pretraiter(L);
		 List<Nom> nomPretraiteListe = pretraitement.pretraiter(List.of(n));
		 Nom nomPretraite = nomPretraiteListe.get(0);
		 List<Couple> couples = candidats.generer(pretraiteListe, List.of(nomPretraite));
		 List<NomAvecScore> resultats = new ArrayList();
		    for (Couple c : couples) {
		    	double r=comparateurnom.comparer(c.getNom1(), c.getNom2());
		    	m.setNom(c.getNom2());
		    	m.setScore(r);
		        resultats.add(m);
		    	selectionne.selectionner(resultats);
	
		    }

		
		
		
		
		List<Nom> L2 = new ArrayList<>();
		return L2;
	}
	public List<Nom> dedupliquer(List<Nom> L) {
		List<Nom> L2 = new ArrayList<>();
		return L2;
	}
	public List<NomAvecScore> comparer(List<Nom> L,List<Nom> L1) {
		List<NomAvecScore> L2 = new ArrayList<>();
		return L2;
	}
	
}
